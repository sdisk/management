package com.hq.management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagementApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
        LOGGER.info("ManagementApplication is success!");
    }
}
