package com.gioov.nimrod.quartz.service;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.quartz.entity.JobRuntimeLogEntity;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-13
 */
public interface JobRuntimeLogService {

    /**
     * 指定任务运行日志 id，获取任务运行日志
     *
     * @param id 任务运行日志 id
     * @return JobRuntimeLogEntity
     */
    JobRuntimeLogEntity getOne(Long id);

    /**
     * 分页获取所有任务运行日志
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination<JobRuntimeLogEntity>
     */
    Pagination<JobRuntimeLogEntity> pageAll(Integer page, Integer rows);

    /**
     * 清空所有任务运行日志
     */
    void clearAll();

    int deleteAll(List<Long> idList);
}
