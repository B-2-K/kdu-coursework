package com.caching.customlogger;

import org.slf4j.LoggerFactory;

/**
 * Custom logger class for simplified logging operations.
 */
public class Logger {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    /**
     * Logs an informational message.
     *
     * @param message The message to be logged.
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Logs an error message.
     *
     * @param message The error message to be logged.
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Logs a warning message.
     *
     * @param message The warning message to be logged.
     */
    public static void warn(String message) {
        logger.warn(message);
    }

    /**
     * Logs a debug message.
     *
     * @param message The debug message to be logged.
     */
    public static void debug(String message) {
        logger.debug(message);
    }
}
