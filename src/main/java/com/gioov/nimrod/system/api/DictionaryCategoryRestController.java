package com.gioov.nimrod.system.api;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.Url;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.System;
import com.gioov.nimrod.system.entity.DictionaryCategoryEntity;
import com.gioov.nimrod.system.service.DictionaryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(System.Api.DICTIONARY_CATEGORY)
public class DictionaryCategoryRestController {

    private static final String DICTIONARY_CATEGORY = "/API/SYSTEM/DICTIONARY_CATEGORY";

    @Autowired
    private DictionaryCategoryService dictionaryCategoryService;

    /**
     * 分页获取所有父级数据字典分类
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination.Result<DictionaryCategoryEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DICTIONARY_CATEGORY + "/PAGE_ALL_PARENT')")
    @GetMapping(value = "/page_all_parent")
    public ResponseEntity<Pagination.Result<DictionaryCategoryEntity>> pageAllParent(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(dictionaryCategoryService.pageAllParent(page, rows), HttpStatus.OK);
    }

    /**
     * 指定父级数据字典分类 id ，获取所有数据字典分类
     *
     * @param parentId 父级数据字典分类 id
     * @return ResponseEntity<List                               <                               DictionaryCategoryEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DICTIONARY_CATEGORY + "/LIST_ALL_BY_PARENT_ID')")
    @GetMapping(value = "/list_all_by_parent_id/{parentId}")
    public ResponseEntity<List<DictionaryCategoryEntity>> listAllByParentId(@PathVariable Long parentId) {
        return new ResponseEntity<>(dictionaryCategoryService.listAllByParentId(parentId), HttpStatus.OK);
    }

    /**
     * 新增数据字典分类
     *
     * @param name     数据字典分类名
     * @param parentId 数据字典分类父级 id
     * @return ResponseEntity<DictionaryCategoryEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DICTIONARY_CATEGORY + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<DictionaryCategoryEntity> addOne(@RequestParam String name, @RequestParam(required = false) Long parentId, @RequestParam Long sort, @RequestParam String remark) {
        DictionaryCategoryEntity dictionaryCategoryEntity = new DictionaryCategoryEntity();
        dictionaryCategoryEntity.setName(name);
        dictionaryCategoryEntity.setParentId(parentId);
        dictionaryCategoryEntity.setSort(sort);
        dictionaryCategoryEntity.setRemark(remark);
        DictionaryCategoryEntity dictionaryCategoryEntity1 = dictionaryCategoryService.insertOne(dictionaryCategoryEntity);
        return new ResponseEntity<>(dictionaryCategoryEntity1, HttpStatus.OK);
    }

    /**
     * 保存数据字典分类
     *
     * @param id   数据字典分类 id
     * @param name 数据字典分类名
     * @return ResponseEntity<DictionaryCategoryEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DICTIONARY_CATEGORY + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<DictionaryCategoryEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam Long sort, @RequestParam String remark) {
        DictionaryCategoryEntity dictionaryCategoryEntity = new DictionaryCategoryEntity();
        dictionaryCategoryEntity.setId(id);
        dictionaryCategoryEntity.setName(name);
        dictionaryCategoryEntity.setSort(sort);
        dictionaryCategoryEntity.setRemark(remark);
        DictionaryCategoryEntity dictionaryCategoryEntity1 = dictionaryCategoryService.updateOne(dictionaryCategoryEntity);
        return new ResponseEntity<>(dictionaryCategoryEntity1, HttpStatus.OK);
    }

    /**
     * 指定数据字典分类 id ，批量删除数据字典分类
     *
     * @param idList 数据字典分类 id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DICTIONARY_CATEGORY + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) throws BaseResponseException {
        return new ResponseEntity<>(dictionaryCategoryService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定数据字典分类 id ，获取数据字典分类信息
     *
     * @param id 数据字典分类 id
     * @return ResponseEntity<DictionaryCategoryEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DICTIONARY_CATEGORY + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<DictionaryCategoryEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(dictionaryCategoryService.getOne(id), HttpStatus.OK);
    }

}
