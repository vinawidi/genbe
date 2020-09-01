package com.vina.genbe.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_person")

public class PersonEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_person", length = 25)
	private Integer id;

	@Column(name = "nik", length = 16, nullable = false, unique = true)
	private String nik;

	@Column(name = "nama", length = 50)
	private String nama;

	@Column(name = "alamat", length = 255)
	private String alamat;

	@OneToOne(mappedBy = "personEntity")
	private BiodataEntity biodataEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public BiodataEntity getBiodataEntity() {
		return biodataEntity;
	}

	public void setBiodataEntity(BiodataEntity biodataEntity) {
		this.biodataEntity = biodataEntity;
	}

}
