package com.gioov.nimrod.common.security;

import com.gioov.common.web.http.FailureEntity;
import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.common.FailureMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component
public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {

    @Autowired
    private Common common;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter printWriter = httpServletResponse.getWriter();

        // 检查 e 是否为验证码错误类
        if (e instanceof VerifyCodeFilter.VerifyCodeCheckException) {
            printWriter.write(common.objectToJson(new FailureEntity(e.getMessage())));
        } else {
            printWriter.write(common.objectToJson(new FailureEntity(FailureMessage.LOGIN_FAIL)));
        }
        printWriter.flush();
        printWriter.close();
    }
}