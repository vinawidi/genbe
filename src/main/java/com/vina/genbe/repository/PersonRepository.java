package com.vina.genbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vina.genbe.model.entity.PersonEntity;

@Repository

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
	List<PersonEntity> findByNikLike(String nik);

}
