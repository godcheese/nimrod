package com.gioov.nimrod.common.controller;

import com.gioov.nimrod.common.others.FailureEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomBasicErrorController extends AbstractErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBasicErrorController.class);

    @Value("${server.error.path:${error.path:/error}}")
    public String errorPath = "/error";

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    public CustomBasicErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    /**
     * html
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     */
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus httpStatus = getStatus(request);
        int code = httpStatus.value();
//        Map<String, Object> errorAttributes = getErrorAttributes(request, true);
        Map<String, Object> errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        LOGGER.info("status={},exception={}", code, errorAttributes);
        code = RestControllerAdviceHandler.codeSwitch(code);
        httpStatus = HttpStatus.valueOf(code);
//        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
//                request, true));
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(request, ErrorAttributeOptions.defaults()));
        ModelAndView modelAndView = resolveErrorView(request, response, httpStatus, model);
        return (modelAndView == null ? new ModelAndView(code + "", model, httpStatus) : modelAndView);
    }

    /**
     * json
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ResponseEntity<FailureEntity>
     */
    @RequestMapping
    @ResponseBody
    public ResponseEntity<FailureEntity> error(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus httpStatus = getStatus(request);
        int code = httpStatus.value();
        Map<String, Object> errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        LOGGER.info("status={},exception={}", code, errorAttributes);
        code = RestControllerAdviceHandler.codeSwitch(code);
        httpStatus = HttpStatus.valueOf(code);
        return new ResponseEntity<>(new FailureEntity((String) errorAttributes.get("error"), httpStatus.value()), httpStatus);
    }
}
