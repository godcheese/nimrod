package com.gioov.nimrod.common.controller;

import com.gioov.common.util.DataSizeUtil;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.common.web.http.FailureEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestControllerAdvice
public class RestControllerAdviceHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerAdviceHandler.class);

    @Autowired
    private MultipartProperties multipartProperties;

    @ExceptionHandler(BaseResponseException.class)
    public ResponseEntity<FailureEntity> defaultExceptionHandler(HttpServletRequest httpServletRequest, Throwable throwable) {
        HttpStatus httpStatus = getStatus(httpServletRequest);
        throwable.printStackTrace();
        return new ResponseEntity<>(new FailureEntity(throwable.getMessage()), httpStatus);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<FailureEntity> sizeLimitExceededExceptionHandler(HttpServletRequest httpServletRequest, Throwable throwable) {
        HttpStatus httpStatus = getStatus(httpServletRequest);

        String message = "文件上传失败";

        LOGGER.info("{}", throwable);
        if (throwable instanceof MaxUploadSizeExceededException) {

            String maxFileSize = DataSizeUtil.pretty(multipartProperties.getMaxFileSize().toBytes());
            String maxRequestSize = DataSizeUtil.pretty(multipartProperties.getMaxRequestSize().toBytes());

            LOGGER.info("getMaxRequestSize={}", maxRequestSize);
            message = "文件上传失败，上传超出最大文件上传大小（" + maxFileSize + "）或最大请求上传大小（" + maxRequestSize + "）";
        }
        throwable.printStackTrace();
        return new ResponseEntity<>(new FailureEntity(message), httpStatus);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<FailureEntity> badCredentialsExceptionHandler(HttpServletRequest httpServletRequest, Throwable throwable) {
        HttpStatus httpStatus = getStatus(httpServletRequest);
//        throwable.printStackTrace();
        return new ResponseEntity<>(new FailureEntity(throwable.getMessage()), httpStatus);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception var4) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }

}
