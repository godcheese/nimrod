package com.gioov.nimrod.user.mapper;

import com.gioov.nimrod.user.entity.ViewMenuEntity;
import com.gioov.tile.mybatis.CrudMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("viewMenuMapper")
@Mapper
public interface ViewMenuMapper extends CrudMapper<ViewMenuEntity, Long> {

    /**
     * 指定视图菜单分类 id、角色 id，获取视图菜单
     * @param viewMenuCategoryId 视图菜单分类 id
     * @param roleId         角色 id
     * @return List<ViewMenuEntity>
     */
    List<ViewMenuEntity> listAllByViewMenuCategoryIdAndRoleId(@Param("viewMenuCategoryId") Long viewMenuCategoryId, @Param("roleId") Long roleId);

    /**
     * 指定视图菜单分类 id、角色 id，获取视图菜单
     * @param viewMenuCategoryIdList 视图菜单分类 id list
     * @return List<ViewMenuEntity>
     */
    List<ViewMenuEntity> listAllByViewMenuCategoryIdList(@Param("viewMenuCategoryIdList") List<Long> viewMenuCategoryIdList);

    /**
     *  指定视图菜单分类 id list，分页获取所有视图菜单
     * @param viewMenuCategoryIdList 视图菜单分类 id list
     * @return Page<ViewMenuEntity>
     */
    Page<ViewMenuEntity> pageAllByViewMenuCategoryIdList(@Param("viewMenuCategoryIdList") List<Long> viewMenuCategoryIdList);

    /**
     * 指定视图菜单分类 id，获取视图菜单
     * @param viewMenuCategoryId 视图菜单分类 id
     * @return ViewMenuEntity
     */
    ViewMenuEntity getOneByViewMenuCategoryId(@Param("viewMenuCategoryId") Long viewMenuCategoryId);

    /**
     * 指定视图菜单分类 id、角色 id，分页获取所有视图菜单
     * @param viewMenuCategoryId 视图菜单分类 id
     * @param roleId 角色 id
     * @return List<ViewMenuEntity>
     */
    Page<ViewMenuEntity> pageAllByViewMenuCategoryIdAndRoleId(@Param("viewMenuCategoryId") Long viewMenuCategoryId, @Param("roleId") Long roleId);

    /**
     * 指定视图菜单分类 id、角色 id，分页获取所有视图菜单
     * @param viewMenuCategoryId 视图菜单分类 id
     * @return List<ViewMenuEntity>
     */
    Page<ViewMenuEntity> pageAllByViewMenuCategoryId(@Param("viewMenuCategoryId") Long viewMenuCategoryId);

    /**
     * 指定视图菜单名称，模糊搜索获取所有视图菜单
     * @param name 视图菜单名称
     * @return List<ViewMenuEntity>
     */
    List<ViewMenuEntity> searchAllByName(@Param("name") String name);

    /**
     * 指定视图菜单分类 id、角色 id list，获取视图菜单
     * @param viewMenuCategoryId 视图菜单分类 id
     * @param roleIdList 角色 id list
     * @return List<ViewMenuEntity>
     */
    List<ViewMenuEntity> listAllByViewMenuCategoryIdAndRoleIdList(@Param("viewMenuCategoryId") Long viewMenuCategoryId, @Param("roleIdList") List<Long> roleIdList);

}
