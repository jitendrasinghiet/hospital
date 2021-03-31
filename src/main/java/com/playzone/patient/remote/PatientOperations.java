package com.playzone.patient.remote;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.playzone.patient.dto.Patient;

@FeignClient(name="patient-service")
@RequestMapping("/patient")
public interface PatientOperations {

	@GetMapping("/search")
    public List<Patient> search(@RequestParam(value = "query") String search);

	@GetMapping("")
	public List<Patient> retrieveAllPatients();
	
}
