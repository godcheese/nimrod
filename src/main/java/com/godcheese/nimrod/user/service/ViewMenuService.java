package com.godcheese.nimrod.user.service;

import com.godcheese.nimrod.common.easyui.Pagination;
import com.godcheese.nimrod.user.entity.ViewMenuEntity;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ViewMenuService {

    /**
     * 新增视图菜单
     *
     * @param viewMenuEntity ViewMenuEntity
     * @return ViewMenuEntity
     */
    ViewMenuEntity addOne(ViewMenuEntity viewMenuEntity);

    /**
     * 保存视图菜单
     *
     * @param viewMenuEntity ViewMenuEntity
     * @return ViewMenuEntity
     */
    ViewMenuEntity saveOne(ViewMenuEntity viewMenuEntity);

    /**
     * 指定视图菜单 id list，批量删除视图菜单
     *
     * @param idList 视图菜单 id list
     * @return int
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定视图菜单 id，获取视图菜单
     *
     * @param id 视图菜单 id
     * @return ViewMenuEntity
     */
    ViewMenuEntity getOne(Long id);

    /**
     * 指定用户 id、视图菜单分类 id，获取所有视图菜单
     *
     * @param userId             用户 id
     * @param viewMenuCategoryId 视图菜单分类 id
     * @return List<ViewMenuEntity>
     */
    List<ViewMenuEntity> listAllByUserIdAndMenuCategoryId(Long userId, Long viewMenuCategoryId);

    /**
     * 指定视图菜单分类 id、角色 id，分页获取所有视图菜单
     *
     * @param page               页
     * @param rows               每页显示数量
     * @param viewMenuCategoryId 视图菜单分类 id
     * @return Pagination<ViewMenuEntity>
     */
    Pagination<ViewMenuEntity> pageAllByViewMenuCategoryId(Integer page, Integer rows, Long viewMenuCategoryId, Long roleId);

//    /**
//     * 指定视图菜单名称，模糊搜索获取所有视图菜单
//     * @param name 视图菜单名称
//     * @return List<ViewMenuEntity>
//     */
//    List<ViewMenuEntity> searchAllByName(String name);

//    /**
//     * 指定用户 id，获取用户的树形视图菜单
//     * @param userId
//     * @return
//     */
//    List<AntdVueMenu> listAllAsAntdVueMenuByUserId(Long userId);

}
