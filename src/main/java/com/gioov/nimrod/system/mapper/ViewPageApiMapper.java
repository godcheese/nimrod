package com.gioov.nimrod.system.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.system.entity.ViewPageApiEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("viewPageApiMapper")
@Mapper
public interface ViewPageApiMapper extends CrudMapper<ViewPageApiEntity, Long> {

    /**
     * 指定视图页面 id 、 API id ，获取视图页面 API
     *
     * @param pageId 视图页面 id
     * @param apiId  视图页面 API id
     * @return ViewPageApiEntity
     */
    ViewPageApiEntity getOneByPageIdAndApiId(@Param("pageId") Long pageId, @Param("apiId") Long apiId);

    /**
     * 指定视图页面 id 、 API id list ，插入所有
     *
     * @param pageId    视图页面 id
     * @param apiIdList 视图页面 API id list
     * @return int
     */
    int insertAllByPageIdAndApiIdList(@Param("pageId") Long pageId, @Param("apiIdList") List<Long> apiIdList);

    /**
     * 指定视图页面 id 、 API id list ，删除所有
     *
     * @param pageId    视图页面 id
     * @param apiIdList 视图页面 API id list
     * @return int
     */
    int deleteAllByPageIdAndApiIdList(@Param("pageId") Long pageId, @Param("apiIdList") List<Long> apiIdList);

    /**
     * 指定视图页面 id ，获取所有视图页面 API
     *
     * @param pageId 视图页面 id
     * @return List<ViewPageApiEntity>
     */
    List<ViewPageApiEntity> listAllByPageId(@Param("pageId") Long pageId);

}
