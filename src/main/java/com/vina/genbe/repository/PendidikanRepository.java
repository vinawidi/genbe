package com.vina.genbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vina.genbe.model.entity.PendidikanEntity;

@Repository
public interface PendidikanRepository extends JpaRepository<PendidikanEntity, Integer> {

	List<PendidikanEntity> findAllByPersonEntityId(Integer id);

	@Query(value = "select jenjang from t_pendidikan where idperson =?1 order by tahun_lulus desc limit 1 ", nativeQuery = true)
	String akhirPendidikan(Integer id);
}
