package org.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the Spring application context.
 * Specifies the base packages to scan for components.
 */
@Configuration
@ComponentScan(basePackages = {"org.service", "org.service.factory"})
public class AppConfig {
}
