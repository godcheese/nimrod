package com.gioov.nimrod.quartz.mapper;

import com.gioov.nimrod.quartz.entity.JobRuntimeLogEntity;
import com.gioov.tile.mybatis.CrudMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("jobRuntimeLogMapper")
@Mapper
public interface JobRuntimeLogMapper extends CrudMapper<JobRuntimeLogEntity, Long> {

    /**
     * 分页获取所有任务运行日志
     *
     * @return Page<JobRuntimeLogEntity>
     */
    Page<JobRuntimeLogEntity> pageAll();
}
