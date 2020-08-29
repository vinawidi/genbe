package com.vina.genbe.service;

import com.vina.genbe.model.entity.BiodataEntity;
import com.vina.genbe.model.entity.PendidikanEntity;
import com.vina.genbe.model.entity.PersonEntity;

public interface PersonService {
		PersonEntity insertPerson(PersonEntity personEntity);
		BiodataEntity insertBiodata(BiodataEntity biodataEntity);
		PendidikanEntity insertPendidikan(PendidikanEntity pendidikanEntity);

}
