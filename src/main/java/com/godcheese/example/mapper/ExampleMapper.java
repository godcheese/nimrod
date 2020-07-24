package com.godcheese.example.mapper;

import com.godcheese.example.entity.ExampleEntity;
import com.godcheese.tile.mybatis.CrudMapper;
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
