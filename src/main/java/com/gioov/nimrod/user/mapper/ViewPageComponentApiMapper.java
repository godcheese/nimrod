package com.gioov.nimrod.user.mapper;

import com.gioov.nimrod.user.entity.ViewPageComponentApiEntity;
import com.gioov.tile.mybatis.CrudMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("viewPageComponentApiMapper")
@Mapper
public interface ViewPageComponentApiMapper extends CrudMapper<ViewPageComponentApiEntity, Long> {

    /**
     * 指定视图页面组件 id、 API id，获取视图页面组件 API
     *
     * @param viewPageComponentId 视图页面组件 id
     * @param apiId               视图页面组件 API id
     * @return ViewPageComponentApiEntity
     */
    ViewPageComponentApiEntity getOneByViewPageComponentIdAndApiId(@Param("viewPageComponentId") Long viewPageComponentId, @Param("apiId") Long apiId);

    /**
     * 指定视图页面组件 id、 API id list，统计所有视图页面组件 API 个数
     *
     * @param viewPageComponentId 视图页面组件 id
     * @param apiIdList           视图页面组件 API id
     * @return int
     */
    int insertAllByViewPageComponentIdAndApiIdList(@Param("viewPageComponentId") Long viewPageComponentId, @Param("apiIdList") List<Long> apiIdList);

    /**
     * 指定视图页面组件 id、 API id list，删除所有
     *
     * @param viewPageComponentId 视图页面组件 id
     * @param apiIdList           视图页面组件 API
     * @return int
     */
    int deleteAllByViewPageComponentIdAndApiIdList(@Param("viewPageComponentId") Long viewPageComponentId, @Param("apiIdList") List<Long> apiIdList);

    /**
     * 指定视图页面组件 id，获取所有视图页面组件 API
     *
     * @param viewPageComponentId 视图页面组件 id
     * @return List<ViewPageComponentApiEntity>
     */
    List<ViewPageComponentApiEntity> listAllByViewPageComponentId(@Param("viewPageComponentId") Long viewPageComponentId);


}
