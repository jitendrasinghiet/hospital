package com.playzone.hospital.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class ErrorRespDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("code")
	private String code;

	@JsonProperty("mesg")
	private String mesg;

	public ErrorRespDto(String code, String mesg) {
		super();
		this.code = code;
		this.mesg = mesg;
	}	
	
}
