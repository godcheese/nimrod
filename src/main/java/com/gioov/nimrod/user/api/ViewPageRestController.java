package com.gioov.nimrod.user.api;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.entity.ViewPageEntity;
import com.gioov.nimrod.user.service.ViewPageService;
import com.gioov.tile.web.exception.BaseResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value = User.Api.VIEW_PAGE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ViewPageRestController {

    private static final String VIEW_PAGE = "/API/SYSTEM/VIEW_PAGE";

    @Autowired
    private ViewPageService viewPageService;

    /**
     * 新增视图页面
     *
     * @param name               视图页面名称
     * @param url                请求地址（url）
     * @param authority          权限（authority）
     * @param viewPageCategoryId 视图页面分类 id
     * @param sort               排序
     * @param remark             备注
     * @return ResponseEntity<ViewPageEntity>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "新增视图页面", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<ViewPageEntity> addOne(@RequestParam String name, @RequestParam String url, @RequestParam String authority, @RequestParam Long viewPageCategoryId, @RequestParam Long sort, @RequestParam String remark) throws BaseResponseException {
        ViewPageEntity viewPageEntity = new ViewPageEntity();
        viewPageEntity.setName(name);
        viewPageEntity.setUrl(url);
        viewPageEntity.setAuthority(authority);
        viewPageEntity.setViewPageCategoryId(viewPageCategoryId);
        viewPageEntity.setSort(sort);
        viewPageEntity.setRemark(remark);
        ViewPageEntity viewPageEntity1 = viewPageService.addOne(viewPageEntity);
        return new ResponseEntity<>(viewPageEntity1, HttpStatus.OK);
    }

    /**
     * 保存视图页面
     *
     * @param id                 视图页面 id
     * @param name               视图页面名称
     * @param url                请求地址（url）
     * @param authority          权限（authority）
     * @param viewPageCategoryId 视图页面分类 id
     * @param sort               排序
     * @param remark             备注
     * @return ResponseEntity<ViewPageEntity>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "保存视图页面", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<ViewPageEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam String url, @RequestParam String authority, @RequestParam Long viewPageCategoryId, @RequestParam Long sort, @RequestParam String remark) throws BaseResponseException {
        ViewPageEntity viewPageEntity = new ViewPageEntity();
        viewPageEntity.setId(id);
        viewPageEntity.setName(name);
        viewPageEntity.setUrl(url);
        viewPageEntity.setAuthority(authority);
        viewPageEntity.setViewPageCategoryId(viewPageCategoryId);
        viewPageEntity.setSort(sort);
        viewPageEntity.setRemark(remark);
        ViewPageEntity viewPageEntity1 = viewPageService.saveOne(viewPageEntity);
        return new ResponseEntity<>(viewPageEntity1, HttpStatus.OK);
    }

    /**
     * 指定视图页面 id，批量删除视图页面
     *
     * @param idList 视图页面 id list
     * @return ResponseEntity<Integer>
     */
    @OperationLog(value = "指定视图页面 id，批量删除视图页面", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(viewPageService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定视图页面 id，获取视图页面
     *
     * @param id 视图页面 id
     * @return ResponseEntity<ViewPageEntity>
     */
    @OperationLog(value = "指定视图页面 id，获取视图页面", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ViewPageEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(viewPageService.getOne(id), HttpStatus.OK);
    }

    /**
     * 指定视图页面分类 id，分页获取所有视图页面
     *
     * @param page               页
     * @param rows               每页显示数量
     * @param viewPageCategoryId 视图页面分类
     * @return ResponseEntity<Pagination < ViewPageEntity>>
     */
    @OperationLog(value = "指定视图页面分类 id，分页获取所有视图页面", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE + "/PAGE_ALL_BY_VIEW_PAGE_CATEGORY_ID')")
    @GetMapping(value = "/page_all_by_view_page_category_id")
    public ResponseEntity<Pagination<ViewPageEntity>> pageAllByViewPageCategoryId(@RequestParam Integer page, @RequestParam Integer rows, @RequestParam Long viewPageCategoryId, @RequestParam(required = false) Long roleId) {
        return new ResponseEntity<>(viewPageService.pageAllByViewPageCategoryId(page, rows, viewPageCategoryId, roleId), HttpStatus.OK);
    }
}
