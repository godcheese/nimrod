package com.gioov.nimrod.common.operationlog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gioov.nimrod.common.others.Common;
import com.gioov.nimrod.common.security.SimpleUser;
import com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl;
import com.gioov.nimrod.system.entity.OperationLogEntity;
import com.gioov.tile.util.ClientUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.*;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-07-18
 */
@Aspect
@Component
public class OperationLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationLogAspect.class);

    private OperationLogEntity operationLogEntity = new OperationLogEntity();

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Common common;

    @Pointcut("@annotation(com.gioov.nimrod.common.operationlog.OperationLog)")
    public void operationLogAspect() {
    }

    @Before(value = "operationLogAspect()")
    public void before(JoinPoint joinpoint) throws JsonProcessingException {
        long beginTime = Instant.now().toEpochMilli();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        SimpleUser simpleUser = SimpleUserDetailsServiceImpl.getCurrentSimpleUser();
        if (simpleUser != null) {
            operationLogEntity.setUserId(simpleUser.getId());
        }
        operationLogEntity.setIpAddress(ClientUtil.getClientIp(httpServletRequest));
        String operation = "";
        MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();
        Method method = methodSignature.getMethod();
        OperationLog operationLog = method.getAnnotation(OperationLog.class);
        if (operationLog != null) {
            operationLogEntity.setOperationType(operationLog.type().value());
            operation = operationLog.value();
            if ("".equals(operation)) {
                operation = operationLog.operation();
            }
        }
        operationLogEntity.setOperation(operation);
        operationLogEntity.setHandler(joinpoint.getSignature().toLongString());
        StringBuffer requestUrl = httpServletRequest.getRequestURL();
        if (requestUrl != null) {
            operationLogEntity.setRequestUrl(requestUrl.toString());
        }
        operationLogEntity.setRequestMethod(httpServletRequest.getMethod());
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        Map<String, Object> map = new HashMap<>(6);
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            Object parameter = httpServletRequest.getParameter(name);
            map.put(name, parameter);
        }
        operationLogEntity.setRequestParameter(common.objectToJson(map));
        operationLogEntity.setAcceptLanguage(httpServletRequest.getHeader("Accept-Language"));
        operationLogEntity.setReferer(httpServletRequest.getHeader("Referer"));
        operationLogEntity.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession != null) {
            operationLogEntity.setSessionId(httpSession.getId());
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        map = new HashMap<>(4);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                map.put(cookie.getName(), cookie.getValue());
            }
        }
        operationLogEntity.setCookie(common.objectToJson(map));
        operationLogEntity.setStatus(String.valueOf(httpServletResponse.getStatus()));
        operationLogEntity.setGmtCreated(new Date());
        operationLogEntity.setConsumingTime(Instant.now().toEpochMilli() - beginTime);
    }

    @AfterReturning(pointcut = "operationLogAspect()", returning = "returning")
    public void afterReturning(Object returning) {
        LOGGER.info("returning={}", returning);
        applicationContext.publishEvent(new OperationLogEvent(operationLogEntity));
    }

    @AfterThrowing(pointcut = "operationLogAspect()", throwing = "throwing")
    public void afterThrowing(Throwable throwing) {
        StringWriter stringWriter = new StringWriter();
        try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
            throwing.printStackTrace(printWriter);
        }
        operationLogEntity.setStackTrace(stringWriter.toString());
        applicationContext.publishEvent(new OperationLogEvent(operationLogEntity));
    }
}
