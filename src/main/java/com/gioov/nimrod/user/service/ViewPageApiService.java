package com.gioov.nimrod.user.service;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ViewPageApiService {

//    /**
//     * 指定视图页面 id、API id，判断是否关联 API
//     * @param viewPageId 视图页面 id
//     * @param apiId API id
//     * @return Map<String,Object>
//     */
//    Map<String, Object> isAssociatedByViewPageIdAndApiId(Long viewPageId, Long apiId);

    /**
     * 指定视图页面 id、API id list，批量关联 API
     *
     * @param viewPageId 视图页面 id
     * @param apiIdList  API id list
     * @return int
     */
    int associateAllByViewPageIdAndApiIdList(Long viewPageId, List<Long> apiIdList);

    /**
     * 指定视图页面 id、API id list，批量撤销关联 API
     *
     * @param viewPageId 视图页面 id
     * @param apiIdList  API id list
     * @return int
     */
    int revokeAssociateAllByViewPageIdAndApiIdList(Long viewPageId, List<Long> apiIdList);

}
