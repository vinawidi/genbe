package com.vina.genbe.model.entity;



import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "t_biodata")

public class BiodataEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bio")
	private Integer idBio;

	@Column(name = "no_hp", length = 16)
	private String noHp;

	@Column(name = "tanggal_lahir", nullable = false)
	private Date tanggalLahir;

	@Column(name = "tempat_lahir", length = 50)
	private String tempatLahir;

	@OneToOne
	@JoinColumn(name = "idperson")
	private PersonEntity personEntity;

	public Integer getIdBio() {
		return idBio;
	}

	public void setIdBio(Integer idBio) {
		this.idBio = idBio;
	}

	public String getNoHp() {
		return noHp;
	}

	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date date) {
		this.tanggalLahir = date;
	}

	public String getTempatLahir() {
		return tempatLahir;
	}

	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}

	public PersonEntity getPersonEntity() {
		return personEntity;
	}

	public void setPersonEntity(PersonEntity personEntity) {
		this.personEntity = personEntity;
	}

	

}
