package com.playzone.patient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Patient {
	
	private Long id;
	@JsonProperty("givenName")
	private String fname;
	@JsonProperty("familyName")
	private String lname;
	private String email;
	private String phone;
	private Integer age;
	private Address address;	
	
}
