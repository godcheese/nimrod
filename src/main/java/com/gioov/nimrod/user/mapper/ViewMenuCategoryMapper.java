package com.gioov.nimrod.user.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.user.entity.ViewMenuCategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("viewMenuCategoryMapper")
@Mapper
public interface ViewMenuCategoryMapper extends CrudMapper<ViewMenuCategoryEntity, Long> {

    /**
     * 指定角色 id ，获取所有视图菜单分类
     *
     * @param roleId 角色 id
     * @return List<ViewMenuCategoryEntity>
     */
    List<ViewMenuCategoryEntity> listAllByParentIdIsNullAndRoleId(@Param("roleId") Long roleId);

    /**
     * 指定角色 id ，获取所有视图菜单分类
     *
     * @param roleId   角色 id
     * @param pageable Pageable
     * @return List<ViewMenuCategoryEntity>
     */
    List<ViewMenuCategoryEntity> pageAllByParentIdIsNullAndRoleId(@Param("roleId") Long roleId, @Param("pageable") Pageable pageable);

    /**
     * 指定角色 id ，统计父级 id 为 null 的视图菜单分类个数
     *
     * @param roleId 角色 id
     * @return int
     */
    int countAllByParentIdIsNullAndRoleId(@Param("roleId") Long roleId);

    /**
     * 指定角色 id 、视图菜单分类父级 id ，获取所有视图菜单分类
     *
     * @param roleId   角色 id
     * @param parentId 视图菜单分类父级 id
     * @return List<ViewMenuCategoryEntity>
     */
    List<ViewMenuCategoryEntity> listAllByParentIdAndRoleId(@Param("parentId") Long parentId, @Param("roleId") Long roleId);

    /**
     * 指定角色 id 、视图菜单分类父级 id ，获取视图菜单分类
     *
     * @param roleId   角色 id
     * @param parentId 视图菜单分类父级 id
     * @return ViewMenuCategoryEntity
     */
    ViewMenuCategoryEntity getOneByParentIdAndRoleId(@Param("parentId") Long parentId, @Param("roleId") Long roleId);

    /**
     * 指定视图菜单分类名，搜索获取所有视图菜单分类
     *
     * @param name 视图菜单分类名
     * @return List<ViewMenuCategoryEntity>
     */
    List<ViewMenuCategoryEntity> searchAllByName(@Param("name") String name);
}