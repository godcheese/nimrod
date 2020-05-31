package com.gioov.nimrod.user.mapper;

import com.gioov.nimrod.user.entity.ViewPageApiEntity;
import com.gioov.tile.mybatis.CrudMapper;
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
     * 指定视图页面 id、 API id，获取视图页面 API
     *
     * @param viewPageId 视图页面 id
     * @param apiId      视图页面 API id
     * @return ViewPageApiEntity
     */
    ViewPageApiEntity getOneByViewPageIdAndApiId(@Param("viewPageId") Long viewPageId, @Param("apiId") Long apiId);

    /**
     * 指定视图页面 id、 API id list，插入所有
     *
     * @param viewPageId 视图页面 id
     * @param apiIdList  视图页面 API id list
     * @return int
     */
    int insertAllByViewPageIdAndApiIdList(@Param("viewPageId") Long viewPageId, @Param("apiIdList") List<Long> apiIdList);

    /**
     * 指定视图页面 id、 API id list，删除所有
     *
     * @param viewPageId 视图页面 id
     * @param apiIdList  视图页面 API id list
     * @return int
     */
    int deleteAllByViewPageIdAndApiIdList(@Param("viewPageId") Long viewPageId, @Param("apiIdList") List<Long> apiIdList);

    /**
     * 指定视图页面 id，获取所有视图页面 API
     *
     * @param viewPageId 视图页面 id
     * @return List<ViewPageApiEntity>
     */
    List<ViewPageApiEntity> listAllByViewPageId(@Param("viewPageId") Long viewPageId);

}
