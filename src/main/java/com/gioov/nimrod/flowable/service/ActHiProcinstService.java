package com.gioov.nimrod.flowable.service;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.flowable.entity.ActHiProcinstEntity;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-21
 */
public interface ActHiProcinstService {

    int deleteProcessInstance(List<String> processInstanceIdList, List<String> deleteReasonList);

    void deleteProcessInstance(String id, String deleteReason);

    Pagination.Result<ActHiProcinstEntity> pageAll(int page, int rows, boolean active);

}
