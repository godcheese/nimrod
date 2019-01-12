package com.gioov.nimrod.system.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.system.entity.ViewPageComponentApiEntity;
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
     * 指定视图页面组件 id 、 API id ，获取视图页面组件 API
     *
     * @param pageComponentId 视图页面组件 id
     * @param apiId           视图页面组件 API id
     * @return ViewPageComponentApiEntity
     */
    ViewPageComponentApiEntity getOneByPageComponentIdAndApiId(@Param("pageComponentId") Long pageComponentId, @Param("apiId") Long apiId);

    /**
     * 指定视图页面组件 id 、 API id list ，统计所有视图页面组件 API 个数
     *
     * @param pageComponentId 视图页面组件 id
     * @param apiIdList       视图页面组件 API id
     * @return int
     */
    int insertAllByPageComponentIdAndApiIdList(@Param("pageComponentId") Long pageComponentId, @Param("apiIdList") List<Long> apiIdList);

    /**
     * 指定视图页面组件 id 、 API id list ，删除所有
     *
     * @param pageComponentId 视图页面组件 id
     * @param apiIdList       视图页面组件 API
     * @return int
     */
    int deleteAllByPageComponentIdAndApiIdList(@Param("pageComponentId") Long pageComponentId, @Param("apiIdList") List<Long> apiIdList);

    /**
     * 指定视图页面组件 id ，获取所有视图页面组件 API
     *
     * @param pageComponentId 视图页面组件 id
     * @return List<ViewPageComponentApiEntity>
     */
    List<ViewPageComponentApiEntity> listAllByPageComponentId(@Param("pageComponentId") Long pageComponentId);

}
