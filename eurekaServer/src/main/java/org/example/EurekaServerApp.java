package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer

public class EurekaServerApp {
    private static Logger logger = LoggerFactory.getLogger(EurekaServerApp.class);
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp.class, args);
        logger.info("----------------------EurekaServer is Started----------------------");
    }
}
