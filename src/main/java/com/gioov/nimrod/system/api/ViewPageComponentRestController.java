package com.gioov.nimrod.system.api;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.constant.Api;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ViewPageComponentEntity;
import com.gioov.nimrod.system.service.ViewPageComponentService;
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
@RequestMapping(value = Api.System.VIEW_PAGE_COMPONENT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ViewPageComponentRestController {

    private static final String VIEW_PAGE_COMPONENT = "/API/SYSTEM/VIEW_PAGE_COMPONENT";

    @Autowired
    private ViewPageComponentService viewPageComponentService;

    /**
     * 分页获取所有视图页面组件
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination.Result                               <                               ViewPageComponentEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT + "/PAGE_ALL_BY_PAGE_ID')")
    @GetMapping(value = "/page_all_by_page_id/{pageId}")
    public ResponseEntity<Pagination.Result<ViewPageComponentEntity>> pageAllByPageId(@PathVariable Long pageId, @RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(viewPageComponentService.pageAllByPageId(pageId, page, rows), HttpStatus.OK);
    }

    /**
     * 新增视图页面组件
     *
     * @param pageComponentType 视图页面组件类型
     * @param name              视图页面组件名
     * @param authority         权限（authority）
     * @param pageId            视图页面 id
     * @param sort              排序
     * @param remark            备注
     * @return ViewPageComponentEntity
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<ViewPageComponentEntity> addOne(@RequestParam Long pageComponentType, @RequestParam String name, @RequestParam String authority, @RequestParam Long pageId, @RequestParam Long sort, @RequestParam String remark) throws BaseResponseException {
        ViewPageComponentEntity viewPageComponentEntity = new ViewPageComponentEntity();
        viewPageComponentEntity.setPageComponentType(pageComponentType);
        viewPageComponentEntity.setName(name);
        viewPageComponentEntity.setAuthority(authority);
        viewPageComponentEntity.setPageId(pageId);
        viewPageComponentEntity.setSort(sort);
        viewPageComponentEntity.setRemark(remark);
        ViewPageComponentEntity viewPageComponentEntity1 = viewPageComponentService.insertOne(viewPageComponentEntity);
        return new ResponseEntity<>(viewPageComponentEntity1, HttpStatus.OK);
    }

    /**
     * 保存视图页面组件
     *
     * @param id                视图页面组件 id
     * @param pageComponentType 视图页面组件类型
     * @param name              视图页面组件名
     * @param authority         权限（authority）
     * @param sort              排序
     * @param remark            备注
     * @return ViewPageComponentEntity
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<ViewPageComponentEntity> saveOne(@RequestParam Long id, @RequestParam Long pageComponentType, @RequestParam String name, @RequestParam String authority, @RequestParam Long sort, @RequestParam String remark) throws BaseResponseException {
        ViewPageComponentEntity viewPageComponentEntity = new ViewPageComponentEntity();
        viewPageComponentEntity.setPageComponentType(pageComponentType);
        viewPageComponentEntity.setName(name);
        viewPageComponentEntity.setAuthority(authority);
        viewPageComponentEntity.setSort(sort);
        viewPageComponentEntity.setRemark(remark);
        ViewPageComponentEntity viewPageComponentEntity1 = viewPageComponentService.updateOne(viewPageComponentEntity);
        return new ResponseEntity<>(viewPageComponentEntity1, HttpStatus.OK);
    }

    /**
     * 指定视图页面组件 id ，批量删除视图页面组件
     *
     * @param idList 视图页面组件 id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(viewPageComponentService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定视图组件 id ，获取视图组件信息
     *
     * @param id 视图页面组件 id
     * @return ResponseEntity<ViewPageComponentEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ViewPageComponentEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(viewPageComponentService.getOne(id), HttpStatus.OK);
    }

}
