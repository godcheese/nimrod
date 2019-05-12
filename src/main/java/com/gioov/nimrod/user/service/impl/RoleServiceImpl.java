package com.gioov.nimrod.user.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.FailureMessage;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.RoleEntity;
import com.gioov.nimrod.user.entity.UserRoleEntity;
import com.gioov.nimrod.user.mapper.RoleMapper;
import com.gioov.nimrod.user.mapper.UserRoleMapper;
import com.gioov.nimrod.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 根据用户关联角色来获取所有角色
     *
     * @param userRoleEntityList 用户角色 list
     * @return List<RoleEntity>
     */
    @Override
    public List<RoleEntity> listAllByUserRoleList(List<UserRoleEntity> userRoleEntityList) {
        List<RoleEntity> roleEntityList = null;
        if (userRoleEntityList != null && !userRoleEntityList.isEmpty()) {
            roleEntityList = new ArrayList<>();
            for (UserRoleEntity userRoleEntity : userRoleEntityList) {
                RoleEntity roleEntity = roleMapper.getOne(userRoleEntity.getRoleId());
                roleEntityList.add(roleEntity);
            }
        }
        return roleEntityList;
    }

    @Override
    public Pagination<RoleEntity> pageAll(Integer page, Integer rows) {
        Pagination<RoleEntity> pagination = new Pagination<>();
        List<RoleEntity> roleEntityList = roleMapper.pageAll(new Pageable(page, rows));
        if (roleEntityList != null) {
            pagination.setRows(roleEntityList);
        }
        pagination.setTotal(roleMapper.countAll());
        return pagination;
    }

    @Override
    public List<RoleEntity> listAll() {
        return roleMapper.listAll();
    }

    @Override
    public List<RoleEntity> listAllByUserId(Long userId) {
        List<RoleEntity> roleEntityList = new ArrayList<>();
        List<UserRoleEntity> userRoleEntityList = userRoleMapper.listAllByUserId(userId);
        if (userRoleEntityList != null && !userRoleEntityList.isEmpty()) {
            for (UserRoleEntity userRoleEntity : userRoleEntityList) {
                RoleEntity roleEntity = roleMapper.getOne(userRoleEntity.getRoleId());
                roleEntityList.add(roleEntity);
            }
        }
        return roleEntityList;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RoleEntity insertOne(RoleEntity roleEntity) throws BaseResponseException {
        RoleEntity roleEntity1 = new RoleEntity();
        Date date = new Date();
        String value = roleEntity.getValue().toUpperCase();
        RoleEntity roleEntity3 = roleMapper.getOneByValue(value);
        if (roleEntity3 != null) {
            throw new BaseResponseException(FailureMessage.ADD_ROLE_VALUE_FAIL);
        }
        roleEntity1.setName(roleEntity.getName());
        roleEntity1.setValue(value);
        roleEntity1.setRemark(roleEntity.getRemark());
        roleEntity1.setGmtModified(date);
        roleEntity1.setGmtCreated(date);
        roleMapper.insertOne(roleEntity1);
        return roleEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RoleEntity updateOne(RoleEntity roleEntity) throws BaseResponseException {
        RoleEntity roleEntity1 = roleMapper.getOne(roleEntity.getId());
        Date date = new Date();
        String value = roleEntity.getValue().toUpperCase();
        RoleEntity roleEntity3 = roleMapper.getOneByValue(value);
        if (roleEntity3 != null && !roleEntity3.getId().equals(roleEntity.getId())) {
            throw new BaseResponseException(FailureMessage.ADD_ROLE_VALUE_FAIL);
        }
        roleEntity1.setName(roleEntity.getName());
        roleEntity1.setValue(value);
        roleEntity1.setRemark(roleEntity.getRemark());
        roleEntity1.setGmtModified(date);
        roleMapper.updateOne(roleEntity);
        return roleEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) {
        int result = 0;
        for (Long id : idList) {
            userRoleMapper.deleteAllByRoleId(id);
            roleMapper.deleteOne(id);
            result++;
        }
        return result;
    }

    @Override
    public RoleEntity getOne(Long id) {
        return roleMapper.getOne(id);
    }

}
