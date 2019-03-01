package com.gioov.nimrod.quartz.listener;

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

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-02
 */
@Component
public class GlobalJobListener implements JobListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalJobListener.class);

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
//        SpringContextUtil.getBean("jobRuntimeLogService", JobRuntimeLogServiceImpl.class);
        LOGGER.info("jobLogEntity={}", jobRuntimeLogService().log(context,null, "开始执行"));
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        LOGGER.info("context2={}", context);
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        LOGGER.info("jobLogEntity={}", jobRuntimeLogService().log(context,jobException, "执行完毕"));
    }

}
