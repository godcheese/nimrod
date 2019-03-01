package com.gioov.nimrod.flowable.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.flowable.entity.ActHiProcinstEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-18
 */
@Component("actHiProcinstMapper")
@Mapper
public interface ActHiProcinstMapper extends CrudMapper<ActHiProcinstEntity, String> {

    List<ActHiProcinstEntity> pageAllActive(@Param("pageable") Pageable pageable);

    int countAllActive();
}
