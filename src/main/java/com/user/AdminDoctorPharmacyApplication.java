package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdminDoctorPharmacyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminDoctorPharmacyApplication.class, args);
	}

}
