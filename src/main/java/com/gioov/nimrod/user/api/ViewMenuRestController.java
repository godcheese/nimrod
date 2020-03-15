package com.gioov.nimrod.user.api;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.entity.ViewMenuEntity;
import com.gioov.nimrod.user.service.ViewMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value =  User.Api.VIEW_MENU, produces = MediaType.APPLICATION_JSON_VALUE)
public class ViewMenuRestController {

    private static final String VIEW_MENU = "/API/USER/VIEW_MENU";
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewMenuRestController.class);

    @Autowired
    private ViewMenuService viewMenuService;

    /**
     * 新增视图菜单
     * @param name 视图菜单名称
     * @param icon 图标（icon）
     * @param url 请求地址（url）
     * @param viewMenuCategoryId 视图菜单分类 id
     * @param sort 排序
     * @param remark 备注
     * @return ResponseEntity<ViewMenuEntity>
     */
    @OperationLog(value = "新增视图菜单", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<ViewMenuEntity> addOne(@RequestParam String name, @RequestParam(required = false) String icon, @RequestParam String url, @RequestParam Long viewMenuCategoryId, @RequestParam Long sort, @RequestParam String remark) {
        ViewMenuEntity viewMenuEntity = new ViewMenuEntity();
        viewMenuEntity.setName(name);
        viewMenuEntity.setIcon(icon);
        viewMenuEntity.setUrl(url);
        viewMenuEntity.setViewMenuCategoryId(viewMenuCategoryId);
        viewMenuEntity.setSort(sort);
        viewMenuEntity.setRemark(remark);
        ViewMenuEntity viewMenuEntity1 = viewMenuService.addOne(viewMenuEntity);
        return new ResponseEntity<>(viewMenuEntity1, HttpStatus.OK);
    }

    /**
     * 保存视图菜单
     * @param id 视图菜单 id
     * @param name 视图菜单名称
     * @param icon 图标（icon）
     * @param url 请求地址（url）
     * @param viewMenuCategoryId 视图菜单分类 id
     * @param sort 排序
     * @param remark 备注
     * @return ResponseEntity<ViewMenuEntity>
     */
    @OperationLog(value = "保存视图菜单", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<ViewMenuEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam(required = false) String icon, @RequestParam String url, @RequestParam Long viewMenuCategoryId, @RequestParam Long sort, @RequestParam String remark) {
        ViewMenuEntity viewMenuEntity = new ViewMenuEntity();
        viewMenuEntity.setId(id);
        viewMenuEntity.setName(name);
        viewMenuEntity.setIcon(icon);
        viewMenuEntity.setUrl(url);
        viewMenuEntity.setViewMenuCategoryId(viewMenuCategoryId);
        viewMenuEntity.setSort(sort);
        viewMenuEntity.setRemark(remark);
        ViewMenuEntity viewMenuEntity1 = viewMenuService.saveOne(viewMenuEntity);
        return new ResponseEntity<>(viewMenuEntity1, HttpStatus.OK);
    }

    /**
     * 指定视图菜单 id list，批量删除视图菜单
     * @param idList 视图菜单 id list
     * @return ResponseEntity<Integer>
     */
    @OperationLog(value = "指定视图菜单 id list，批量删除视图菜单", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(viewMenuService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定视图菜单 id，获取视图菜单
     * @param id 视图菜单 id
     * @return ResponseEntity<ViewMenuEntity>
     */
    @OperationLog(value = "指定视图菜单 id，获取视图菜单", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ViewMenuEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(viewMenuService.getOne(id), HttpStatus.OK);
    }

//    /**
//     * 指定视图菜单分类 id、角色 id，分页获取所有视图菜单
//     * @param viewMenuCategoryId 视图菜单分类 id
//     * @param roleId 角色 id
//     * @param page 页
//     * @param rows 每页显示数量
//     * @return ResponseEntity<Pagination<ViewMenuEntity>>
//     */
//    @OperationLog(value = "指定视图菜单分类 id、角色 id，分页获取所有视图菜单", type = OperationLogType.API)
//    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/PAGE_ALL_BY_VIEW_MENU_CATEGORY_ID_AND_ROLE_ID')")
//    @GetMapping(value = "/page_all_by_view_menu_category_id_and_role_id")
//    public ResponseEntity<Pagination<ViewMenuEntity>> pageAllByViewMenuCategoryIdAndRoleId(@RequestParam Integer page, @RequestParam Integer rows, @RequestParam Long viewMenuCategoryId, @RequestParam Long roleId) {
//        return new ResponseEntity<>(viewMenuService.pageAllByViewMenuCategoryIdAndRoleId(viewMenuCategoryId, roleId, page, rows), HttpStatus.OK);
//    }

    /**
     * 指定视图菜单分类 id，分页获取所有视图菜单
     * @param viewMenuCategoryId 视图菜单分类 id
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination<ViewMenuEntity>>
     */
    @OperationLog(value = "指定视图菜单分类 id，分页获取所有视图菜单", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/PAGE_ALL_BY_VIEW_MENU_CATEGORY_ID')")
    @GetMapping(value = "/page_all_by_view_menu_category_id")
    public ResponseEntity<Pagination<ViewMenuEntity>> pageAllByViewMenuCategoryId(@RequestParam Integer page, @RequestParam Integer rows, @RequestParam Long viewMenuCategoryId, @RequestParam(required = false) Long roleId) {
        return new ResponseEntity<>(viewMenuService.pageAllByViewMenuCategoryId(page, rows,viewMenuCategoryId, roleId), HttpStatus.OK);
    }

//    /**
//     * 指定菜单名，模糊搜索获取所有菜单
//     * @return ResponseEntity<List<ViewMenuEntity>>
//     */
//    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/SEARCH_ALL_BY_NAME')")
//    @GetMapping(value = "/search_all_by_name")
//    public ResponseEntity<List<ViewMenuEntity>> searchAllByName(@RequestParam String q) {
//        return new ResponseEntity<>(viewMenuService.searchAllByName(q), HttpStatus.OK);
//    }

}
