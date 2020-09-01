package com.vina.genbe.controller;


import java.time.Year;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vina.genbe.model.dto.StatusMessageDto;
//import com.vina.genbe.model.dto.detailPendidikanDto;
import com.vina.genbe.model.dto.DetailPendidikanDto;
//import com.vina.genbe.model.dto.BiodataDto;
import com.vina.genbe.model.dto.PersonBiodataDto;
//import com.vina.genbe.model.dto.PersonDto;
import com.vina.genbe.model.entity.BiodataEntity;
//import com.vina.genbe.model.entity.BiodataEntity;
import com.vina.genbe.model.entity.PersonEntity;
import com.vina.genbe.repository.BiodataRepository;
//import com.vina.genbe.repository.BiodataRepository;
import com.vina.genbe.repository.PersonRepository;
import com.vina.genbe.service.PersonService;
import com.vina.genbe.service.ServiceImpl;

@RestController
@RequestMapping("/person")
public class PersonController {
	private final PersonRepository personRepository;
//	private final BiodataRepository biodataRepository;
	
	@Autowired
	private PersonService personService = new ServiceImpl();
	
	
	@Autowired
	public PersonController(PersonRepository personRepository, BiodataRepository biodataRepository) {
			this.personRepository = personRepository;
//			this.biodataRepository = biodataRepository;
	}
	
	
	@PostMapping
	public StatusMessageDto insert(@RequestBody PersonBiodataDto dto ) {
		StatusMessageDto status = new StatusMessageDto();
		hitungUmur(dto);
		if (dto.getNiK().length()==16 && hitungUmur(dto) > 30) {
			personService.insertPerson(dto);
			status.setStatus("true");
			status.setMessage("data berhasil masuk");
			return status;
		} else if(hitungUmur(dto) >30  && dto.getNiK().length()!=16) {
			status.setStatus("false");
			status.setMessage("data gagal masuk, jumlah digit nik tidak sama dengan 16");
			return status;
		} else if (hitungUmur(dto)<30 && dto.getNiK().length()==16) {
			status.setStatus("false");
			status.setMessage("data gagal masuk, umur kurang dari 30 tahun");
			return status;
		} else {
			status.setStatus("false");
			status.setMessage("data gagal masuk, umur kurang dari 30 tahun dan NIK kurang dari 16 digit");
			return status;
		}
		
	}
	
	private Integer hitungUmur(PersonBiodataDto dto) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dto.getTglLahir());
		Integer umur = Year.now().getValue()- calendar.get(Calendar.YEAR);
		return umur;
		
	}
	
	
//	private StatusMessageDto dataBenar() {
//		StatusMessageDto statusMessageDto = new StatusMessageDto();
//		statusMessageDto.setStatus();
//		statusMessageDto.setMessage();
//		return statusMessageDto;
//	}
//	
//	private StatusMessageDto dataSalah() {
//		StatusMessageDto statusMessageDto = new StatusMessageDto();
//		statusMessageDto.setStatus("false");
//		statusMessageDto.setMessage("data gagal masuk, jumlah digit nik tidak sama dengan 16");
//		return statusMessageDto;
//	}
//	
//	private PersonEntity convertToEntity1(PersonBiodataDto dto) {
//		PersonEntity personEntity = new PersonEntity();
//		personEntity.setNik(dto.getNiK());
//		personEntity.setNama(dto.getNamA());
//		personEntity.setAlamat(dto.getAlamaT());
//		return personEntity;
//			
//	}
	
//	private StatusMessageDto dataSalahUmur() {
//		StatusMessageDto statusMessageDto = new StatusMessageDto();
//		statusMessageDto.setStatus("false");
//		statusMessageDto.setMessage("data gagal masuk, umur kurang dari 30 tahun");
//		return statusMessageDto;
//	}
//	private StatusMessageDto dataSalahUmurDanNik() {
//		StatusMessageDto statusMessageDto = new StatusMessageDto();
//		statusMessageDto.setStatus("false");
//		statusMessageDto.setMessage("data gagal masuk, umur kurang dari 30 tahun dan NIK kurang dari 16 digit");
//		return statusMessageDto;
//	}
//	private BiodataEntity convertToEntity2(PersonBiodataDto dto) {
//	
////		PersonEntity personEntity = new PersonEntity();
////		personEntity =  personRepository.findById(dto.getiD()).get();
//		BiodataEntity biodataEntity = new BiodataEntity();
//		biodataEntity.setNoHp(dto.getHp());
//		biodataEntity.setTanggalLahir(dto.getTglLahir());
//		biodataEntity.setTempatLahir(dto.getTmpLahir());
//		
//		return biodataEntity;
//	}

		private DetailPendidikanDto convertToDto(PersonEntity personEntity) {
		DetailPendidikanDto detail = new DetailPendidikanDto();
		detail.setNiK(personEntity.getNik());
		detail.setNamA(personEntity.getNama());
		detail.setAlamaT(personEntity.getAlamat());
		detail.setHp(personEntity.getBiodataEntity().getNoHp());
		detail.setTglLahir(personEntity.getBiodataEntity().getTanggalLahir());
		detail.setTmpLahir(personEntity.getBiodataEntity().getTempatLahir());
		detail.setUmuR(Integer.toString(hitungUmur(personEntity.getBiodataEntity().getTanggalLahir())));

		return detail;
	}


}
