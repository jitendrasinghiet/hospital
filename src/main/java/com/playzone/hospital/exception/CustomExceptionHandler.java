package com.playzone.hospital.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.playzone.hospital.dto.ErrorRespDto;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
		log.error("EXCEPTION : ", ex);
		ErrorRespDto error = new ErrorRespDto("500", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}	

	@ExceptionHandler({ HospitalNotFoundException.class })
	public ResponseEntity<Object> handleESPNotFoundException(final HospitalNotFoundException ex, final WebRequest request) {
		log.info("NOT_FOUND {} : {} ", ex.getCode(), ex.getMessage());
		ErrorRespDto errorResponseDto = new ErrorRespDto(ex.getCode(), ex.getMessage());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	}

}
