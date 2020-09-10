package com.vina.genbe.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vina.genbe.model.dto.DetailPendidikanDto;
import com.vina.genbe.model.dto.PendidikanDto;
import com.vina.genbe.model.dto.PersonBiodataDto;
import com.vina.genbe.model.entity.BiodataEntity;
import com.vina.genbe.model.entity.PendidikanEntity;
import com.vina.genbe.model.entity.PersonEntity;
import com.vina.genbe.repository.BiodataRepository;
import com.vina.genbe.repository.PendidikanRepository;
import com.vina.genbe.repository.PersonRepository;
//import com.vina.genbe.service.Service;

@Service
@Transactional

public class ServiceImpl implements PersonService {
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private BiodataRepository biodataRepository;
	@Autowired
	private PendidikanRepository pendidikanRepository;

	private PersonEntity convertToEntity1(PersonBiodataDto dto) {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setNik(dto.getNiK());
		personEntity.setNama(dto.getNamA());
		personEntity.setAlamat(dto.getAlamaT());
		personEntity.setId(dto.getiD());
		return personEntity;

	}

	private BiodataEntity convertToEntity2(PersonBiodataDto dto) {

			PersonEntity personEntity = new PersonEntity();
			personEntity =  personRepository.findById(dto.getiD()).get();
		BiodataEntity biodataEntity = new BiodataEntity();
		biodataEntity.setNoHp(dto.getHp());
		biodataEntity.setTanggalLahir(dto.getTglLahir());
		biodataEntity.setTempatLahir(dto.getTmpLahir());
		biodataEntity.setIdBio(dto.getIdBio());
		biodataEntity.setPersonEntity(personEntity);

		return biodataEntity;
	}

	@Override
	public PersonBiodataDto insertPerson(PersonBiodataDto personBiodataDto) {

		PersonEntity personEntity = convertToEntity1(personBiodataDto);
		personRepository.save(personEntity);
		personBiodataDto.setiD(personEntity.getId());
		BiodataEntity biodataEntity = convertToEntity2(personBiodataDto);
		biodataEntity.setPersonEntity(personEntity);
		biodataRepository.save(biodataEntity);
		return null;
	}

	private PendidikanEntity convertToEntity3(PendidikanDto dto) {

		PendidikanEntity pendidikanEntity = new PendidikanEntity();
		pendidikanEntity.setIdPend(dto.getIdPendidikan());
		pendidikanEntity.setInstitusiPendidikan(dto.getInstitusi());
		pendidikanEntity.setJenjangPendidikan(dto.getJenjang());
		pendidikanEntity.setThnMasuk(dto.getTahunMasuk());
		pendidikanEntity.setThnLulus(dto.getTahunLulus());

		return pendidikanEntity;
	}

	@Override
	public List<PendidikanDto> insertPendidikan(List<PendidikanDto> pendidikanDto, Integer id) {
		for (PendidikanDto dto : pendidikanDto) {
			PendidikanEntity pendidikanEntity = convertToEntity3(dto);
			if (personRepository.findById(id).isPresent()) {
				PersonEntity personEntity = personRepository.findById(id).get();
				pendidikanEntity.setPersonEntity(personEntity);
			}
			pendidikanRepository.save(pendidikanEntity);
		}
		return pendidikanDto;
	}

	@Override
	public DetailPendidikanDto getUmur(DetailPendidikanDto detailPendidikanDto, PersonBiodataDto personBiodataDto) {
		java.sql.Date date = personBiodataDto.getTglLahir();
		LocalDate today = LocalDate.now();
		LocalDate birth = date.toLocalDate();
		Period umur = Period.between(birth, today); 
		detailPendidikanDto.setUmuR(Integer.toString(umur.getYears()));
		return detailPendidikanDto ;
	}


	@Override
	public DetailPendidikanDto getUmur2(DetailPendidikanDto detailPendidikanDto) {
		java.sql.Date date = detailPendidikanDto.getTglLahir();
		LocalDate today = LocalDate.now();
		LocalDate birth = date.toLocalDate();
		Period umur1 = Period.between(birth, today);
		detailPendidikanDto.setUmuR(Integer.toString(umur1.getYears()));
		return detailPendidikanDto ;

	}

}
