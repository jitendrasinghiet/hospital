package com.playzone.hospital.exception;

public class HospitalNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private final String code;
	
	public HospitalNotFoundException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	

}
