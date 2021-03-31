package com.playzone.hospital.service;

import java.util.List;

import com.playzone.hospital.entity.Hospital;

public interface HospitalService {
	
	List<Hospital> search(String query);
	List<Hospital> getAll();
	Hospital getByID(Long id);
	Hospital create(Hospital p);
	void update(Hospital p, Long id);
	void delete(Long id);

}
