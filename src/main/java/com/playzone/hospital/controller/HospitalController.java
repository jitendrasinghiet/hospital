package com.playzone.hospital.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.playzone.hospital.entity.Hospital;
import com.playzone.hospital.service.HospitalService;
import com.playzone.patient.remote.PatientOperations;

@RestController
public class HospitalController implements HospitalOperations{

	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	private PatientOperations patientOperations;
		
	@Override
    public List<Hospital> search(@RequestParam(value = "query") String search) {
        return hospitalService.search(search);
    }

	@Override
	public List<Hospital> retrieveAllHospitals() {
		return hospitalService.getAll();
	}

	@Override
	public Hospital retrieveHospital(@PathVariable long id) {
		return hospitalService.getByID(id);
	}
	
	@Override
	public Hospital retrieveHospitalPatients(@PathVariable long id) {
		Hospital p = hospitalService.getByID(id);
		p.setPatients(patientOperations.search("query=hospitalId:"+id));
		return p;
	}
	
	@Override
	public Hospital searchHospitalPatients(long id, String search) {
		Hospital p = hospitalService.getByID(id);
		if(null!=search) {
			search = search.replaceFirst("query=", "query=hospitalId:"+id+",");
			p.setPatients(patientOperations.search(search));
		}
		
		return p;
	}

	@Override
	public void deleteHospital(@PathVariable long id) {
		hospitalService.delete(id);
	}

	@Override
	public ResponseEntity<Object> createHospital(@RequestBody Hospital hospital) {
		Hospital savedHospital = hospitalService.create(hospital);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedHospital.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@Override
	public ResponseEntity<Object> updateHospital(@RequestBody Hospital hospital, @PathVariable long id) {
		hospitalService.update(hospital, id);
		return ResponseEntity.noContent().build();
	}
	
}