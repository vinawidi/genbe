package com.vina.genbe.service;

import java.util.List;

import com.vina.genbe.model.dto.PendidikanDto;
import com.vina.genbe.model.dto.PersonBiodataDto;

public interface PersonService {
	PersonBiodataDto insertPerson(PersonBiodataDto personBiodataDto);

	List<PendidikanDto> insertPendidikan(List<PendidikanDto> pendidikanDto, Integer id);

}
