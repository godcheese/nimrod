package com.gioov.nimrod.user.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.FailureMessage;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.RoleEntity;
import com.gioov.nimrod.user.entity.UserRoleEntity;
import com.gioov.nimrod.user.entity.ViewMenuCategoryEntity;
import com.gioov.nimrod.user.entity.ViewMenuEntity;
import com.gioov.nimrod.user.mapper.UserRoleMapper;
import com.gioov.nimrod.user.mapper.ViewMenuCategoryMapper;
import com.gioov.nimrod.user.mapper.ViewMenuMapper;
import com.gioov.nimrod.user.service.RoleService;
import com.gioov.nimrod.user.service.ViewMenuCategoryService;
import com.gioov.nimrod.user.service.ViewMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class ViewMenuCategoryServiceImpl implements ViewMenuCategoryService {

    @Autowired
    private ViewMenuCategoryMapper viewMenuCategoryMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ViewMenuMapper viewMenuMapper;

    @Autowired
    private ViewMenuCategoryService viewMenuCategoryService;

    @Autowired
    private ViewMenuService viewMenuService;

    /**
     * 指定 用户id，获取所有视图菜单父级分类
     *
     * @param userId
     * @return
     */
    @Override
    public List<ViewMenuCategoryEntity> listAllParentByUserId(Long userId) {
        List<ViewMenuCategoryEntity> viewMenuCategoryEntityList = null;
        List<UserRoleEntity> userRoleEntityList;
        if ((userRoleEntityList = userRoleMapper.listAllByUserId(userId)) != null) {
            List<RoleEntity> roleEntityList;
            if ((roleEntityList = roleService.listAllByUserRoleList(userRoleEntityList)) != null) {
                viewMenuCategoryEntityList = new ArrayList<>();
                for (RoleEntity roleEntity : roleEntityList) {
                    viewMenuCategoryEntityList.addAll(viewMenuCategoryMapper.listAllByParentIdIsNullAndRoleId(roleEntity.getId()));
                }
            }
        }
        return viewMenuCategoryEntityList;
    }

    /**
     * 指定用户id 、视图菜单父级分类，获取所有视图菜单子级分类
     *
     * @param userId
     * @param parentId
     * @return
     */
    @Override
    public List<ViewMenuCategoryEntity> listAllChildByParentIdAndUserId(Long parentId, Long userId) {
        List<ViewMenuCategoryEntity> viewMenuCategoryEntityList = null;
        List<UserRoleEntity> userRoleEntityList;
        if ((userRoleEntityList = userRoleMapper.listAllByUserId(userId)) != null) {
            List<RoleEntity> roleEntityList;
            if ((roleEntityList = roleService.listAllByUserRoleList(userRoleEntityList)) != null) {
                viewMenuCategoryEntityList = new ArrayList<>();
                for (RoleEntity roleEntity : roleEntityList) {
                    // 根据父级视图菜单分类和角色 id ，获取每个角色所拥有的视图菜单子级分类
                    viewMenuCategoryEntityList.addAll(viewMenuCategoryMapper.listAllByParentIdAndRoleId(parentId, roleEntity.getId()));
                }
            }
        }
        return viewMenuCategoryEntityList;
    }

    @Override
    public List<ViewMenuCategoryEntity> listAllByParentIdAndRoleId(Long parentId, Long roleId) {
        return viewMenuCategoryMapper.listAllByParentIdAndRoleId(parentId, roleId);
    }

    @Override
    public List<ViewMenuCategoryEntity> listAllParentByRoleId(Long roleId) {
        return viewMenuCategoryMapper.listAllByParentIdIsNullAndRoleId(roleId);
    }

    @Override
    public List<Map<String, Object>> listAllChildViewMenuCategoryAndViewMenuByParentIdAndUserId(Long parentId, Long userId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<ViewMenuCategoryEntity> viewMenuCategoryEntityList = null;
        viewMenuCategoryEntityList = viewMenuCategoryService.listAllChildByParentIdAndUserId(parentId, userId);
        if (viewMenuCategoryEntityList != null) {
            for (ViewMenuCategoryEntity viewMenuCategoryEntity : viewMenuCategoryEntityList) {
                Map<String, Object> map = new HashMap<>(3);
                map.put("id", viewMenuCategoryEntity.getId());
                map.put("name", viewMenuCategoryEntity.getName());
                map.put("icon", viewMenuCategoryEntity.getIcon());
                mapList.add(map);
            }
            List<ViewMenuEntity> viewMenuEntityList = null;
            viewMenuEntityList = viewMenuService.listAllByUserIdAndMenuCategoryId(userId, parentId);
            if (viewMenuEntityList != null) {
                for (ViewMenuEntity viewMenuEntity : viewMenuEntityList) {
                    Map<String, Object> map = new HashMap<>(4);
                    map.put("id", viewMenuEntity.getId());
                    map.put("name", viewMenuEntity.getName());
                    map.put("icon", viewMenuEntity.getIcon());
                    map.put("url", viewMenuEntity.getUrl());
                    mapList.add(map);
                }
            }
        }
        return mapList;
    }

    @Override
    public List<ViewMenuCategoryEntity> listAll() {
        return viewMenuCategoryMapper.listAll();
    }

    @Override
    public List<ViewMenuCategoryEntity> searchAllByName(String name) {
        return viewMenuCategoryMapper.searchAllByName(name);
    }

    @Override
    public Pagination<ViewMenuCategoryEntity> pageAllParent(Long roleId, Integer page, Integer rows) {
        Pagination<ViewMenuCategoryEntity> pagination = new Pagination<>();
        List<ViewMenuCategoryEntity> viewMenuCategoryEntityList = viewMenuCategoryMapper.pageAllByParentIdIsNullAndRoleId(roleId, new Pageable(page, rows));
        if (viewMenuCategoryEntityList != null) {
            pagination.setRows(viewMenuCategoryEntityList);
        }
        pagination.setTotal(viewMenuCategoryMapper.countAllByParentIdIsNullAndRoleId(roleId));
        return pagination;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ViewMenuCategoryEntity insertOne(ViewMenuCategoryEntity viewMenuCategoryEntity) {
        Date date = new Date();
        viewMenuCategoryEntity.setGmtModified(date);
        viewMenuCategoryEntity.setGmtCreated(date);
        viewMenuCategoryMapper.insertOne(viewMenuCategoryEntity);
        return viewMenuCategoryEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ViewMenuCategoryEntity updateOne(ViewMenuCategoryEntity viewMenuCategoryEntity) {
        ViewMenuCategoryEntity viewMenuCategoryEntity1 = viewMenuCategoryMapper.getOne(viewMenuCategoryEntity.getId());
        Date date = new Date();
        viewMenuCategoryEntity1.setName(viewMenuCategoryEntity.getName());
        viewMenuCategoryEntity1.setIcon(viewMenuCategoryEntity.getIcon());
        viewMenuCategoryEntity1.setSort(viewMenuCategoryEntity.getSort());
        viewMenuCategoryEntity1.setRemark(viewMenuCategoryEntity.getRemark());
        viewMenuCategoryEntity1.setGmtModified(date);
        viewMenuCategoryMapper.updateOne(viewMenuCategoryEntity1);
        return viewMenuCategoryEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList, Long roleId) throws BaseResponseException {
        int result = 0;
        for (Long id : idList) {
            // 有子视图菜单分类报错
            ViewMenuCategoryEntity viewMenuCategoryEntity = viewMenuCategoryMapper.getOneByParentIdAndRoleId(id, roleId);
            if (viewMenuCategoryEntity != null) {
                throw new BaseResponseException(FailureMessage.DELETE_VIEW_MENU_CATEGORY_FAIL1);
            }
            // 有子视图菜单报错
            ViewMenuEntity viewMenuEntity = viewMenuMapper.getOneByMenuCategoryIdAndRoleId(id, roleId);
            if (viewMenuEntity != null) {
                throw new BaseResponseException(FailureMessage.DELETE_VIEW_MENU_CATEGORY_FAIL2);
            }
            viewMenuCategoryMapper.deleteOne(id);
            result++;
        }
        return result;
    }

    @Override
    public ViewMenuCategoryEntity getOne(Long id) {
        return viewMenuCategoryMapper.getOne(id);
    }
}
