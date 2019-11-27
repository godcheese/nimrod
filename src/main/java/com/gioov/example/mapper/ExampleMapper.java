package com.gioov.example.mapper;

import com.gioov.tile.mybatis.CrudMapper;
import com.gioov.example.entity.ExampleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("exampleMapper")
@Mapper
public interface ExampleMapper extends CrudMapper<ExampleEntity, Long> {
}
