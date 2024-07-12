package com.caching;

import com.caching.customlogger.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main class for the GeoCoding application.
 */
@EnableCaching
@SpringBootApplication
public class GeoCodingApp {

	/**
	 * Main method to start the GeoCoding application.
	 *
	 * @param args Command line arguments (if any).
	 */
	public static void main(String[] args) {
		SpringApplication.run(GeoCodingApp.class, args);
		Logger.info("Application started...");
	}
}
