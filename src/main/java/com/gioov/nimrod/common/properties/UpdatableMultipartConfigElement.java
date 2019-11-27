package com.gioov.nimrod.common.properties;

import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.MultipartConfigElement;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-10-24
 */
public class UpdatableMultipartConfigElement extends MultipartConfigElement {

    private volatile long maxFileSize = -1;

    private volatile long maxRequestSize = -1;

    public UpdatableMultipartConfigElement(String location, long maxFileSize, long maxRequestSize, int fileSizeThreshold) {
        super(location, maxFileSize, maxRequestSize, fileSizeThreshold);
    }

    @Override
    public long getMaxFileSize() {
        return maxFileSize == -1 ? super.getMaxFileSize() : maxFileSize;
    }

    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    @Override
    public long getMaxRequestSize() {
        return maxRequestSize == -1 ? super.getMaxRequestSize() : maxRequestSize;
    }

    public void setMaxRequestSize(long maxRequestSize) {
        this.maxRequestSize = maxRequestSize;
    }
}