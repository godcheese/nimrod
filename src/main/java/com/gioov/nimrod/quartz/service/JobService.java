package com.gioov.nimrod.quartz.service;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.quartz.entity.JobEntity;

import java.util.Date;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-01
 */
public interface JobService {

    Date addOne(String jobClassName, String jobGroup, String cronExpression, String description) throws BaseResponseException;

    JobEntity getOne(String jobClassName, String jobGroup);

    Date updateCronExpressionByJobClassNameAndJobGroup(String jobClassName, String jobGroup, String cronExpression, String description) throws BaseResponseException;

    int deleteAll(List<String> jobClassNameList, List<String> jobGroupList) throws BaseResponseException;

    Pagination.Result<JobEntity> pageAll(Integer page, Integer rows);

}