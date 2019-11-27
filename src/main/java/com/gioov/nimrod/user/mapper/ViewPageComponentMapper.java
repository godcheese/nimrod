package com.gioov.nimrod.user.mapper;

import com.gioov.nimrod.user.entity.ViewPageComponentEntity;
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
@Component("viewPageComponentMapper")
@Mapper
public interface ViewPageComponentMapper extends CrudMapper<ViewPageComponentEntity, Long> {

    /**
     * 指定视图页面 id，获取所有视图页面组件
     * @param viewPageId 视图页面 id
     * @return List<ViewPageComponentEntity>
     */
    Page<ViewPageComponentEntity> pageAllByViewPageId(@Param("viewPageId") Long viewPageId);

    /**
     * 指定 authority，获取视图页面组件
     * @param authority authority
     * @return ViewPageComponentEntity
     */
    ViewPageComponentEntity getOneByAuthority(@Param("authority") String authority);

    /**
     * 指定视图页面 id list，分页获取视图页面
     * @param viewPageIdList 视图页面 id list
     * @return Page<ViewPageComponentEntity>
     */
    Page<ViewPageComponentEntity> pageAllByViewPageIdList(@Param("viewPageIdList") List<Long> viewPageIdList);
}
