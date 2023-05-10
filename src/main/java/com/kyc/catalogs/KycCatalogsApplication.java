package com.kyc.catalogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class KycCatalogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KycCatalogsApplication.class, args);
	}

}
