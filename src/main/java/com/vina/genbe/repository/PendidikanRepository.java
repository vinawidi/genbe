package com.vina.genbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vina.genbe.model.entity.PendidikanEntity;


@Repository
public interface PendidikanRepository extends JpaRepository<PendidikanEntity, Integer> {

	List<PendidikanEntity> findAllByPersonEntityId(Integer id);
}
