package com.gioov.nimrod.quartz.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.quartz.entity.JobEntity;
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

    int updateJobDetailsDescriptionByJobClassNameAndJobGroup(@Param("jobClassName") String jobClassName, @Param("jobGroup") String jobGroup, @Param("description") String description);

    JobEntity getOneByJobClassNameAndJobGroup(@Param("jobClassName") String jobClassName, @Param("jobGroup") String jobGroup);

}
