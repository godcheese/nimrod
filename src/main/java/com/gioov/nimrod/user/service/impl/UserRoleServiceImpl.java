package com.gioov.nimrod.user.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.UserRoleEntity;
import com.gioov.nimrod.user.mapper.UserRoleMapper;
import com.gioov.nimrod.user.service.UserRoleService;
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
    public Pagination.Result<UserRoleEntity> pageAll(Integer page, Integer rows) {
        Pagination.Result<UserRoleEntity> paginationResult = new Pagination().new Result<>();
        List<UserRoleEntity> userRoleEntityList = userRoleMapper.pageAll(new Pageable(page, rows));
        if (userRoleEntityList != null) {
            paginationResult.setRows(userRoleEntityList);
        }
        paginationResult.setTotal(userRoleMapper.countAll());
        return paginationResult;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public UserRoleEntity insertOne(UserRoleEntity userRoleEntity) {
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

}
