package com.gioov.nimrod.common.security;

import com.gioov.nimrod.common.others.Common;
import com.gioov.tile.web.http.SuccessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
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
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Autowired
    private Common common;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SimpleUser simpleUser = (SimpleUser) authentication.getPrincipal();
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.write(common.objectToJson(new SuccessEntity(simpleUser)));
        printWriter.flush();
        printWriter.close();
    }
}
