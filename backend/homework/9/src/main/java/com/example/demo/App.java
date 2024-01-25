package com.example.demo;

import com.example.demo.logger.CustomLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class that serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		CustomLogger.info("Application started...");
	}
}
