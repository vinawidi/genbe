package com.vina.genbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vina.genbe.model.entity.BiodataEntity;

@Repository

public interface BiodataRepository extends JpaRepository<BiodataEntity, Integer> {

	List<BiodataEntity> findAllByPersonEntityId(Integer id);

}
