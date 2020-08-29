package com.vina.genbe.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.vina.genbe.model.dto.BiodataDto;
//import com.vina.genbe.model.dto.PersonDto;
import com.vina.genbe.model.entity.BiodataEntity;
import com.vina.genbe.model.entity.PersonEntity;
import com.vina.genbe.repository.BiodataRepository;
import com.vina.genbe.repository.PersonRepository;

@RestController
@RequestMapping("/biodata")

public class BiodataController {
	private final BiodataRepository biodataRepository;
	private final PersonRepository personRepository;
	
	@Autowired
	public BiodataController(BiodataRepository biodataRepository, PersonRepository personRepository) {
		this.biodataRepository= biodataRepository;
		this.personRepository = personRepository;
	}
	
	@GetMapping
	public List<BiodataDto> get(){
		List<BiodataEntity> biodataEntityList = biodataRepository.findAll();
		List<BiodataDto> biodataDtoList = biodataEntityList.stream().map(this::convertToDto)
				.collect(Collectors.toList());
		return biodataDtoList;
		
	}
	
	@GetMapping("/{idbio}")
    public BiodataDto get(@PathVariable Integer idbio) {
        if(biodataRepository.findById(idbio).isPresent()){
           BiodataDto biodataDto = convertToDto(biodataRepository.findById(idbio).get());
            return biodataDto;
        }
        return null;
    }
	
	@GetMapping("/person/{idperson}")
	public List<BiodataDto> getByPersonEntity(@PathVariable Integer idperson){
		List<BiodataEntity> biodataEntityList = biodataRepository.findAllByPersonEntityId(idperson);
		List<BiodataDto> biodataDtoList = biodataEntityList.stream().map(this::convertToDto)
				.collect(Collectors.toList());
		return biodataDtoList;
	}
	
	
	@PostMapping
	public BiodataDto insert(@RequestBody BiodataDto dto) {
		BiodataEntity biodataEntity= convertToEntity(dto);
		biodataRepository.save(biodataEntity);
		return dto;
		
	}
	
	private BiodataEntity convertToEntity(BiodataDto dto) {
		BiodataEntity biodataEntity = new BiodataEntity();
		biodataEntity.setIdBio(dto.getIdBio());
		biodataEntity.setNoHp(dto.getNoHp());
		biodataEntity.setTanggalLahir(dto.getTanggalLahir());
		biodataEntity.setTempatLahir(dto.getTempatLahir());
		
		if(personRepository.findById(dto.getIdPers()).isPresent()) {
			PersonEntity personEntity= personRepository.findById(dto.getIdPers()).get();
			biodataEntity.setPersonEntity(personEntity);
		}
		return biodataEntity;
		
	}
	
	private BiodataDto convertToDto(BiodataEntity biodataEntity) {
		BiodataDto biodataDto = new BiodataDto();
		biodataDto.setIdBio(biodataEntity.getIdBio());
		biodataDto.setNoHp(biodataEntity.getNoHp());
		biodataDto.setTanggalLahir(biodataEntity.getTanggalLahir());
		biodataDto.setTempatLahir(biodataEntity.getTempatLahir());
		return biodataDto;
		
	}
	
	
	
	

}
