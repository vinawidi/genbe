package com.vina.genbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

		@Override
		public PersonEntity insertPerson(PersonEntity personEntity) {
			// TODO Auto-generated method stub
			PersonEntity enT = personRepository.save(personEntity);
			return enT;
		}

		@Override
		public BiodataEntity insertBiodata(BiodataEntity biodataEntity) {
			// TODO Auto-generated method stub
			BiodataEntity enT = biodataRepository.save(biodataEntity);
			return enT;
		}

		@Override
		public PendidikanEntity insertPendidikan(PendidikanEntity pendidikanEntity) {
			// TODO Auto-generated method stub
			PendidikanEntity enT = pendidikanRepository.save(pendidikanEntity);
			return enT;
		}
}
