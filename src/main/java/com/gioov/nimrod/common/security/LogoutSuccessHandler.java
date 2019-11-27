package com.gioov.nimrod.common.security;

import com.gioov.nimrod.common.others.Common;
import com.gioov.tile.web.http.SuccessEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-07
 */
@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutSuccessHandler.class);

    @Autowired
    private Common common;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.write(common.objectToJson(new SuccessEntity("注销成功")));
        printWriter.flush();
        printWriter.close();
    }
}
