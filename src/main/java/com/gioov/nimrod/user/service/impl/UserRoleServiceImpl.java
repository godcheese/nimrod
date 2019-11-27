package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.UserRoleEntity;
import com.gioov.nimrod.user.mapper.UserRoleMapper;
import com.gioov.nimrod.user.service.UserRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public UserRoleEntity addOne(UserRoleEntity userRoleEntity) {
        UserRoleEntity userRoleEntity1 = userRoleMapper.getOneByUserIdAndRoleId(userRoleEntity.getUserId(), userRoleEntity.getRoleId());
        if (userRoleEntity1 == null) {
            userRoleMapper.insertOne(userRoleEntity);
        }
        return userRoleEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAllByUserIdAndRoleIdList(Long userId, List<Long> roleIdList) {
        return userRoleMapper.deleteAllByUserIdAndRoleIdList(userId, roleIdList);
    }

    @Override
    public Pagination<UserRoleEntity> pageAll(Integer page, Integer rows) {
        Pagination<UserRoleEntity> pagination = new Pagination<>();
        PageHelper.startPage(page, rows);
        Page<UserRoleEntity> userRoleEntityPage = userRoleMapper.pageAll();
        pagination.setRows(userRoleEntityPage.getResult());
        pagination.setTotal(userRoleEntityPage.getTotal());
        return pagination;
    }

    @Override
    public List<UserRoleEntity> listAllByUserId(Long userId) {
        return userRoleMapper.listAllByUserId(userId);
    }
}
