package com.gioov;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class NimrodWarBootstrap extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NimrodJarBootstrap.class);
    }
}