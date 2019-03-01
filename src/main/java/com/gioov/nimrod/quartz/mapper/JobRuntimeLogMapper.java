package com.gioov.nimrod.quartz.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.quartz.entity.JobRuntimeLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("jobRuntimeLogMapper")
@Mapper
public interface JobRuntimeLogMapper extends CrudMapper<JobRuntimeLogEntity, Long> {

}
