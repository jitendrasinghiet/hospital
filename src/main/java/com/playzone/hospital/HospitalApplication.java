package com.playzone.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.playzone.patient.remote.PatientOperations;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackageClasses = {PatientOperations.class})
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}	
	
}
