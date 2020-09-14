package com.vina.genbe.controller;



import java.util.ArrayList;
import java.util.List;
// import java.sql.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vina.genbe.model.dto.StatusMessageDto;
import com.vina.genbe.model.dto.DetailPendidikanDto;
import com.vina.genbe.model.dto.PersonBiodataDto;
import com.vina.genbe.model.dto.StatusDto;
import com.vina.genbe.model.entity.BiodataEntity;
import com.vina.genbe.model.entity.PersonEntity;
import com.vina.genbe.repository.BiodataRepository;
import com.vina.genbe.repository.PendidikanRepository;
import com.vina.genbe.repository.PersonRepository;
import com.vina.genbe.service.PersonService;
import com.vina.genbe.service.ServiceImpl;

@RestController
@RequestMapping("/person")
public class PersonController {
	private final PersonRepository personRepository;
	private final PendidikanRepository pendidikanRepository;
	private final BiodataRepository biodataRepository;

	@Autowired
	private PersonService personService = new ServiceImpl();

	@Autowired
	public PersonController(PersonRepository personRepository, PendidikanRepository pendidikanRepository, BiodataRepository biodataRepository) {
		this.personRepository = personRepository;
		this.pendidikanRepository = pendidikanRepository;
		this.biodataRepository = biodataRepository;
	}
	
	@GetMapping("/detail/{iD}")
	 public PersonBiodataDto getpersonBiodata(@PathVariable Integer iD) {
			PersonEntity personEntity = personRepository.findById(iD).get();
        	PersonBiodataDto dto = new PersonBiodataDto();
        	dto.setiD(personEntity.getId());
        	dto.setIdBio(personEntity.getBiodataEntity().getIdBio());
        	dto.setAlamaT(personEntity.getAlamat());
        	dto.setHp(personEntity.getBiodataEntity().getNoHp());
        	dto.setNamA(personEntity.getNama());
        	dto.setNiK(personEntity.getNik());
        	dto.setTglLahir(personEntity.getBiodataEntity().getTanggalLahir());
        	dto.setTmpLahir(personEntity.getBiodataEntity().getTempatLahir());
        	
        return dto;
    }
	
	 @GetMapping("/all")
	    public List<PersonBiodataDto> getListBiodata() {
	        List<PersonEntity> personList = personRepository.findAll();
	        List<PersonBiodataDto> personBiodataDtos = new ArrayList<>();
	        for (PersonEntity p : personList) {
	        	PersonBiodataDto dto = new PersonBiodataDto();
	        	BiodataEntity biodataEntity = biodataRepository.findByPersonEntityId(p.getId());
	        	dto.setiD(p.getId());
	        	dto.setAlamaT(p.getAlamat());
	        	dto.setHp(biodataEntity.getNoHp());
	        	dto.setNamA(p.getNama());
	        	dto.setNiK(p.getNik());
	        	dto.setTglLahir(biodataEntity.getTanggalLahir());
	        	dto.setTmpLahir(biodataEntity.getTempatLahir());
	        	personBiodataDtos.add(dto);
	        	
	        }
	        return personBiodataDtos;
	    }

	@GetMapping("/{nik}")
	public List<Object> cariData(@PathVariable String nik) {
		List<Object> data = new ArrayList<>();
		StatusDto stat = new StatusDto();
		StatusMessageDto statmes = new StatusMessageDto();
		if (nik.length() == 16) {
			if (personRepository.findByNikLike(nik).isEmpty() == false) {
				PersonEntity personEntity = personRepository.findByNikLike(nik).get(0);
				DetailPendidikanDto det = convertToDto(personEntity);
				stat.setStatus("true");
				stat.setMessage("success");
				stat.setDetailPendidikanDto(det);
				data.add(stat);
			} else {
				statmes.setStatus("false");
				statmes.setMessage("data dengan NIK " + nik + " tidak ditemukan");
				data.add(statmes);
			}
		} else {
			statmes.setStatus("false");
			statmes.setMessage("jumlah digit data dengan NIK " + nik + " tidak sama dengan 16");
			data.add(statmes);
		}
		return data;
	}

	@PostMapping
	public StatusMessageDto insert(@RequestBody PersonBiodataDto dto) {
		StatusMessageDto status = new StatusMessageDto();
		DetailPendidikanDto detailPendidikanDto = new DetailPendidikanDto();
		personService.getAge(detailPendidikanDto, dto);
		if (dto.getNiK().length() == 16 && Integer.parseInt(detailPendidikanDto.getUmuR()) >= 30 ) {
			personService.insertPerson(dto);
			status.setStatus("Success!!");
			status.setMessage("Data Berhasil Masuk");
			return status;
		} else if ( dto.getNiK().length() != 16 && Integer.parseInt(detailPendidikanDto.getUmuR()) >= 30 ) {
			status.setStatus("False");
			status.setMessage("Data Gagal Masuk, Jumlah Digit NIK tidak sama dengan 16");
			return status;
		} else if (Integer.parseInt(detailPendidikanDto.getUmuR()) < 30 &&  dto.getNiK().length() == 16) {
			status.setStatus("False");
			status.setMessage("Data Gagal Masuk, Umur kurang dari 30 Tahun");
			return status;
		} else {
			status.setStatus("False");
			status.setMessage("Data Gagal Masuk, Umur kurang dari 30 Tahun dan NIK kurang dari 16 digit");
			return status;
		}

	}


	private DetailPendidikanDto convertToDto(PersonEntity personEntity) {
		DetailPendidikanDto detail = new DetailPendidikanDto();
		detail.setNiK(personEntity.getNik());
		detail.setNamA(personEntity.getNama());
		detail.setAlamaT(personEntity.getAlamat());
		detail.setHp(personEntity.getBiodataEntity().getNoHp());
		detail.setTglLahir(personEntity.getBiodataEntity().getTanggalLahir());
		detail.setTmpLahir(personEntity.getBiodataEntity().getTempatLahir());
		personService.getUmur2(detail);
		detail.setPendidikan_terakhir(pendidikanRepository.akhirPendidikan(personEntity.getId()));
		return detail;
	}

}
