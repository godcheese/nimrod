package com.gioov.nimrod.common.security;

import com.gioov.nimrod.user.User;
import com.gioov.tile.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.gioov.nimrod.system.api.SystemRestController.VERIFY_CODE_NAME;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component
public class VerifyCodeFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(VerifyCodeFilter.class);

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            verifyCodeCheck(httpServletRequest);
        } catch (VerifyCodeCheckException e) {
            e.printStackTrace();
            authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void verifyCodeCheck(HttpServletRequest httpServletRequest) throws VerifyCodeCheckException {
        if (httpServletRequest.getRequestURI().equalsIgnoreCase(httpServletRequest.getContextPath() + User.Api.LOGIN)) {
            HttpSession httpSession = httpServletRequest.getSession();
            ImageUtil.VerifyCodeImage verifyCodeImage = (ImageUtil.VerifyCodeImage) httpSession.getAttribute(VERIFY_CODE_NAME);
            String requestVerifyCode = httpServletRequest.getParameter(VERIFY_CODE_NAME);

            if (requestVerifyCode == null || "".equals(requestVerifyCode) || verifyCodeImage == null) {
                throw new VerifyCodeCheckException("验码不正确");
            } else if (verifyCodeImage.isExpire()) {
                throw new VerifyCodeCheckException("验证码已过期");
            } else if (!requestVerifyCode.equalsIgnoreCase(verifyCodeImage.getVerifyCode().toLowerCase())) {
                httpSession.removeAttribute(VERIFY_CODE_NAME);
                throw new VerifyCodeCheckException("验证码不正确");
            }
        }
    }

    public class VerifyCodeCheckException extends AuthenticationException {
        private static final long serialVersionUID = 7399186031568040869L;
        public VerifyCodeCheckException(String msg) {
            super(msg);
        }
    }

}
