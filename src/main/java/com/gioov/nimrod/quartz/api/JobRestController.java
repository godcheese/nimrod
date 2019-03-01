package com.gioov.nimrod.quartz.api;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.common.web.http.SuccessEntity;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.quartz.Quartz;
import com.gioov.nimrod.quartz.entity.JobEntity;
import com.gioov.nimrod.quartz.mapper.JobMapper;
import com.gioov.nimrod.quartz.service.JobService;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-01
 */
@RestController
@RequestMapping(Quartz.Api.JOB)
public class JobRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobRestController.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobService jobService;

    @RequestMapping("/add_one")
    public ResponseEntity<Date> addJob(@RequestParam String jobClassName, @RequestParam String jobGroup, @RequestParam String cronExpression, @RequestParam String description) throws BaseResponseException {
        return new ResponseEntity<>(jobService.addOne(jobClassName, jobGroup, cronExpression, description), HttpStatus.OK);
    }

    @RequestMapping("/one")
    public ResponseEntity<JobEntity> getOne(@RequestParam String jobClassName, @RequestParam String jobGroup) {
        return new ResponseEntity<>(jobService.getOne(jobClassName, jobGroup), HttpStatus.OK);
    }

    @RequestMapping("/page_all")
    public ResponseEntity<Pagination.Result<JobEntity>> pageAll(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(jobService.pageAll(page, rows), HttpStatus.OK);
    }

    /**
     * 暂停所有任务
     * @param jobClassNameList
     * @param jobGroupList
     * @return
     * @throws BaseResponseException
     */
    @RequestMapping("/pause_all")
    public ResponseEntity<SuccessEntity> pauseAll(@RequestParam(required = false, name = "jobClassName[]") List<String> jobClassNameList, @RequestParam(required = false, name = "jobGroup[]") List<String> jobGroupList) throws BaseResponseException {
        try {
            if(jobClassNameList != null && jobGroupList != null) {
                int index = 0;
                for (String jobClassName : jobClassNameList) {
                    scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupList.get(index)));
                    index++;
                }
            } else {
                scheduler.pauseAll();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new BaseResponseException("任务暂停失败");
        }
        return new ResponseEntity<>(new SuccessEntity(), HttpStatus.OK);
    }

    /**
     * 恢复所有任务
     * @param jobClassNameList
     * @param jobGroupList
     * @return
     * @throws BaseResponseException
     */
    @RequestMapping("/resume_all")
    public ResponseEntity<SuccessEntity> resumeAll(@RequestParam(required = false, name = "jobClassName[]") List<String> jobClassNameList, @RequestParam(required = false, name = "jobGroup[]") List<String> jobGroupList) throws BaseResponseException {
        try {
            if(jobClassNameList != null && jobGroupList != null) {
                int index = 0;
                for (String jobClassName : jobClassNameList) {
                    scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupList.get(index)));
                    index++;
                }
            } else {
                scheduler.resumeAll();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new BaseResponseException("任务恢复失败");
        }
        return new ResponseEntity<>(new SuccessEntity(), HttpStatus.OK);
    }

    /**
     * 删除所有任务
     * @param jobClassNameList
     * @param jobGroupList
     * @return
     * @throws BaseResponseException
     */
    @RequestMapping("/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam(name = "jobClassName[]") List<String> jobClassNameList, @RequestParam(name = "jobGroup[]") List<String> jobGroupList) throws BaseResponseException {
        return new ResponseEntity<>(jobService.deleteAll(jobClassNameList, jobGroupList),HttpStatus.OK);
    }

    @RequestMapping("/save_one")
    public ResponseEntity<Date> saveOne(@RequestParam String jobClassName, @RequestParam String jobGroup, @RequestParam String cronExpression, @RequestParam String description) throws BaseResponseException {
       return new ResponseEntity<>(jobService.updateCronExpressionByJobClassNameAndJobGroup(jobClassName, jobGroup, cronExpression, description), HttpStatus.OK);
    }

}
