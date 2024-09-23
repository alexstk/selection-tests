package com.rockwell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RockwellBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RockwellBackendApplication.class, args);
	}

}
