package org.customlogger;
import org.slf4j.LoggerFactory;

public class Logg {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logg.class);

    public void info(String message) {
        logger.info(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    public void debug(String message) {
        logger.debug(message);
    }
}
