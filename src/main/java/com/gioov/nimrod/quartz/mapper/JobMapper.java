package com.gioov.nimrod.quartz.mapper;

import com.gioov.nimrod.quartz.entity.JobEntity;
import com.gioov.tile.mybatis.CrudMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("jobMapper")
@Mapper
public interface JobMapper extends CrudMapper<JobEntity, String> {

    /**
     * 指定 JobClassName、JobGroup，更新任务描述
     *
     * @param jobClassName JobClassName
     * @param jobGroup     JobGroup
     * @param description  描述
     * @return int
     */
    int updateJobDetailsDescriptionByJobClassNameAndJobGroup(@Param("jobClassName") String jobClassName, @Param("jobGroup") String jobGroup, @Param("description") String description);

    /**
     * 指定 JobClassName、JobGroup、获取任务息
     *
     * @param jobClassName JobClassName
     * @param jobGroup     JobGroup
     * @return JobEntity
     */
    JobEntity getOneByJobClassNameAndJobGroup(@Param("jobClassName") String jobClassName, @Param("jobGroup") String jobGroup);

    /**
     * 分页获取所有任务
     *
     * @return Page<JobEntity>
     */
    Page<JobEntity> pageAll();

}
