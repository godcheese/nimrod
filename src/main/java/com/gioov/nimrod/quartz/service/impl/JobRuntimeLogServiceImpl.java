package com.gioov.nimrod.quartz.service.impl;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.others.SpringContextUtil;
import com.gioov.nimrod.quartz.entity.JobRuntimeLogEntity;
import com.gioov.nimrod.quartz.mapper.JobRuntimeLogMapper;
import com.gioov.nimrod.quartz.service.JobRuntimeLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-13
 */
@Service
public class JobRuntimeLogServiceImpl implements JobRuntimeLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobRuntimeLogServiceImpl.class);

    private JobRuntimeLogMapper jobRuntimeLogMapper;

    public JobRuntimeLogServiceImpl() {
        jobRuntimeLogMapper = (JobRuntimeLogMapper) SpringContextUtil.getBean("jobRuntimeLogMapper", JobRuntimeLogMapper.class);
    }

    @Override
    public JobRuntimeLogEntity getOne(Long id) {
        return null;
    }

    @Override
    public Pagination<JobRuntimeLogEntity> pageAll(Integer page, Integer rows) {
        Pagination<JobRuntimeLogEntity> pagination = new Pagination<>();
        PageHelper.startPage(page, rows);
        Page<JobRuntimeLogEntity> jobRuntimeLogEntityPage = jobRuntimeLogMapper.pageAll();
        pagination.setRows(jobRuntimeLogEntityPage.getResult());
        pagination.setTotal(jobRuntimeLogEntityPage.getTotal());
        return pagination;
    }

    @Override
    public void clearAll() {
        jobRuntimeLogMapper.truncate();
    }

    @Override
    public int deleteAll(List<Long> idList) {
        return jobRuntimeLogMapper.deleteAll(idList);
    }
}