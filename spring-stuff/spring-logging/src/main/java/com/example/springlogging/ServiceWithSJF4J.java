package com.example.springlogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceWithSJF4J {
    private final Logger logger = LoggerFactory.getLogger(ServiceWithSJF4J.class);

    public void helloFromSJF4J() {
        logger.info("Hello from SJF4J");
        logger.info("This is a series of log messages");
        logger.info("This application tests the logging");
        logger.info("BYE!");
    }
}
