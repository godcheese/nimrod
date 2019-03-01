package com.gioov.nimrod.quartz.api;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.quartz.Quartz;
import com.gioov.nimrod.quartz.entity.JobRuntimeLogEntity;
import com.gioov.nimrod.quartz.service.JobRuntimeLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-01
 */
@RestController
@RequestMapping(Quartz.Api.JOB_RUNTIME_LOG)
public class JobRuntimeLogRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobRuntimeLogRestController.class);

    @Autowired
    private JobRuntimeLogService jobRuntimeLogService;

//    @RequestMapping("/one")
//    public ResponseEntity<JobEntity> getOne(@RequestParam String jobClassName, @RequestParam String jobGroup) {
//        return new ResponseEntity<>(jobRuntimeLogService.getOne(jobClassName, jobGroup), HttpStatus.OK);
//    }

    @RequestMapping("/page_all")
    public ResponseEntity<Pagination.Result<JobRuntimeLogEntity>> pageAll(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(jobRuntimeLogService.pageAll(page, rows), HttpStatus.OK);
    }

    /**
     * 删除所有任务
     * @param jobClassNameList
     * @param jobGroupList
     * @return
     * @throws BaseResponseException
     */
    @RequestMapping("/truncate")
    public ResponseEntity<HttpStatus> truncate() {
        jobRuntimeLogService.truncate();
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.OK);
    }

}
