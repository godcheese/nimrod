package com.gioov.nimrod.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gioov.common.mybatis.Sort;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.OperationLogEntity;
import com.gioov.nimrod.system.entity.OperationLogEntity2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface OperationLogService {

    /**
     * 分页获取所有操作日志
     *
     * @param page 页
     * @param rows 每页显示数量
     * @param sort 排序
     * @return Pagination.Result<OperationLogEntity2>
     */
    Pagination.Result<OperationLogEntity2> pageAll(int page, int rows, Sort sort);

    /**
     * 新增操作日志
     *
     * @param operationLogEntity OperationLogEntity
     * @return OperationLogEntity
     */
    OperationLogEntity insertOne(OperationLogEntity operationLogEntity);

    /**
     * 指定操作日志 id ，批量删除操作日志
     *
     * @param idList 操作日志 id list
     * @return 已删除操作日志个数
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定操作日志 id ，获取操作日志信息
     *
     * @param id 操作日志 id
     * @return OperationLogEntity2
     */
    OperationLogEntity2 getOne(Long id);

    OperationLogEntity log(HttpServletRequest request, HttpServletResponse response, Object handler, long requestTime) throws JsonProcessingException;

    /**
     * 清空操作日志
     */
    void truncate();

}
