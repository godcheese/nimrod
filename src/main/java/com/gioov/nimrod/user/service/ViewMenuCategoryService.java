package com.gioov.nimrod.user.service;

import com.gioov.nimrod.common.easyui.ComboTree;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.ViewMenuCategoryEntity;
import com.gioov.tile.web.exception.BaseResponseException;

import java.util.List;
import java.util.Map;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ViewMenuCategoryService {

    /**
     * 新增视图菜单分类
     * @param viewMenuCategoryEntity ViewMenuCategoryEntity
     * @return ViewMenuCategoryEntity
     */
    ViewMenuCategoryEntity addOne(ViewMenuCategoryEntity viewMenuCategoryEntity);

    /**
     * 保存视图菜单分类
     * @param viewMenuCategoryEntity ViewMenuCategoryEntity
     * @return ViewMenuCategoryEntity
     */
    ViewMenuCategoryEntity saveOne(ViewMenuCategoryEntity viewMenuCategoryEntity);

    /**
     * 指定视图菜单分类 id list，批量删除视图菜单分类
     * @param idList 视图菜单分类 id list
     * @return int
     * @throws BaseResponseException BaseResponseException
     */
    int deleteAll(List<Long> idList) throws BaseResponseException;

    /**
     * 指定角色 id，获取角色
     * @param id 角色 id
     * @return ViewMenuCategoryEntity
     */
    ViewMenuCategoryEntity getOne(Long id);

    /**
     * 指定用户 id，获取视图菜单分类
     * @param userId 用户 id
     * @return List<ViewMenuCategoryEntity>
     */
    List<ViewMenuCategoryEntity> listAllParentByUserId(Long userId);

    /**
     * 指定父级视图菜单分类 id、用户 id，获取视图菜单分类
     * @param parentId 父级视图菜单分类 id
     * @param userId 用户 id
     * @return List<ViewMenuCategoryEntity>
     */
    List<ViewMenuCategoryEntity> listAllChildByParentIdAndUserId(Long parentId, Long userId);

    /**
     * 指定父级视图菜单分类 id、角色 id，获取所有视图菜单分类
     * @param parentId 父级视图菜单分类 id
     * @param roleId 角色 id
     * @return List<ViewMenuCategoryEntity>
     */
    List<ViewMenuCategoryEntity> listAllByParentIdAndRoleId(Long parentId, Long roleId);

    List<ViewMenuCategoryEntity> listAllByParentId(Long parentId, Long roleId);

    /**
     * 指定角色 id，获取所有父级视图菜单分类
     * @param roleId 角色 id
     * @return List<ViewMenuCategoryEntity>
     */
    List<ViewMenuCategoryEntity> listAllParentByRoleId(Long roleId);

    List<Map<String, Object>> listAllChildViewMenuCategoryAndViewMenuByParentIdAndUserId(Long parentId, Long userId);
    /**
     * 获取所有视图菜单分类
     * @return List<ViewMenuCategoryEntity>
     */
    List<ViewMenuCategoryEntity> listAll();

    /**
     * 指定视图菜单分类名称，模糊搜索获取所有视图菜单分类
     * @param name 视图菜单分类名称
     * @return List<ViewMenuCategoryEntity>
     */
    List<ViewMenuCategoryEntity> searchAllByName(String name);

    /**
     * 分页获取所有父级视图菜单分类
     * @return List<ViewMenuCategoryEntity>
     */
    List<ViewMenuCategoryEntity> listAllParent(Long roleId);

    /**
     * 获取所有视图菜单分类，以 ComboTree 形式展示
     * @return List<ComboTree>
     */
    List<ComboTree> listAllViewMenuCategoryComboTree();
    /**
     * 指定父级视图菜单分类 id，ViewMenuCategoryComboTree list，获取所有子级视图菜单分类
     * @param parentId 父级视图菜单分类 id
     * @param viewMenuCategoryComboTreeList ViewMenuCategoryComboTree list
     * @return List<ComboTree>
     */
    List<ComboTree> getViewMenuCategoryChildrenComboTree(long parentId, List<ComboTree> viewMenuCategoryComboTreeList);
}
