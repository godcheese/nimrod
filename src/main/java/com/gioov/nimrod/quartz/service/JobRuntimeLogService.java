package com.gioov.nimrod.quartz.service;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.quartz.entity.JobRuntimeLogEntity;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-13
 */
public interface JobRuntimeLogService {

    JobRuntimeLogEntity log(JobExecutionContext jobExecutionContext, JobExecutionException jobExecutionException, String log);

    Pagination.Result<JobRuntimeLogEntity> pageAll(Integer page, Integer rows);

    void truncate();
}
