package com.gioov.nimrod.user.mapper;

import com.gioov.nimrod.user.entity.ViewPageEntity;
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
@Component("viewPageMapper")
@Mapper
public interface ViewPageMapper extends CrudMapper<ViewPageEntity, Long> {

    /**
     * 指定视图页面分类 id，获取所有视图页面
     * @param viewPageCategoryId 视图页面分类 id
     * @return List<ViewPageEntity>
     */
    Page<ViewPageEntity> pageAllByViewPageCategoryId(@Param("viewPageCategoryId") Long viewPageCategoryId);

    /**
     * 指定视图页面分类 id，统计所有视图页面个数
     * @param viewPageCategoryId 视图页面分类 id
     * @return int
     */
    int countAllByViewPageCategoryId(@Param("viewPageCategoryId") Long viewPageCategoryId);

    /**
     * 指定 authority，获取视图页面
     * @param authority authority
     * @return ViewPageEntity
     */
    ViewPageEntity getOneByAuthority(@Param("authority") String authority);

    /**
     * 指定视图页面分类 id，获取所有视图页面
     * @param viewPageCategoryId 视图页面分类 id
     * @return List<ViewPageEntity>
     */
    List<ViewPageEntity> listAllByViewPageCategoryId(@Param("viewPageCategoryId") Long viewPageCategoryId);

    /**
     * 指定视图页面分类 id，获取视图页面
     * @param viewPageCategoryId 视图页面分类 id
     * @return ViewPageEntity
     */
    ViewPageEntity getOneByViewPageCategoryId(@Param("viewPageCategoryId") Long viewPageCategoryId);

    /**
     * 指定视图页面分类 id list，分页获取视图页面
     * @param viewPageCategoryIdList 视图页面分类 id list
     * @return Page<ViewPageEntity>
     */
    Page<ViewPageEntity> pageAllByViewPageCategoryIdList(@Param("viewPageCategoryIdList") List<Long> viewPageCategoryIdList);

}
