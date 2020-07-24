package com.godcheese.nimrod.quartz.service;

import com.godcheese.nimrod.common.easyui.Pagination;
import com.godcheese.nimrod.quartz.entity.JobEntity;
import com.godcheese.tile.web.exception.BaseResponseException;

import java.util.Date;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-01
 */
public interface JobService {

    /**
     * 新增任务
     *
     * @param jobClassName   JobClassName
     * @param jobGroup       JobGroup
     * @param cronExpression Cron 表达式
     * @param description    描述
     * @return Date
     * @throws BaseResponseException BaseResponseException
     */
    Date addOne(String jobClassName, String jobGroup, String cronExpression, String description) throws BaseResponseException;

    /**
     * 指定 JobClassName list、JobGroup list，批量删除任务
     *
     * @param jobClassNameList JobClassName list
     * @param jobGroupList     JobGroup list
     * @return int
     * @throws BaseResponseException BaseResponseException
     */
    int deleteAll(List<String> jobClassNameList, List<String> jobGroupList) throws BaseResponseException;

    /**
     * 指定 JobClassName、JobGroup，获取任务
     *
     * @param jobClassName JobClassName
     * @param jobGroup     JobGroup
     * @return JobEntity
     */
    JobEntity getOne(String jobClassName, String jobGroup);

    /**
     * 分页获取所有任务
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination<JobEntity>
     */
    Pagination<JobEntity> pageAll(Integer page, Integer rows);

    /**
     * 指定 JobClassName、JobGroup，更新任务 Cron 表达式、描述
     *
     * @param jobClassName   JobClassName
     * @param jobGroup       JobGroup
     * @param cronExpression Cron 表达式
     * @param description    描述
     * @return Date
     * @throws BaseResponseException BaseResponseException
     */
    Date updateCronExpressionByJobClassNameAndJobGroup(String jobClassName, String jobGroup, String cronExpression, String description) throws BaseResponseException;

    /**
     * 指定 JobClassName list、JobGroup list，暂停所有任务
     *
     * @param jobClassNameList JobClassName list
     * @param jobGroupList     JobGroup list
     * @return int
     * @throws BaseResponseException BaseResponseException
     */
    int pauseAll(List<String> jobClassNameList, List<String> jobGroupList) throws BaseResponseException;

    /**
     * 指定 JobClassName list、JobGroup list，恢复所有任务
     *
     * @param jobClassNameList JobClassName list
     * @param jobGroupList     JobGroup list
     * @return int
     * @throws BaseResponseException BaseResponseException
     */
    int resumeAll(List<String> jobClassNameList, List<String> jobGroupList) throws BaseResponseException;

    /**
     * 重置 error state trigger
     *
     * @param jobClassNameList
     * @param jobGroupList
     * @return
     * @throws BaseResponseException
     */
    int resetTriggerFromErrorState(List<String> jobClassNameList, List<String> jobGroupList) throws BaseResponseException;
}