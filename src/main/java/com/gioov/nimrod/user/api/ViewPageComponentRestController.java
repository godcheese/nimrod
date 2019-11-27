package com.gioov.nimrod.user.api;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.entity.ViewPageComponentEntity;
import com.gioov.nimrod.user.service.ViewPageComponentService;
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
@RequestMapping(value = User.Api.VIEW_PAGE_COMPONENT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ViewPageComponentRestController {

    private static final String VIEW_PAGE_COMPONENT = "/API/SYSTEM/VIEW_PAGE_COMPONENT";

    @Autowired
    private ViewPageComponentService viewPageComponentService;

    /**
     * 新增视图页面组件
     * @param viewPageComponentType 视图页面组件类型
     * @param name 视图页面组件名称
     * @param authority 权限（authority）
     * @param viewPageId 视图页面 id
     * @param sort 排序
     * @param remark 备注
     * @return ResponseEntity<ViewPageComponentEntity>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "新增视图页面组件", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<ViewPageComponentEntity> addOne(@RequestParam Long viewPageComponentType, @RequestParam String name, @RequestParam String authority, @RequestParam Long viewPageId, @RequestParam Long sort, @RequestParam String remark) throws BaseResponseException {
        ViewPageComponentEntity viewPageComponentEntity = new ViewPageComponentEntity();
        viewPageComponentEntity.setViewPageComponentType(viewPageComponentType);
        viewPageComponentEntity.setName(name);
        viewPageComponentEntity.setAuthority(authority);
        viewPageComponentEntity.setViewPageId(viewPageId);
        viewPageComponentEntity.setSort(sort);
        viewPageComponentEntity.setRemark(remark);
        ViewPageComponentEntity viewPageComponentEntity1 = viewPageComponentService.addOne(viewPageComponentEntity);
        return new ResponseEntity<>(viewPageComponentEntity1, HttpStatus.OK);
    }

    /**
     * 保存视图页面组件
     * @param id 视图页面组件 id
     * @param viewPageComponentType 视图页面组件类型
     * @param name 视图页面组件名称
     * @param authority 权限（authority）
     * @param sort 排序
     * @param remark 备注
     * @return ResponseEntity<ViewPageComponentEntity>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "保存视图页面组件", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<ViewPageComponentEntity> saveOne(@RequestParam Long id, @RequestParam Long viewPageComponentType, @RequestParam String name, @RequestParam String authority, @RequestParam Long sort, @RequestParam String remark) throws BaseResponseException {
        ViewPageComponentEntity viewPageComponentEntity = new ViewPageComponentEntity();
        viewPageComponentEntity.setId(id);
        viewPageComponentEntity.setName(name);
        viewPageComponentEntity.setViewPageComponentType(viewPageComponentType);
        viewPageComponentEntity.setAuthority(authority);
        viewPageComponentEntity.setSort(sort);
        viewPageComponentEntity.setRemark(remark);
        ViewPageComponentEntity viewPageComponentEntity1 = viewPageComponentService.saveOne(viewPageComponentEntity);
        return new ResponseEntity<>(viewPageComponentEntity1, HttpStatus.OK);
    }

    /**
     * 指定视图页面组件 id，批量删除视图页面组件
     * @param idList 视图页面组件 id list
     * @return ResponseEntity<Integer>
     */
    @OperationLog(value = "指定视图页面组件 id，批量删除视图页面组件", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(viewPageComponentService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定视图组件 id，获取视图组件
     * @param id 视图页面组件 id
     * @return ResponseEntity<ViewPageComponentEntity>
     */
    @OperationLog(value = "指定视图组件 id，获取视图组件", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ViewPageComponentEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(viewPageComponentService.getOne(id), HttpStatus.OK);
    }

    /**
     * 指定视图页面 id，分页获取所有视图页面组件
     * @param viewPageId 视图页面 id
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination<ViewPageComponentEntity>>
     */
    @OperationLog(value = "指定视图页面 id，分页获取所有视图页面组件", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT + "/PAGE_ALL_BY_VIEW_PAGE_ID')")
    @GetMapping(value = "/page_all_by_view_page_id")
    public ResponseEntity<Pagination<ViewPageComponentEntity>> pageAllByViewPageId(@RequestParam Integer page, @RequestParam Integer rows, @RequestParam Long viewPageId, @RequestParam Long roleId) {
        return new ResponseEntity<>(viewPageComponentService.pageAllByViewPageId(page, rows, viewPageId, roleId), HttpStatus.OK);
    }
}
