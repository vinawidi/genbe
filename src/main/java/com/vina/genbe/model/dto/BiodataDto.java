package com.vina.genbe.model.dto;

import java.util.Date;

public class BiodataDto {

	private Integer idBio;
	private String noHp;
	private Date tanggalLahir;
	private String tempatLahir;
	private Integer idPers;

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

	public Integer getIdPers() {
		return idPers;
	}

	public void setIdPers(Integer idPers) {
		this.idPers = idPers;
	}

}
