package com.gioov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@SpringBootApplication
public class NimrodBootstrap extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NimrodBootstrap.class);

    public static void main(String[] args) {
        SpringApplication.run(NimrodBootstrap.class, args);
        LOGGER.info("==================={}===================", "Nimrod is started");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NimrodBootstrap.class);
    }

}