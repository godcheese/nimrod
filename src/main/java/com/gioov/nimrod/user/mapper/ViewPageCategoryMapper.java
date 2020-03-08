package com.gioov.nimrod.user.mapper;

import com.gioov.nimrod.user.entity.ViewPageCategoryEntity;
import com.gioov.tile.mybatis.CrudMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("viewPageCategoryMapper")
@Mapper
public interface ViewPageCategoryMapper extends CrudMapper<ViewPageCategoryEntity, Long> {

    /**
     * 获取所有父级 id 为 null 的视图页面分类
     * @return List<ViewPageCategoryEntity>
     */
    List<ViewPageCategoryEntity> listAllByParentIdIsNull();

    /**
     * 指定父级视图页面分类 id，获取所有视图页面分类
     * @param parentId 父级视图页面分类 id
     * @return List<ViewPageCategoryEntity>
     */
    List<ViewPageCategoryEntity> listAllByParentId(@Param("parentId") Long parentId);

    /**
     * 指定父级视图页面分类 id，获取视图页面分类
     * @param parentId 父级视图页面分类 id
     * @return ViewPageCategoryEntity
     */
    ViewPageCategoryEntity getOneByParentId(@Param("parentId") Long parentId);

    /**
     * 指定视图页面 id，获取视图页面分类
     * @param viewPageId 视图页面 id
     * @return ViewPageCategoryEntity
     */
    ViewPageCategoryEntity getOneByViewPageId(@Param("viewPageId") Long viewPageId);

}
