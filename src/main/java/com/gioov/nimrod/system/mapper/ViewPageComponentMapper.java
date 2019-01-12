package com.gioov.nimrod.system.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.system.entity.ViewPageComponentEntity;
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
     * 指定视图页面 id ，获取所有视图页面组件
     *
     * @param pageId   视图页面 id
     * @param pageable Pageable
     * @return List<ViewPageComponentEntity>
     */
    List<ViewPageComponentEntity> pageAllByPageId(@Param("pageId") Long pageId, @Param("pageable") Pageable pageable);

    /**
     * 指定视图页面 id ，统计所有视图页面组件个数
     *
     * @param pageId 视图页面 id
     * @return int
     */
    int countAllByPageId(@Param("pageId") Long pageId);

    /**
     * 指定 authority ，获取视图页面组件
     *
     * @param authority authority
     * @return ViewPageComponentEntity
     */
    ViewPageComponentEntity getOneByAuthority(@Param("authority") String authority);

}
