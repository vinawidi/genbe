package com.vina.genbe.model.dto;

import java.util.Date;

public class DetailPendidikanDto {

	private String niK;
	private String namA;
	private String alamaT;
	private String hp;
	private Date tglLahir;
	private String tmpLahir;
	private String umuR;
	private String pendidikan_terakhir;

	public String getNiK() {
		return niK;
	}

	public void setNiK(String niK) {
		this.niK = niK;
	}

	public String getNamA() {
		return namA;
	}

	public void setNamA(String namA) {
		this.namA = namA;
	}

	public String getAlamaT() {
		return alamaT;
	}

	public void setAlamaT(String alamaT) {
		this.alamaT = alamaT;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public Date getTglLahir() {
		return tglLahir;
	}

	public void setTglLahir(Date date) {
		this.tglLahir = date;
	}

	public String getTmpLahir() {
		return tmpLahir;
	}

	public void setTmpLahir(String tmpLahir) {
		this.tmpLahir = tmpLahir;
	}

	public String getUmuR() {
		return umuR;
	}

	public void setUmuR(String umuR) {
		this.umuR = umuR;
	}

	public String getPendidikan_terakhir() {
		return pendidikan_terakhir;
	}

	public void setPendidikan_terakhir(String pendidikan_terakhir) {
		this.pendidikan_terakhir = pendidikan_terakhir;
	}

}
