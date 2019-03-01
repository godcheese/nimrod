package com.gioov.nimrod.quartz.job;

import com.gioov.common.util.DateUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-01
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SimpleJob implements BaseJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info( "{} SimpleJob 被执行", DateUtil.getNowDateTime());
    }

}
