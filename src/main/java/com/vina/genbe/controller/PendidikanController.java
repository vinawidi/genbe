package com.vina.genbe.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.vina.genbe.model.dto.PendidikanDto;
import com.vina.genbe.model.dto.PersonBiodataDto;
import com.vina.genbe.model.dto.StatusMessageDto;

import com.vina.genbe.model.entity.PendidikanEntity;
import com.vina.genbe.model.entity.PersonEntity;
import com.vina.genbe.repository.PendidikanRepository;
import com.vina.genbe.repository.PersonRepository;
import com.vina.genbe.service.PersonService;
import com.vina.genbe.service.ServiceImpl;

@RestController
@RequestMapping("/pendidikan")

public class PendidikanController {
	private final PendidikanRepository pendidikanRepository;
	private final PersonRepository personRepository;

	@Autowired
	private PersonService pendService = new ServiceImpl();

	@Autowired
	public PendidikanController(PendidikanRepository pendidikanRepository, PersonRepository personRepository) {
		this.pendidikanRepository = pendidikanRepository;
		this.personRepository = personRepository;
	}

	private StatusMessageDto status() {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		statusMessageDto.setStatus("true");
		statusMessageDto.setMessage("data berhasil diinput");
		return statusMessageDto;
	}

	@PostMapping("/{id}")
	public StatusMessageDto insert(@RequestBody List<PendidikanDto> dto, @RequestParam Integer id) {
		try {
			pendService.insertPendidikan(dto, id);
			return dataBenar();
		} catch (Exception e) {
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
		statusMessageDto.setMessage("data gagal masuk");
		return statusMessageDto;
	}

	private PendidikanDto convertToDto(PendidikanEntity pendidikanEntity) {
		PendidikanDto pendidikanDto = new PendidikanDto();
		pendidikanDto.setIdPendidikan(pendidikanEntity.getIdPend());
		pendidikanDto.setJenjang(pendidikanEntity.getJenjangPendidikan());
		pendidikanDto.setInstitusi(pendidikanEntity.getInstitusiPendidikan());
		pendidikanDto.setTahunMasuk(pendidikanEntity.getThnMasuk());
		pendidikanDto.setTahunLulus(pendidikanEntity.getThnLulus());
		return pendidikanDto;
	}

}
