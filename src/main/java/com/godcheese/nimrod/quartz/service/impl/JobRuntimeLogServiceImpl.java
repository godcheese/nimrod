package com.godcheese.nimrod.quartz.service.impl;

import com.godcheese.nimrod.common.easyui.Pagination;
import com.godcheese.nimrod.common.others.SpringContextUtil;
import com.godcheese.nimrod.quartz.entity.JobRuntimeLogEntity;
import com.godcheese.nimrod.quartz.mapper.JobRuntimeLogMapper;
import com.godcheese.nimrod.quartz.service.JobRuntimeLogService;
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
        jobRuntimeLogMapper = (JobRuntimeLogMapper) SpringContextUtil.getBean(JobRuntimeLogMapper.class);
    }

    @Override
    public int deleteAll(List<Long> idList) {
        return jobRuntimeLogMapper.deleteAll(idList);
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
}