package com.gioov.nimrod.quartz.listener;

import com.gioov.nimrod.common.others.SpringContextUtil;
import com.gioov.nimrod.quartz.entity.JobRuntimeLogEntity;
import com.gioov.nimrod.quartz.mapper.JobRuntimeLogMapper;
import com.gioov.nimrod.quartz.service.JobRuntimeLogService;
import com.gioov.nimrod.quartz.service.impl.JobRuntimeLogServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-02
 */
@Component
public class GlobalJobListener implements JobListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalJobListener.class);

    private JobRuntimeLogMapper jobRuntimeLogMapper;

    public GlobalJobListener() {
        jobRuntimeLogMapper = (JobRuntimeLogMapper) SpringContextUtil.getBean(JobRuntimeLogMapper.class);
    }

    private JobRuntimeLogEntity jobRuntimeLogEntity = new JobRuntimeLogEntity();

    private long beginTime = 0;

    @Autowired
    @Bean
    private JobRuntimeLogService jobRuntimeLogService() {
        return new JobRuntimeLogServiceImpl();
    }

    @Override
    public String getName() {
        return "globalJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        beginTime = Instant.now().toEpochMilli();
        jobRuntimeLogEntity.setJobClassName(context.getJobDetail().getKey().getName());
        jobRuntimeLogEntity.setJobGroup(context.getJobDetail().getKey().getGroup());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        if (jobException != null) {
            jobRuntimeLogEntity.setJobException(jobException.getMessage());
        }
        jobRuntimeLogEntity.setConsumingTime(Instant.now().toEpochMilli() - beginTime);
        jobRuntimeLogEntity.setGmtCreated(new Date());
        LOGGER.info("jobRuntimeLogEntity={}", jobRuntimeLogEntity);
        jobRuntimeLogMapper.insertOne(jobRuntimeLogEntity);
    }

}
