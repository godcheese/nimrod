package com.gioov.nimrod.system.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.system.entity.DictionaryCategoryEntity;
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
     * @param pageable Pageable
     * @return List<DictionaryCategoryEntity>
     */
    List<DictionaryCategoryEntity> pageAllByParentIdIsNull(@Param("pageable") Pageable pageable);

    /**
     * 统计所有父级 id 为 null 的数据字典分类分类个数
     *
     * @return int
     */
    int countAllByParentIdIsNull();

    /**
     * 指定父级数据字典分类 id ，获取所有数据字典分类
     *
     * @param parentId 数据字典分类父级 id
     * @return List<DictionaryCategoryEntity>
     */
    List<DictionaryCategoryEntity> listAllByParentId(@Param("parentId") Long parentId);

    /**
     * 指定父级 id 为 null 的数据字典分类，获取数据字典分类
     *
     * @param parentId 数据字典分类父级 id
     * @return DictionaryCategoryEntity
     */
    DictionaryCategoryEntity getOneByParentId(@Param("parentId") Long parentId);

}
