package com.kdu.smarthome;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the Smart Home Spring Boot application.
 */
@SpringBootApplication
@Slf4j
public class SmartHomeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmartHomeApplication.class, args);
		log.info("Application Started...");
	}
}
