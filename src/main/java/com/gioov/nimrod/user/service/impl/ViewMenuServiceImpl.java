package com.gioov.nimrod.user.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.RoleEntity;
import com.gioov.nimrod.user.entity.UserRoleEntity;
import com.gioov.nimrod.user.entity.ViewMenuEntity;
import com.gioov.nimrod.user.mapper.UserRoleMapper;
import com.gioov.nimrod.user.mapper.ViewMenuMapper;
import com.gioov.nimrod.user.service.RoleService;
import com.gioov.nimrod.user.service.ViewMenuService;
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
public class ViewMenuServiceImpl implements ViewMenuService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private ViewMenuMapper viewMenuMapper;

    @Autowired
    private RoleService roleService;

    /**
     * 指定用户 id 、视图菜单分类 id 获取视图菜单
     *
     * @param userId         用户 id
     * @param menuCategoryId 视图菜单分类 id
     * @return
     */
    @Override
    public List<ViewMenuEntity> listAllByUserIdAndMenuCategoryId(Long userId, Long menuCategoryId) {
        List<ViewMenuEntity> viewMenuCategoryEntityList = null;
        List<UserRoleEntity> userRoleEntityList;
        if ((userRoleEntityList = userRoleMapper.listAllByUserId(userId)) != null) {
            List<RoleEntity> roleEntityList;
            if ((roleEntityList = roleService.listAllByUserRoleList(userRoleEntityList)) != null) {
                viewMenuCategoryEntityList = new ArrayList<>();
                for (RoleEntity roleEntity : roleEntityList) {
                    viewMenuCategoryEntityList.addAll(viewMenuMapper.listAllByMenuCategoryIdAndRoleId(menuCategoryId, roleEntity.getId()));
                }
            }
        }
        return viewMenuCategoryEntityList;
    }

    @Override
    public Pagination.Result<ViewMenuEntity> pageAllByMenuCategoryIdAndRoleId(Long menuCategoryId, Long roleId, Integer page, Integer rows) {
        Pagination.Result<ViewMenuEntity> paginationResult = new Pagination().new Result<>();
        List<ViewMenuEntity> viewMenuEntityList = viewMenuMapper.pageAllByMenuCategoryIdAndRoleId(menuCategoryId, roleId, new Pageable(page, rows));
        if (viewMenuEntityList != null) {
            paginationResult.setRows(viewMenuEntityList);
        }
        paginationResult.setTotal(viewMenuMapper.countAllByMenuCategoryIdAndRoleId(menuCategoryId, roleId));
        return paginationResult;
    }

    @Override
    public List<ViewMenuEntity> searchAllByName(String name) {
        return viewMenuMapper.searchAllByName(name);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ViewMenuEntity insertOne(ViewMenuEntity viewMenuEntity) {
        Date date = new Date();
        viewMenuEntity.setGmtModified(date);
        viewMenuEntity.setGmtCreated(date);
        viewMenuMapper.insertOne(viewMenuEntity);
        return viewMenuEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ViewMenuEntity updateOne(ViewMenuEntity viewMenuEntity) {
        ViewMenuEntity viewMenuEntity1 = viewMenuMapper.getOne(viewMenuEntity.getId());
        Date date = new Date();
        viewMenuEntity.setName(viewMenuEntity.getName());
        viewMenuEntity.setIcon(viewMenuEntity.getIcon());
        viewMenuEntity.setUrl(viewMenuEntity.getUrl());
        viewMenuEntity.setSort(viewMenuEntity.getSort());
        viewMenuEntity.setRemark(viewMenuEntity.getRemark());
        viewMenuEntity.setGmtModified(date);
        viewMenuMapper.updateOne(viewMenuEntity);
        return viewMenuEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) {
        return viewMenuMapper.deleteAll(idList);
    }

    @Override
    public ViewMenuEntity getOne(Long id) {
        return viewMenuMapper.getOne(id);
    }

}
