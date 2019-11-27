package com.gioov.nimrod.system.service;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.OperationLogEntity;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface OperationLogService {

    /**
     * 分页获取所有操作日志
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination<OperationLogEntity>
     */
    Pagination<OperationLogEntity> pageAll(Integer page, Integer rows);

    /**
     * 新增操作日志
     * @param operationLogEntity OperationLogEntity
     * @return OperationLogEntity
     */
    OperationLogEntity addOne(OperationLogEntity operationLogEntity);

    /**
     * 指定操作日志 id，批量删除操作日志
     * @param idList 操作日志 id list
     * @return 已删除操作日志个数
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定操作日志 id，获取操作日志
     * @param id 操作日志 id
     * @return OperationLogEntity
     */
    OperationLogEntity getOne(Long id);

    /**
     * 清空所有操作日志
     */
    void clearAll();

}
