package com.gioov.nimrod.common.controller;

import com.gioov.nimrod.common.others.FailureEntity;
import com.gioov.nimrod.system.service.DictionaryService;
import com.gioov.tile.util.DataSizeUtil;
import com.gioov.tile.web.exception.BaseResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.unit.DataSize;
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
    private DictionaryService dictionaryService;
    @Autowired
    private FailureEntity failureEntity;

    @ExceptionHandler({BaseResponseException.class})
    public ResponseEntity<FailureEntity> defaultExceptionHandler(HttpServletRequest httpServletRequest, Throwable throwable) {
        HttpStatus httpStatus = getStatus(httpServletRequest);
        throwable.printStackTrace();
        String message = throwable.getMessage();
        int code = 0;
        if (throwable instanceof BaseResponseException) {
            code = ((BaseResponseException) throwable).getCode();
        }
        return new ResponseEntity<>(new FailureEntity(message, code), httpStatus);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<FailureEntity> sizeLimitExceededExceptionHandler(HttpServletRequest httpServletRequest, Throwable throwable) {
        HttpStatus httpStatus = getStatus(httpServletRequest);
        FailureEntity fm = failureEntity.i18n("file.upload_fail");
        if (throwable instanceof MaxUploadSizeExceededException) {
            String maxFileSize = DataSizeUtil.pretty(DataSize.parse((String) dictionaryService.get("FILE", "MAX_FILE_SIZE")).toBytes());
            String maxRequestSize = DataSizeUtil.pretty(DataSize.parse((String) dictionaryService.get("FILE", "MAX_REQUEST_SIZE")).toBytes());
            fm = failureEntity.i18n("file.upload_fail_max_upload_size_exceeded", maxFileSize, maxRequestSize);
        }
        throwable.printStackTrace();
        return new ResponseEntity<>(fm, httpStatus);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<FailureEntity> badCredentialsExceptionHandler(HttpServletRequest httpServletRequest, Throwable throwable) {
        HttpStatus httpStatus = getStatus(httpServletRequest);
        throwable.printStackTrace();
        return new ResponseEntity<>(new FailureEntity(throwable.getMessage(), httpStatus.value()), httpStatus);
    }

    public static HttpStatus getStatus(HttpServletRequest request) {
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

    public static int codeSwitch(int code) {
        switch (code) {
            case 400:
                break;
            case 403:
                break;
            case 404:
                break;
            case 500:
                break;
            default:
                code = 500;
                break;
        }
        return code;
    }
}
