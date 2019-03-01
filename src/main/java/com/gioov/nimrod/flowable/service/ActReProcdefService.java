package com.gioov.nimrod.flowable.service;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.flowable.entity.ActReProcdefEntity;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-18
 */
public interface ActReProcdefService {

    Pagination.Result<ActReProcdefEntity> pageAll(Integer page, Integer rows);

    int deleteProcessDefinition(List<String> idList);

}
