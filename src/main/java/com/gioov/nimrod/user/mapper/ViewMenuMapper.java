package com.gioov.nimrod.user.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.user.entity.ViewMenuEntity;
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
     * 指定视图菜单分类 id 、角色 id ，获取视图菜单
     *
     * @param menuCategoryId 视图菜单分类 id
     * @param roleId         角色 id
     * @return List<ViewMenuEntity>
     */
    List<ViewMenuEntity> listAllByMenuCategoryIdAndRoleId(@Param("menuCategoryId") Long menuCategoryId, @Param("roleId") Long roleId);

    /**
     * 指定视图菜单分类 id 、角色 id ，获取视图菜单
     *
     * @param menuCategoryId 视图菜单分类 id
     * @param roleId         角色 id
     * @return ViewMenuEntity
     */
    ViewMenuEntity getOneByMenuCategoryIdAndRoleId(@Param("menuCategoryId") Long menuCategoryId, @Param("roleId") Long roleId);

    /**
     * 指定分类 id 和角色 id 分页获取视图菜单
     *
     * @param menuCategoryId 视图菜单分类 id
     * @param roleId         角色 id
     * @param pageable       Pageable
     * @return List<ViewMenuEntity>
     */
    List<ViewMenuEntity> pageAllByMenuCategoryIdAndRoleId(@Param("menuCategoryId") Long menuCategoryId, @Param("roleId") Long roleId, @Param("pageable") Pageable pageable);

    /**
     * 指定视图菜单分类 id 、角色 id ，统计所有视图菜单
     *
     * @param menuCategoryId 视图菜单分类 id
     * @param roleId         角色 id
     * @return int
     */
    int countAllByMenuCategoryIdAndRoleId(@Param("menuCategoryId") Long menuCategoryId, @Param("roleId") Long roleId);

    /**
     * 指定视图菜单名，搜索获取所有视图菜单
     *
     * @param name 视图菜单名
     * @return List<ViewMenuEntity>
     */
    List<ViewMenuEntity> searchAllByName(@Param("name") String name);

}
