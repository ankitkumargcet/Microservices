package com.aki.rentcloud.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.aki.rentcloud.commons.model")
public class RentcloudProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentcloudProfileServiceApplication.class, args);
	}

}
