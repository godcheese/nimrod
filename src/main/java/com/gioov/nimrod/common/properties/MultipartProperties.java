package com.gioov.nimrod.common.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-10-24
 */
@Configuration
@EnableConfigurationProperties(org.springframework.boot.autoconfigure.web.servlet.MultipartProperties.class)
public class MultipartProperties {

    private final org.springframework.boot.autoconfigure.web.servlet.MultipartProperties multipartProperties;

    public MultipartProperties(org.springframework.boot.autoconfigure.web.servlet.MultipartProperties multipartProperties) {
        this.multipartProperties = multipartProperties;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigElement multipartConfigElement = multipartProperties.createMultipartConfig();
//        return new MultipartConfigElement(multipartConfigElement.getLocation(), multipartConfigElement.getMaxFileSize(), multipartConfigElement.getMaxRequestSize(), multipartConfigElement.getFileSizeThreshold());
        return new UpdatableMultipartConfigElement(multipartConfigElement.getLocation(), multipartConfigElement.getMaxFileSize(), multipartConfigElement.getMaxRequestSize(), multipartConfigElement.getFileSizeThreshold());
    }
}