package com.gioov.nimrod.system.service.impl;

import com.gioov.nimrod.common.others.Common;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.OperationLogEntity;
import com.gioov.nimrod.system.mapper.OperationLogMapper;
import com.gioov.nimrod.system.service.OperationLogService;
import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.nimrod.user.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Pagination<OperationLogEntity> pageAll(Integer page, Integer rows) {
        Pagination<OperationLogEntity> pagination = new Pagination<>();

//        if(sorterField != null && !"".equals(sorterField) && sorterOrder != null && !"".equals(sorterOrder)) {
//            sorterField = StringUtil.camelToUnderline(sorterField);
//            String orderBy = sorterField + " " + sorterOrder;
//            PageHelper.startPage(page, rows, orderBy);
//        } else {
        PageHelper.startPage(page, rows);
//        }
        Page<OperationLogEntity> operationLogEntityPage = operationLogMapper.pageAll();
        List<OperationLogEntity> operationLogEntityList = operationLogEntityPage.getResult();
        List<OperationLogEntity> operationLogEntityListResult = new ArrayList<>(1);
        for(OperationLogEntity operationLogEntity : operationLogEntityPage) {
            UserEntity userEntity = userService.getOne(operationLogEntity.getUserId());
            if(userEntity != null) {
                operationLogEntity.setUsername(userEntity.getUsername());
            }
            operationLogEntityListResult.add(operationLogEntity);
        }

        pagination.setRows(operationLogEntityListResult);
        pagination.setTotal(operationLogEntityPage.getTotal());
        return pagination;
    }

    @Override
    public OperationLogEntity addOne(OperationLogEntity operationLogEntity) {
        operationLogEntity.setGmtCreated(new Date());
        operationLogMapper.insertOne(operationLogEntity);
        return operationLogEntity;
    }

    @Override
    public int deleteAll(List<Long> idList) {
        return operationLogMapper.deleteAll(idList);
    }

    @Override
    public OperationLogEntity getOne(Long id) {
        OperationLogEntity operationLogEntity = operationLogMapper.getOne(id);
        UserEntity userEntity = userService.getOne(operationLogEntity.getUserId());
        if(userEntity != null) {
            operationLogEntity.setUsername(userEntity.getUsername());
        }
        return operationLogEntity;
    }

//    @Override
//    public void log(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, long requestTime) throws JsonProcessingException {
//        LOGGER.info("{},{}", httpServletRequest, httpServletResponse);
//        if (httpServletRequest != null && httpServletResponse != null) {
//            OperationLogEntity operationLogEntity = new OperationLogEntity();
//            UserEntity userEntity = userService.getCurrentUser();
//            if (userEntity != null) {
//                operationLogEntity.setUserId(userEntity.getId());
//            }
//            operationLogEntity.setIpAddress(ClientUtil.getClientIp(httpServletRequest));
//            String operation = "";
//            if (handler instanceof HandlerMethod) {
//                OperationLog operationLog = ((HandlerMethod) handler).getMethod().getAnnotation(OperationLog.class);
//                if (operationLog != null) {
//                    operationLogEntity.setOperationType(operationLog.type().value());
//                    operation = operationLog.value();
//                    if ("".equals(operation)) {
//                        operation = operationLog.operation();
//                    }
//                }
//            }
//            operationLogEntity.setOperation(operation);
//            operationLogEntity.setConsumingTime(requestTime);
//            StringBuffer requestUrl = httpServletRequest.getRequestURL();
//            if(requestUrl != null) {
//                operationLogEntity.setRequestUrl(requestUrl.toString());
//            }
//            operationLogEntity.setRequestMethod(httpServletRequest.getMethod());
//            Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
//            Map<String, Object> map = new HashMap<>(0);
//            while (parameterNames.hasMoreElements()) {
//                String name = parameterNames.nextElement();
//                Object parameter = httpServletRequest.getParameter(name);
//                map.put(name, parameter);
//            }
//            operationLogEntity.setRequestParameter(common.objectToJson(map));
//            operationLogEntity.setAcceptLanguage(httpServletRequest.getHeader("Accept-Language"));
//            operationLogEntity.setReferer(httpServletRequest.getHeader("Referer"));
//            operationLogEntity.setUserAgent(httpServletRequest.getHeader("User-Agent"));
//            operationLogEntity.setHandler(handler.toString());
//            HttpSession httpSession = httpServletRequest.getSession();
//            if (httpSession != null) {
//                operationLogEntity.setSessionId(httpSession.getId());
//            }
//            Cookie[] cookies = httpServletRequest.getCookies();
//            map = new HashMap<>(0);
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    map.put(cookie.getName(), cookie.getValue());
//                }
//            }
//            operationLogEntity.setCookie(common.objectToJson(map));
//            operationLogEntity.setStatus(String.valueOf(httpServletResponse.getStatus()));
//            operationLogEntity.setGmtCreated(new Date());
//        }
//    }

    @Override
    public void clearAll() {
        operationLogMapper.truncate();
    }

}