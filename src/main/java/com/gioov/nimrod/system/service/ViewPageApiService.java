package com.gioov.nimrod.system.service;

import java.util.List;
import java.util.Map;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ViewPageApiService {

    /**
     * 是否关联 API
     *
     * @param pageId 视图页面 id
     * @param apiId  API id
     * @return Map<String ,   Object>
     */
    Map<String, Object> isAssociatedByPageIdAndApiId(Long pageId, Long apiId);

    /**
     * 批量关联 API
     *
     * @param pageId    视图页面 id
     * @param apiIdList API id list
     * @return List<Long>
     */
    List<Long> associateAllByPageIdAndApiIdList(Long pageId, List<Long> apiIdList);

    /**
     * 指定视图页面 id、API id ，批量撤销关联
     *
     * @param pageId    视图页面 id
     * @param apiIdList API id list
     * @return int
     */
    int revokeAssociateAllByPageIdAndApiIdList(Long pageId, List<Long> apiIdList);

}
