package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.common.others.FailureEntity;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.RoleEntity;
import com.gioov.nimrod.user.entity.UserRoleEntity;
import com.gioov.nimrod.user.mapper.RoleAuthorityMapper;
import com.gioov.nimrod.user.mapper.RoleMapper;
import com.gioov.nimrod.user.mapper.UserRoleMapper;
import com.gioov.nimrod.user.service.RoleService;
import com.gioov.tile.web.exception.BaseResponseException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
    @Autowired
    private FailureEntity failureEntity;
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RoleEntity addOne(RoleEntity roleEntity) throws BaseResponseException {
        Date date = new Date();
        String value = roleEntity.getValue().toUpperCase();
        RoleEntity roleEntity2 = roleMapper.getOneByValue(value);
        if (roleEntity2 != null) {
            throw new BaseResponseException(failureEntity.i18n("role.add_fail_value_exists"));
        }
        roleEntity.setGmtModified(date);
        roleEntity.setGmtCreated(date);
        roleMapper.insertOne(roleEntity);
        return roleEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RoleEntity saveOne(RoleEntity roleEntity) throws BaseResponseException {
        String value = roleEntity.getValue().toUpperCase();
        RoleEntity roleEntity2 = roleMapper.getOneByValue(value);
        if (roleEntity2 != null && !roleEntity2.getId().equals(roleEntity.getId())) {
            throw new BaseResponseException(failureEntity.i18n("role.save_fail_value_exists"));
        }
        roleEntity.setGmtModified(new Date());
        roleMapper.updateOne(roleEntity);
        return roleEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) throws BaseResponseException {
        int result = 0;
        for (Long id : idList) {
            UserRoleEntity userRoleEntity = userRoleMapper.getOneByRoleId(id);
            if(userRoleEntity != null) {
                throw new BaseResponseException(failureEntity.i18n("role.delete_fail_has_user"));
            }
            roleMapper.deleteOne(id);
            roleAuthorityMapper.deleteAllByRoleId(id);
            result++;
        }
        return result;
    }

    @Override
    public RoleEntity getOne(Long id) {
        return roleMapper.getOne(id);
    }

    /**
     * 根据用户关联角色来获取所有角色
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
        PageHelper.startPage(page, rows);
        Page<RoleEntity> roleEntityPage = roleMapper.pageAll();
        pagination.setRows(roleEntityPage.getResult());
        pagination.setTotal(roleEntityPage.getTotal());
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

}
