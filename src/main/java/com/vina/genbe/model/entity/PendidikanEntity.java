package com.vina.genbe.model.entity;

import javax.persistence.*;

@Entity
@Table(name= "t_pendidikan")

public class PendidikanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpendidikan")
	private Integer idPend;
	
	@ManyToOne
	@JoinColumn(name = "idperson", nullable = false)
	private PersonEntity personEntity;
	
	@Column(name = "jenjang", length = 10, nullable = false)
	private String jenjangPendidikan;
	
	@Column(name = "institusi", length = 50, nullable = false)
	private String institusiPendidikan;
	
	@Column(name = "tahunmasuk", length = 10, nullable = false)
	private String thnMasuk;
	
	@Column(name = "tahunLulus", length = 10, nullable = false)
	private String thnLulus;

	public Integer getIdPend() {
		return idPend;
	}

	public void setIdPend(Integer idPend) {
		this.idPend = idPend;
	}

	public PersonEntity getPersonEntity() {
		return personEntity;
	}

	public void setPersonEntity(PersonEntity personEntity) {
		this.personEntity = personEntity;
	}

	public String getJenjangPendidikan() {
		return jenjangPendidikan;
	}

	public void setJenjangPendidikan(String jenjangPendidikan) {
		this.jenjangPendidikan = jenjangPendidikan;
	}

	public String getInstitusiPendidikan() {
		return institusiPendidikan;
	}

	public void setInstitusiPendidikan(String institusiPendidikan) {
		this.institusiPendidikan = institusiPendidikan;
	}

	public String getThnMasuk() {
		return thnMasuk;
	}

	public void setThnMasuk(String thnMasuk) {
		this.thnMasuk = thnMasuk;
	}

	public String getThnLulus() {
		return thnLulus;
	}

	public void setThnLulus(String thnLulus) {
		this.thnLulus = thnLulus;
	}


	
	
	
	

}
