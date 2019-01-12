package com.gioov.nimrod.system.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gioov.common.mybatis.Pageable;
import com.gioov.common.mybatis.Sort;
import com.gioov.common.util.ClientUtil;
import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.system.entity.OperationLogEntity;
import com.gioov.nimrod.system.entity.OperationLogEntity2;
import com.gioov.nimrod.system.mapper.OperationLogMapper;
import com.gioov.nimrod.system.mapper.OperationLogMapper2;
import com.gioov.nimrod.system.service.OperationLogService;
import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.nimrod.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationLogServiceImpl.class);
    @Autowired
    private OperationLogMapper operationLogMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private Common common;

    @Autowired
    private OperationLogMapper2 operationLogMapper2;

    @Override
    public Pagination.Result<OperationLogEntity2> pageAll(int page, int rows, Sort sort) {
        List<OperationLogEntity2> operationLogEntity2List;
        Pagination.Result<OperationLogEntity2> paginationResult = new Pagination().new Result<>();
        operationLogEntity2List = operationLogMapper2.pageAll(new Pageable(page, rows, sort));
        if (operationLogEntity2List != null) {
            paginationResult.setRows(operationLogEntity2List);
        }
        int count = operationLogMapper.countAll();
        paginationResult.setTotal(count);
        return paginationResult;
    }

    @Override
    public OperationLogEntity insertOne(OperationLogEntity operationLogEntity) {
        OperationLogEntity operationLogEntity1 = new OperationLogEntity();
        operationLogEntity1.setUserId(operationLogEntity.getUserId());
        operationLogEntity1.setIpAddress(operationLogEntity.getIpAddress());
        operationLogEntity1.setOperationType(operationLogEntity.getOperationType());
        operationLogEntity1.setOperation(operationLogEntity.getOperation());
        operationLogEntity1.setRequestTime(operationLogEntity.getRequestTime());
        operationLogEntity1.setRequestUrl(operationLogEntity.getRequestUrl());
        operationLogEntity1.setRequestMethod(operationLogEntity.getRequestMethod());
        operationLogEntity1.setRequestParameter(operationLogEntity.getRequestParameter());
        operationLogEntity1.setAcceptLanguage(operationLogEntity.getAcceptLanguage());
        operationLogEntity1.setReferer(operationLogEntity.getReferer());
        operationLogEntity1.setUserAgent(operationLogEntity.getUserAgent());
        operationLogEntity1.setHandler(operationLogEntity.getHandler());
        operationLogEntity1.setSessionId(operationLogEntity.getSessionId());
        operationLogEntity1.setCookie(operationLogEntity.getCookie());
        operationLogEntity1.setContentType(operationLogEntity.getContentType());
        operationLogEntity1.setStatus(operationLogEntity.getStatus());
        operationLogEntity1.setGmtCreated(new Date());
        operationLogMapper.insertOne(operationLogEntity1);
        return operationLogEntity1;
    }

    @Override
    public int deleteAll(List<Long> idList) {
        return operationLogMapper.deleteAll(idList);
    }

    @Override
    public OperationLogEntity2 getOne(Long id) {
        return operationLogMapper2.getOne(id);
    }

    @Override
    public OperationLogEntity log(HttpServletRequest request, HttpServletResponse response, Object handler, long requestTime) throws JsonProcessingException {
        OperationLogEntity operationLogEntity = new OperationLogEntity();

        UserEntity userEntity = userService.getCurrentUser();
        if (userEntity != null) {
            operationLogEntity.setUserId(userEntity.getId());
        }
        operationLogEntity.setIpAddress(ClientUtil.getIpAddress(request));
        String operation = "";
        if (handler instanceof HandlerMethod) {
            OperationLog operationLog = ((HandlerMethod) handler).getMethod().getAnnotation(OperationLog.class);
            if (operationLog != null) {
                operationLogEntity.setOperationType(operationLog.type().value());
                operation = operationLog.value();
                if ("".equals(operation)) {
                    operation = operationLog.operation();
                }
            }
        }
        operationLogEntity.setOperation(operation);
        operationLogEntity.setRequestTime(requestTime);
        operationLogEntity.setRequestUrl(request.getRequestURL().toString());
        operationLogEntity.setRequestMethod(request.getMethod());

        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, Object> map = new HashMap<>(2);
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            Object parameter = request.getParameter(name);
            map.put(name, parameter);
        }
        operationLogEntity.setRequestParameter(common.objectToJson(map));

        operationLogEntity.setAcceptLanguage(request.getHeader("Accept-Language"));
        operationLogEntity.setReferer(request.getHeader("Referer"));
        operationLogEntity.setUserAgent(request.getHeader("User-Agent"));
        operationLogEntity.setHandler(handler.toString());
//        HttpSession httpSession = request.getSession();
//        if(httpSession!= null) {
//            operationLogEntity.setSessionId(httpSession.getId());
//        }
        Cookie[] cookies = request.getCookies();
        map = new HashMap<>(2);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                map.put(cookie.getName(), cookie.getValue());
            }
        }
        operationLogEntity.setCookie(common.objectToJson(map));
        operationLogEntity.setContentType(response.getContentType());
        operationLogEntity.setStatus(String.valueOf(response.getStatus()));
        operationLogEntity.setGmtCreated(new Date());
        return insertOne(operationLogEntity);
    }

    @Override
    public void truncate() {
        operationLogMapper.truncate();
    }

}