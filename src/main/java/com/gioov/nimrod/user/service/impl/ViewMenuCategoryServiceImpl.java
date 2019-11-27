package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.common.others.FailureEntity;
import com.gioov.nimrod.common.easyui.ComboTree;
import com.gioov.nimrod.common.easyui.EasyUI;
import com.gioov.nimrod.system.service.DictionaryService;
import com.gioov.nimrod.user.entity.*;
import com.gioov.nimrod.user.mapper.RoleViewMenuCategoryMapper;
import com.gioov.nimrod.user.mapper.UserRoleMapper;
import com.gioov.nimrod.user.mapper.ViewMenuCategoryMapper;
import com.gioov.nimrod.user.mapper.ViewMenuMapper;
import com.gioov.nimrod.user.service.RoleService;
import com.gioov.nimrod.user.service.ViewMenuCategoryService;
import com.gioov.nimrod.user.service.ViewMenuService;
import com.gioov.tile.web.exception.BaseResponseException;
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
    @Autowired
    private FailureEntity failureEntity;
    @Autowired
    private RoleViewMenuCategoryMapper roleViewMenuCategoryMapper;
    @Autowired
    private DictionaryService dictionaryService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ViewMenuCategoryEntity addOne(ViewMenuCategoryEntity viewMenuCategoryEntity) {
        Date date = new Date();
        viewMenuCategoryEntity.setGmtModified(date);
        viewMenuCategoryEntity.setGmtCreated(date);
        viewMenuCategoryMapper.insertOne(viewMenuCategoryEntity);
        return viewMenuCategoryEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ViewMenuCategoryEntity saveOne(ViewMenuCategoryEntity viewMenuCategoryEntity) {
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
    public int deleteAll(List<Long> idList) throws BaseResponseException {
        int result = 0;
        for (Long id : idList) {
            // 有子视图菜单分类报错
            ViewMenuCategoryEntity viewMenuCategoryEntity = viewMenuCategoryMapper.getOneByParentId(id);
            if (viewMenuCategoryEntity != null) {
                throw new BaseResponseException(failureEntity.i18n("view_menu_category.delete_fail_has_children_category"));
            }
            // 有子视图菜单报错
            ViewMenuEntity viewMenuEntity = viewMenuMapper.getOneByViewMenuCategoryId(id);
            if (viewMenuEntity != null) {
                throw new BaseResponseException(failureEntity.i18n("view_menu_category.delete_fail_has_view_menu"));
            }
            roleViewMenuCategoryMapper.deleteAllByViewMenuCategoryId(id);
            viewMenuCategoryMapper.deleteOne(id);
            result++;
        }
        return result;
    }

    @Override
    public ViewMenuCategoryEntity getOne(Long id) {
        return viewMenuCategoryMapper.getOne(id);
    }

    /**
     * 指定 用户id，获取所有视图菜单父级分类
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
     * 指定用户id、视图菜单父级分类，获取所有视图菜单子级分类
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
                    // 根据父级视图菜单分类和角色 id，获取每个角色所拥有的视图菜单子级分类
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
    public List<ViewMenuCategoryEntity> listAllByParentId(Long parentId, Long roleId) {
        List<ViewMenuCategoryEntity> viewPageCategoryEntityList = viewMenuCategoryMapper.listAllByParentId(parentId);
        List<ViewMenuCategoryEntity> viewMenuCategoryEntityListResult = new ArrayList<>();
        Integer isOrNotIs = Integer.valueOf((String) dictionaryService.get("IS_OR_NOT", "IS"));
        Integer isOrNotNot = Integer.valueOf((String) dictionaryService.get("IS_OR_NOT", "NOT"));
        if(!viewPageCategoryEntityList.isEmpty()) {
            for (ViewMenuCategoryEntity viewMenuCategoryEntity : viewPageCategoryEntityList) {
                if (viewMenuCategoryMapper.getOneByParentId(viewMenuCategoryEntity.getId()) != null) {
                    viewMenuCategoryEntity.setState(EasyUI.State.CLOSED);
                }
                if (roleId != null) {
                    if (roleViewMenuCategoryMapper.getOneByRoleIdAndViewMenuCategoryId(roleId, viewMenuCategoryEntity.getId()) != null) {
                        viewMenuCategoryEntity.setIsGranted(isOrNotIs);
                    } else {
                        viewMenuCategoryEntity.setIsGranted(isOrNotNot);
                    }
                }
                viewMenuCategoryEntityListResult.add(viewMenuCategoryEntity);
            }
        }
        return viewMenuCategoryEntityListResult;
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
    public List<ViewMenuCategoryEntity> listAllParent(Long roleId) {
        List<ViewMenuCategoryEntity> viewMenuCategoryEntityList = viewMenuCategoryMapper.listAllByParentIdIsNull();
        List<ViewMenuCategoryEntity> viewMenuCategoryEntityListResult = new ArrayList<>();
        Integer isOrNotIs = Integer.valueOf((String) dictionaryService.get("IS_OR_NOT","IS"));
        Integer isOrNotNot = Integer.valueOf((String) dictionaryService.get("IS_OR_NOT","NOT"));
        for(ViewMenuCategoryEntity viewMenuCategoryEntity : viewMenuCategoryEntityList) {
            if(viewMenuCategoryMapper.getOneByParentId(viewMenuCategoryEntity.getId()) != null) {
                viewMenuCategoryEntity.setState(EasyUI.State.CLOSED);
            }
            if(roleId != null) {
                if(roleViewMenuCategoryMapper.getOneByRoleIdAndViewMenuCategoryId(roleId,viewMenuCategoryEntity.getId()) != null) {
                    viewMenuCategoryEntity.setIsGranted(isOrNotIs);
                } else {
                    viewMenuCategoryEntity.setIsGranted(isOrNotNot);
                }
            }
            viewMenuCategoryEntityListResult.add(viewMenuCategoryEntity);
        }
        return viewMenuCategoryEntityListResult;
    }

    @Override
    public List<ComboTree> listAllViewMenuCategoryComboTree() {
        List<ComboTree> comboTreeList = new ArrayList<>(0);
        List<ViewMenuCategoryEntity> viewMenuCategoryEntityList = viewMenuCategoryMapper.listAll();
        for(ViewMenuCategoryEntity viewMenuCategoryEntity : viewMenuCategoryEntityList) {
            ComboTree comboTree = new ComboTree();
            comboTree.setId(viewMenuCategoryEntity.getId());
            comboTree.setText(viewMenuCategoryEntity.getName());
            comboTree.setParentId(viewMenuCategoryEntity.getParentId());
            comboTreeList.add(comboTree);
        }
        return comboTreeList;
    }
    @Override
    public List<ComboTree> getViewMenuCategoryChildrenComboTree(long parentId, List<ComboTree> viewMenuCategoryComboTreeList) {
        List<ComboTree> children = new ArrayList<>(0);
        for(ComboTree comboTree : viewMenuCategoryComboTreeList) {
            if(comboTree.getParentId() != null && comboTree.getParentId().equals(parentId)) {
                children.add(comboTree);
            }
        }
        for(ComboTree child : children) {
            List<ComboTree> childChildren = getViewMenuCategoryChildrenComboTree(child.getId(), viewMenuCategoryComboTreeList);
            child.setChildren(childChildren);
        }
        if(children.size() == 0) {
            return null;
        }
        return children;
    }
}
