package com.example.demo.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CustomLogger is a simple utility class for logging messages using SLF4J.
 */
public class CustomLogger {

    private static final Logger logger = LoggerFactory.getLogger(CustomLogger.class);

    /**
     * Log an informational message.
     *
     * @param message The message to be logged.
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Log an error message.
     *
     * @param message The error message to be logged.
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Log a warning message.
     *
     * @param message The warning message to be logged.
     */
    public static void warn(String message) {
        logger.warn(message);
    }
}
