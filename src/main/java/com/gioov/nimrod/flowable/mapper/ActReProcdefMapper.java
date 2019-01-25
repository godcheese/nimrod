package com.gioov.nimrod.flowable.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.flowable.entity.ActReProcdefEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-18
 */
@Component("actReProcDefMapper")
@Mapper
public interface ActReProcdefMapper extends CrudMapper<ActReProcdefEntity, String> {
}
