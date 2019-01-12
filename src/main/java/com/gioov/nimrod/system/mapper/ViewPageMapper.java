package com.gioov.nimrod.system.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.system.entity.ViewPageEntity;
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
     * 指定视图页面分类 id ，获取所有视图页面
     *
     * @param pageCategoryId 视图页面分类 id
     * @param pageable       Pageable
     * @return List<ViewPageEntity>
     */
    List<ViewPageEntity> pageAllByPageCategoryId(@Param("pageCategoryId") Long pageCategoryId, @Param("pageable") Pageable pageable);

    /**
     * 指定视图页面分类 id ，统计所有视图页面个数
     *
     * @param pageCategoryId 视图页面分类 id
     * @return int
     */
    int countAllByPageCategoryId(@Param("pageCategoryId") Long pageCategoryId);

    /**
     * 指定 authority ，获取视图页面
     *
     * @param authority authority
     * @return ViewPageEntity
     */
    ViewPageEntity getOneByAuthority(@Param("authority") String authority);

    /**
     * 指定视图页面分类 id ，获取所有视图页面
     *
     * @param pageCategoryId 视图页面分类 id
     * @return List<ViewPageEntity>
     */
    List<ViewPageEntity> listAllByPageCategoryId(@Param("pageCategoryId") Long pageCategoryId);

    /**
     * 指定视图页面分类 id ，获取视图页面
     *
     * @param pageCategoryId 视图页面分类 id
     * @return ViewPageEntity
     */
    ViewPageEntity getOneByPageCategoryId(@Param("pageCategoryId") Long pageCategoryId);
}
