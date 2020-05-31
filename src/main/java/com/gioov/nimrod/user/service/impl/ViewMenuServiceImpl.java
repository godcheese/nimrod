package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.service.DictionaryService;
import com.gioov.nimrod.user.entity.RoleEntity;
import com.gioov.nimrod.user.entity.UserRoleEntity;
import com.gioov.nimrod.user.entity.ViewMenuEntity;
import com.gioov.nimrod.user.mapper.RoleViewMenuMapper;
import com.gioov.nimrod.user.mapper.UserRoleMapper;
import com.gioov.nimrod.user.mapper.ViewMenuMapper;
import com.gioov.nimrod.user.service.RoleService;
import com.gioov.nimrod.user.service.ViewMenuService;
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
public class ViewMenuServiceImpl implements ViewMenuService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private ViewMenuMapper viewMenuMapper;

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleViewMenuMapper roleViewMenuMapper;
    @Autowired
    private DictionaryService dictionaryService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ViewMenuEntity addOne(ViewMenuEntity viewMenuEntity) {
        Date date = new Date();
        viewMenuEntity.setGmtModified(date);
        viewMenuEntity.setGmtCreated(date);
        viewMenuMapper.insertOne(viewMenuEntity);
        return viewMenuEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ViewMenuEntity saveOne(ViewMenuEntity viewMenuEntity) {
        ViewMenuEntity viewMenuEntity1 = viewMenuMapper.getOne(viewMenuEntity.getId());
        viewMenuEntity1.setName(viewMenuEntity.getName());
        viewMenuEntity1.setIcon(viewMenuEntity.getIcon());
        viewMenuEntity1.setUrl(viewMenuEntity.getUrl());
        viewMenuEntity1.setViewMenuCategoryId(viewMenuEntity.getViewMenuCategoryId());
        viewMenuEntity1.setSort(viewMenuEntity.getSort());
        viewMenuEntity1.setRemark(viewMenuEntity.getRemark());
        viewMenuEntity1.setGmtModified(new Date());
        viewMenuMapper.updateOne(viewMenuEntity1);
        return viewMenuEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) {
        roleViewMenuMapper.deleteAllByViewMenuIdList(idList);
        return viewMenuMapper.deleteAll(idList);
    }

    @Override
    public ViewMenuEntity getOne(Long id) {
        return viewMenuMapper.getOne(id);
    }

    /**
     * 指定用户 id、视图菜单分类 id 获取视图菜单
     *
     * @param userId             用户 id
     * @param viewMenuCategoryId 视图菜单分类 id
     * @return
     */
    @Override
    public List<ViewMenuEntity> listAllByUserIdAndMenuCategoryId(Long userId, Long viewMenuCategoryId) {
        List<ViewMenuEntity> viewMenuCategoryEntityList = null;
        List<UserRoleEntity> userRoleEntityList;
        if ((userRoleEntityList = userRoleMapper.listAllByUserId(userId)) != null) {
            List<RoleEntity> roleEntityList;
            if ((roleEntityList = roleService.listAllByUserRoleList(userRoleEntityList)) != null) {
                viewMenuCategoryEntityList = new ArrayList<>();
                for (RoleEntity roleEntity : roleEntityList) {
                    viewMenuCategoryEntityList.addAll(viewMenuMapper.listAllByViewMenuCategoryIdAndRoleId(viewMenuCategoryId, roleEntity.getId()));
                }
            }
        }
        return viewMenuCategoryEntityList;
    }

    @Override
    public Pagination<ViewMenuEntity> pageAllByViewMenuCategoryId(Integer page, Integer rows, Long viewMenuCategoryId, Long roleId) {
        Pagination<ViewMenuEntity> pagination = new Pagination<>();
        PageHelper.startPage(page, rows);
        Page<ViewMenuEntity> viewMenuEntityPage = viewMenuMapper.pageAllByViewMenuCategoryId(viewMenuCategoryId);
        List<ViewMenuEntity> viewMenuEntityListResult = new ArrayList<>();
        List<ViewMenuEntity> viewMenuEntityList = viewMenuEntityPage.getResult();
        Integer isOrNotIs = Integer.valueOf((String) dictionaryService.get("IS_OR_NOT", "IS"));
        Integer isOrNotNot = Integer.valueOf((String) dictionaryService.get("IS_OR_NOT", "NOT"));
        if (!viewMenuEntityList.isEmpty()) {
            for (ViewMenuEntity viewMenuEntity : viewMenuEntityList) {
                if (roleId != null) {
                    if (roleViewMenuMapper.getOneByRoleIdAndViewMenuId(roleId, viewMenuEntity.getId()) != null) {
                        viewMenuEntity.setIsGranted(isOrNotIs);
                    } else {
                        viewMenuEntity.setIsGranted(isOrNotNot);
                    }
                }
                viewMenuEntityListResult.add(viewMenuEntity);
            }
        }
        pagination.setRows(viewMenuEntityListResult);
        pagination.setTotal(viewMenuEntityPage.getTotal());
        return pagination;
    }


//    @Override
//    public List<AntdVueMenu> listAllAsAntdVueMenuByUserId(Long userId) {
//        List<AntdVueMenu> vueMenuList = new ArrayList<>(0);
//        List<AntdVueMenu> vueMenuListResult = new ArrayList<>(0);
//        List<ViewMenuCategoryEntity> viewMenuCategoryEntityList = null;
//        List<UserRoleEntity> userRoleEntityList;
//        List<Long> roleIdList = new ArrayList<>(1);
//        if ((userRoleEntityList = userRoleMapper.listAllByUserId(userId)) != null) {
//            List<RoleEntity> roleEntityList;
//            if ((roleEntityList = roleService.listAllByUserRoleList(userRoleEntityList)) != null) {
//                for (RoleEntity roleEntity : roleEntityList) {
//                    roleIdList.add(roleEntity.getId());
//                }
//            }
//        }
//        viewMenuCategoryEntityList = viewMenuCategoryMapper.listAllByParentIdIsNullAndRoleIdList(roleIdList);
//        if(viewMenuCategoryEntityList != null) {
//            for (ViewMenuCategoryEntity viewMenuCategoryEntity : viewMenuCategoryEntityList) {
//                forEachViewMenuAndViewMenuCategoryByViewMenuCategoryId(viewMenuCategoryEntity.getId(), roleIdList, vueMenuList);
//                AntdVueMenu vueMenu = new AntdVueMenu();
//                vueMenu.setId(viewMenuCategoryEntity.getId());
//                vueMenu.setName(viewMenuCategoryEntity.getName());
//                vueMenu.setIcon(viewMenuCategoryEntity.getIcon());
//                vueMenu.setParentId(viewMenuCategoryEntity.getParentId());
//                vueMenu.setIsCategory(true);
//                vueMenuList.add(vueMenu);
//            }
//        }
//        for(AntdVueMenu vueMenu : vueMenuList) {
//            if(vueMenu.getParentId() == null) {
//                vueMenuListResult.add(vueMenu);
//            }
//        }
//        for(AntdVueMenu vueMenu : vueMenuListResult) {
//            vueMenu.setChildren(forEachVueMenuByVueMenuParentId(vueMenu.getId(), vueMenuList));
//        }
//        return vueMenuListResult;
//    }

}
