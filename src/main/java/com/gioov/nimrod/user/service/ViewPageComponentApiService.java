package com.gioov.nimrod.user.service;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ViewPageComponentApiService {

//    /**
//     * 指定视图页面组件 id、API id，判断是否关联 API
//     * @param viewPageComponentId 视图页面组件 id
//     * @param apiId API id
//     * @return Map<String,Object>
//     */
//    Map<String, Object> isAssociatedByViewPageComponentIdAndApiId(Long viewPageComponentId, Long apiId);

    /**
     * 指定视图页面组件 id、API id list，批量关联 API
     *
     * @param viewPageComponentId 视图页面组件 id
     * @param apiIdList           API id list
     * @return int
     */
    int associateAllByViewPageComponentIdAndApiIdList(Long viewPageComponentId, List<Long> apiIdList);

    /**
     * 指定视图页面组件 id、API id list，批量撤销关联 API
     *
     * @param viewPageComponentId 视图页面组件 id
     * @param apiIdList           API id list
     * @return int
     */
    int revokeAssociateAllByViewPageComponentIdAndApiIdList(Long viewPageComponentId, List<Long> apiIdList);

}
