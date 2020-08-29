package com.vina.genbe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vina.genbe.model.dto.StatusMessageDto;
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
	private final BiodataRepository biodataRepository;
	
	@Autowired
	private PersonService personService = new ServiceImpl();
	
	
	@Autowired
	public PersonController(PersonRepository personRepository, BiodataRepository biodataRepository) {
			this.personRepository = personRepository;
			this.biodataRepository = biodataRepository;
	}
	
	
	@PostMapping
	public StatusMessageDto insert(@RequestBody PersonBiodataDto dto ) {
		if (dto.getNiK().length()<=16) {
			PersonEntity personEntity = convertToEntity1(dto);
			personRepository.save(personEntity);
			dto.setiD(personEntity.getId());
			BiodataEntity biodataEntity = convertToEntity2(dto);
			biodataRepository.save(biodataEntity);
			return dataBenar();
		} else {
			return dataSalah();
		}
		
	}
	private StatusMessageDto dataBenar() {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		statusMessageDto.setStatus("true");
		statusMessageDto.setMessage("data berhasil masuk");
		return statusMessageDto;
	}
	
	private StatusMessageDto dataSalah() {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		statusMessageDto.setStatus("false");
		statusMessageDto.setMessage("data gagal masuk, jumlah digit nik tidak sama dengan 16");
		return statusMessageDto;
	}
	
	private PersonEntity convertToEntity1(PersonBiodataDto dto) {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setNik(dto.getNiK());
		personEntity.setNama(dto.getNamA());
		personEntity.setAlamat(dto.getAlamaT());
		return personEntity;
			
	}
	
	private StatusMessageDto dataSalahUmur() {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		statusMessageDto.setStatus("false");
		statusMessageDto.setMessage("data gagal masuk, umur kurang dari 30 tahun");
		return statusMessageDto;
	}
	
	private BiodataEntity convertToEntity2(PersonBiodataDto dto) {
	
		PersonEntity personEntity = new PersonEntity();
		personEntity =  personRepository.findById(dto.getiD()).get();
		BiodataEntity biodataEntity = new BiodataEntity();
		biodataEntity.setNoHp(dto.getHp());
		biodataEntity.setTanggalLahir(dto.getTglLahir());
		biodataEntity.setTempatLahir(dto.getTmpLahir());
		biodataEntity.setPersonEntity(personEntity);
		return biodataEntity;
	}

		private PersonBiodataDto convertToDto1(PersonEntity personEntity) {
		PersonBiodataDto personBiodataDto = new PersonBiodataDto();
		personBiodataDto.setNiK(personEntity.getNik());
		personBiodataDto.setNamA(personEntity.getNama());
		personBiodataDto.setAlamaT(personEntity.getAlamat());
//		personDto.setIdPer(personEntity.getBiodataEntity().getIdPers());
		return personBiodataDto;
	}
	
		private PersonBiodataDto convertToDto2(BiodataEntity biodataEntity) {
			PersonBiodataDto personBiodataDto = new PersonBiodataDto();
			personBiodataDto.setHp(biodataEntity.getNoHp());
			personBiodataDto.setTglLahir(biodataEntity.getTanggalLahir());
			personBiodataDto.setTmpLahir(biodataEntity.getTempatLahir());
			return personBiodataDto;
			
		}
//	
	
	
	
	
	

}
