package com.vina.genbe.model.dto;

import java.sql.Date;

public class PersonBiodataDto {
	
	private Integer iD;
	private String niK;
	private String namA;
	private String alamaT;
	private String hp;
	private Date tglLahir;
	private String tmpLahir;
	
	
	public Integer getiD() {
		return iD;
	}
	public void setiD(Integer iD) {
		this.iD = iD;
	}
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
	public void setTglLahir(Date tglLahir) {
		this.tglLahir = tglLahir;
	}
	public String getTmpLahir() {
		return tmpLahir;
	}
	public void setTmpLahir(String tmpLahir) {
		this.tmpLahir = tmpLahir;
	}
	
	

}
