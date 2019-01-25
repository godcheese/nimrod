package com.gioov.nimrod.system.api;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.Url;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ViewPageCategoryEntity;
import com.gioov.nimrod.system.service.ViewPageCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(value = Url.Api.System.VIEW_PAGE_CATEGORY, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ViewPageCategoryRestController {

    private static final String VIEW_PAGE_CATEGORY = "/API/SYSTEM/VIEW_PAGE_CATEGORY";

    @Autowired
    private ViewPageCategoryService viewPageCategoryService;

    /**
     * 分页获取所有父级视图页面分类
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination.Result < ViewPageCategoryEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/PAGE_ALL_PARENT')")
    @GetMapping(value = "/page_all_parent")
    public ResponseEntity<Pagination.Result<ViewPageCategoryEntity>> pageAllParent(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(viewPageCategoryService.pageAllParent(page, rows), HttpStatus.OK);
    }

    /**
     * 指定父级视图页面分类 id ，获取所有视图页面分类
     *
     * @param id 视图页面分类 id
     * @return ResponseEntity<List                               <                               ViewPageCategoryEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/LIST_ALL_BY_PARENT_ID')")
    @GetMapping(value = "/list_all_by_parent_id/{id}")
    public ResponseEntity<Object> listAllByParentId(@PathVariable Long id) {
        return new ResponseEntity<>(viewPageCategoryService.listAllByParentId(id), HttpStatus.OK);
    }

    /**
     * 新增视图页面分类
     *
     * @param name     视图页面分类名
     * @param parentId 视图页面分类父级 id
     * @param sort     排序
     * @param remark   备注
     * @return ResponseEntity<ViewPageCategoryEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<ViewPageCategoryEntity> addOne(@RequestParam String name, @RequestParam(required = false) Long parentId, @RequestParam Long sort, @RequestParam String remark) {
        ViewPageCategoryEntity viewPageCategoryEntity = new ViewPageCategoryEntity();
        viewPageCategoryEntity.setName(name);
        viewPageCategoryEntity.setParentId(parentId);
        viewPageCategoryEntity.setSort(sort);
        viewPageCategoryEntity.setRemark(remark);
        ViewPageCategoryEntity viewPageCategoryEntity1 = viewPageCategoryService.insertOne(viewPageCategoryEntity);
        return new ResponseEntity<>(viewPageCategoryEntity1, HttpStatus.OK);
    }

    /**
     * 保存视图页面分类
     *
     * @param id     视图页面分类 id
     * @param name   视图分类名
     * @param sort   排序
     * @param remark 备注
     * @return ResponseEntity<ViewPageCategoryEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<ViewPageCategoryEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam Long sort, @RequestParam String remark) {
        ViewPageCategoryEntity viewPageCategoryEntity = new ViewPageCategoryEntity();
        viewPageCategoryEntity.setId(id);
        viewPageCategoryEntity.setName(name);
        viewPageCategoryEntity.setSort(sort);
        viewPageCategoryEntity.setRemark(remark);
        ViewPageCategoryEntity viewPageCategoryEntity1 = viewPageCategoryService.updateOne(viewPageCategoryEntity);
        return new ResponseEntity<>(viewPageCategoryEntity1, HttpStatus.OK);
    }

    /**
     * 指定视图页面分类 id ，批量删除视图页面分类
     *
     * @param idList 视图页面分类 id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) throws BaseResponseException {
        return new ResponseEntity<>(viewPageCategoryService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定视图页面分类 id ，获取视图页面分类信息
     *
     * @param id 视图页面分类 id
     * @return ResponseEntity<ViewPageCategoryEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ViewPageCategoryEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(viewPageCategoryService.getOne(id), HttpStatus.OK);
    }

}
