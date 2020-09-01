package com.vina.genbe.controller;

import java.sql.Date;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vina.genbe.model.dto.StatusMessageDto;
//import com.vina.genbe.model.dto.detailPendidikanDto;
import com.vina.genbe.model.dto.DetailPendidikanDto;
//import com.vina.genbe.model.dto.BiodataDto;
import com.vina.genbe.model.dto.PersonBiodataDto;
import com.vina.genbe.model.dto.StatusDto;
//import com.vina.genbe.model.dto.PersonDto;
import com.vina.genbe.model.entity.BiodataEntity;
//import com.vina.genbe.model.entity.BiodataEntity;
import com.vina.genbe.model.entity.PersonEntity;
import com.vina.genbe.repository.BiodataRepository;
import com.vina.genbe.repository.PendidikanRepository;
//import com.vina.genbe.repository.BiodataRepository;
import com.vina.genbe.repository.PersonRepository;
import com.vina.genbe.service.PersonService;
import com.vina.genbe.service.ServiceImpl;

@RestController
@RequestMapping("/person")
public class PersonController {
	private final PersonRepository personRepository;
	private final PendidikanRepository pendidikanRepository;

	@Autowired
	private PersonService personService = new ServiceImpl();

	@Autowired
	public PersonController(PersonRepository personRepository, PendidikanRepository pendidikanRepository) {
		this.personRepository = personRepository;
		this.pendidikanRepository = pendidikanRepository;
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
		hitungUmur(dto.getTglLahir());
		if (dto.getNiK().length() == 16 && Integer.parseInt(hitungUmur(dto.getTglLahir())) > 30) {
			personService.insertPerson(dto);
			status.setStatus("true");
			status.setMessage("data berhasil masuk");
			return status;
		} else if (Integer.parseInt(hitungUmur(dto.getTglLahir())) > 30 && dto.getNiK().length() != 16) {
			status.setStatus("false");
			status.setMessage("data gagal masuk, jumlah digit nik tidak sama dengan 16");
			return status;
		} else if (Integer.parseInt(hitungUmur(dto.getTglLahir())) < 30 && dto.getNiK().length() == 16) {
			status.setStatus("false");
			status.setMessage("data gagal masuk, umur kurang dari 30 tahun");
			return status;
		} else {
			status.setStatus("false");
			status.setMessage("data gagal masuk, umur kurang dari 30 tahun dan NIK kurang dari 16 digit");
			return status;
		}

	}

	private String hitungUmur(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer umur = Year.now().getValue() - calendar.get(Calendar.YEAR);
		String biodataUmur = Integer.toString(umur);
		return biodataUmur;

	}

	private DetailPendidikanDto convertToDto(PersonEntity personEntity) {
		DetailPendidikanDto detail = new DetailPendidikanDto();
		detail.setNiK(personEntity.getNik());
		detail.setNamA(personEntity.getNama());
		detail.setAlamaT(personEntity.getAlamat());
		detail.setHp(personEntity.getBiodataEntity().getNoHp());
		detail.setTglLahir(personEntity.getBiodataEntity().getTanggalLahir());
		detail.setTmpLahir(personEntity.getBiodataEntity().getTempatLahir());
		detail.setUmuR(hitungUmur(personEntity.getBiodataEntity().getTanggalLahir()));
		detail.setPendidikan_terakhir(pendidikanRepository.akhirPendidikan(personEntity.getId()));
		return detail;
	}

}
