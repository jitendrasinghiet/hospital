package com.playzone.hospital.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.playzone.hospital.entity.Hospital;
import com.playzone.hospital.exception.HospitalNotFoundException;
import com.playzone.hospital.repository.HospitalRepository;
import com.playzone.hospital.search.SpecificationBuilder;
import com.playzone.kafka.KafkaSender;

@Service
public class HospitalServiceImpl implements HospitalService{
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private KafkaSender kafkaSender;

	@Override
	public List<Hospital> getAll() {
		return hospitalRepository.findAll();
	}

	@Override
	public Hospital getByID(Long id) {
		Optional<Hospital> Hospital = hospitalRepository.findById(id);
		if (!Hospital.isPresent())
			throw new HospitalNotFoundException(HttpStatus.NOT_FOUND.name(),"id-" + id);
		return Hospital.get();
	}

	@Override
	public Hospital create(Hospital p) {
		return hospitalRepository.save(p);
	}

	@Override
	public void update(Hospital p, Long id) {
		getByID(id);//check exists
		p.setId(id);		
		hospitalRepository.save(p);		
	}

	@Override
	public void delete(Long id) {
		hospitalRepository.deleteById(id);	
		kafkaSender.sendMessage("Hospital deleted:"+id);
	}

	@Override
	public List<Hospital> search(String query) {
		SpecificationBuilder builder = new SpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(query + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        
        Specification<Hospital> spec = builder.build();
        return hospitalRepository.findAll(spec);
	}	

}
