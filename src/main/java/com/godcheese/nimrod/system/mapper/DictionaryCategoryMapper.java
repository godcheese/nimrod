package com.godcheese.nimrod.system.mapper;

import com.godcheese.nimrod.system.entity.DictionaryCategoryEntity;
import com.godcheese.tile.mybatis.CrudMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("dictionaryCategoryMapper")
@Mapper
public interface DictionaryCategoryMapper extends CrudMapper<DictionaryCategoryEntity, Long> {

    /**
     * 分页获取所有父级 id 为 null 的数据字典分类
     *
     * @return List<DictionaryCategoryEntity>
     */
    List<DictionaryCategoryEntity> listAllByParentIdIsNull();

    /**
     * 指定父级数据字典分类 id，获取所有数据字典分类
     *
     * @param parentId 父级数据字典分类 id
     * @return List<DictionaryCategoryEntity>
     */
    List<DictionaryCategoryEntity> listAllByParentId(@Param("parentId") Long parentId);

    /**
     * 指定父级 id 为 null 的数据字典分类，获取数据字典分类
     *
     * @param parentId 父级数据字典分类 id
     * @return DictionaryCategoryEntity
     */
    DictionaryCategoryEntity getOneByParentId(@Param("parentId") Long parentId);
}
