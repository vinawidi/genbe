package com.vina.genbe.service;

import java.util.List;

import com.vina.genbe.model.dto.PendidikanDto;
import com.vina.genbe.model.dto.PersonBiodataDto;
//import com.vina.genbe.model.entity.BiodataEntity;
//import com.vina.genbe.model.entity.PendidikanEntity;
//import com.vina.genbe.model.entity.PersonEntity;

public interface PersonService {
		PersonBiodataDto insertPerson(PersonBiodataDto personBiodataDto);
//		PendidikanDto insertPendidikan(PendidikanDto pendidikanDto);
//		PendidikanDto insertPendidikan(List<PendidikanDto> pendidikanDto);
		List <PendidikanDto>  insertPendidikan(List<PendidikanDto> pendidikanDto, Integer id);
		
		
		

}
