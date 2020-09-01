package com.vina.genbe.model.dto;

public class StatusDto {
	private String status;
	private String message;
	private DetailPendidikanDto detailPendidikanDto;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public DetailPendidikanDto getDetailPendidikanDto() {
		return detailPendidikanDto;
	}
	public void setDetailPendidikanDto(DetailPendidikanDto detailPendidikanDto) {
		this.detailPendidikanDto = detailPendidikanDto;
	}
	
	
}
