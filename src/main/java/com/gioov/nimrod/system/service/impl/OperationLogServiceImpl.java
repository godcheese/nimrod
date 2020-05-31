package com.gioov.nimrod.system.service.impl;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.others.Common;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        PageHelper.startPage(page, rows);
        Page<OperationLogEntity> operationLogEntityPage = operationLogMapper.pageAll();
        List<OperationLogEntity> operationLogEntityList = operationLogEntityPage.getResult();
        List<OperationLogEntity> operationLogEntityListResult = new ArrayList<>(1);
        for (OperationLogEntity operationLogEntity : operationLogEntityPage) {
            UserEntity userEntity = userService.getOne(operationLogEntity.getUserId());
            if (userEntity != null) {
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
        if (userEntity != null) {
            operationLogEntity.setUsername(userEntity.getUsername());
        }
        return operationLogEntity;
    }

    @Override
    public void clearAll() {
        operationLogMapper.truncate();
    }
}