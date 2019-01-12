package com.gioov.nimrod.system.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.system.entity.ViewPageCategoryEntity;
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
     * 分页获取所有父级 id 为 null 的视图页面分类
     *
     * @param pageable Pageable
     * @return List<ViewPageCategoryEntity>
     */
    List<ViewPageCategoryEntity> pageAllByParentIdIsNull(@Param("pageable") Pageable pageable);

    /**
     * 统计所有父级 id 为 null 的视图页面分类个数
     *
     * @return int
     */
    int countAllByParentIdIsNull();

    /**
     * 指定视图页面分类父级 id ，获取所有视图页面分类
     *
     * @param parentId 视图页面分类父级 id
     * @return List<ViewPageCategoryEntity>
     */
    List<ViewPageCategoryEntity> listAllByParentId(@Param("parentId") Long parentId);

    /**
     * 指定视图页面分类父级 id ，获取视图页面分类
     *
     * @param parentId 视图页面分类父级 id
     * @return ViewPageCategoryEntity
     */
    ViewPageCategoryEntity getOneByParentId(@Param("parentId") Long parentId);
}
