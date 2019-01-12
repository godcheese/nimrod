package com.gioov.nimrod.system.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.system.entity.OperationLogEntity2;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("operationLogMapper2")
@Mapper
public interface OperationLogMapper2 extends CrudMapper<OperationLogEntity2, Long> {
}
