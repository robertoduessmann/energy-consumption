package com.electric.energyconsumption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EnergyConsumptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergyConsumptionApplication.class, args);
	}

}
