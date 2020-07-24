package com.godcheese.nimrod.common.security;

import com.godcheese.nimrod.common.others.Common;
import com.godcheese.nimrod.common.others.FailureEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component
public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFailureHandler.class);

    @Autowired
    private Common common;

    @Autowired
    private FailureEntity failureEntity;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter printWriter = httpServletResponse.getWriter();

        // 检查 e 是否为验证码错误类
        if (e instanceof VerifyCodeFilter.VerifyCodeCheckException) {
            printWriter.write(common.objectToJson(new FailureEntity(e.getMessage(), 0)));
        } else if (e instanceof BadCredentialsException) {
            printWriter.write(common.objectToJson(failureEntity.i18n("user.login_fail_account_or_password_error")));
        } else if (e instanceof DisabledException) {
            LOGGER.info("e.getMessage={}", e.getMessage());
            printWriter.write(common.objectToJson(failureEntity.i18n("user.login_fail_account_or_password_error")));
        }
        e.printStackTrace();
        printWriter.flush();
        printWriter.close();
    }
}