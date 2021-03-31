package com.playzone.hospital.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.playzone.hospital.entity.Hospital;

@RequestMapping("/hospital")
public interface HospitalOperations {

	@GetMapping("/search")
    public List<Hospital> search(@RequestParam(value = "query") String search);

	@GetMapping("")
	public List<Hospital> retrieveAllHospitals();

	@GetMapping("/{id}")
	public Hospital retrieveHospital(@PathVariable long id);
	
	@GetMapping("/{id}/patients")
	public Hospital retrieveHospitalPatients(@PathVariable long id);
	
	@GetMapping("/{id}/search")
	public Hospital searchHospitalPatients(@PathVariable long id, @RequestParam(value = "query") String search);

	@DeleteMapping("/{id}")
	public void deleteHospital(@PathVariable long id);

	@PostMapping("")
	public ResponseEntity<Object> createHospital(@RequestBody Hospital hospital);
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateHospital(@RequestBody Hospital hospital, @PathVariable long id);
	
}
