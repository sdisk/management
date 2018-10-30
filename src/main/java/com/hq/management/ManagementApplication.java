package com.hq.management;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hq.management.web.mapper")
public class ManagementApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
        LOGGER.info("ManagementApplication is success!");
    }
}
