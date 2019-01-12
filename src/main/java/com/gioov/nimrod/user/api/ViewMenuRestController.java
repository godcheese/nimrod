package com.gioov.nimrod.user.api;

import com.gioov.nimrod.common.constant.Api;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.ViewMenuEntity;
import com.gioov.nimrod.user.service.ViewMenuService;
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
@RequestMapping(value = Api.User.VIEW_MENU, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ViewMenuRestController {

    private static final String VIEW_MENU = "/API/USER/VIEW_MENU";

    @Autowired
    private ViewMenuService viewMenuService;

    /**
     * 指定视图菜单分类 id 、角色 id ，分页获取所有视图菜单
     *
     * @param menuCategoryId 视图菜单分类 id
     * @param roleId         角色 id
     * @param page           页
     * @param rows           每页显示数量
     * @return ResponseEntity<Pagination.Result < ViewMenuEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/PAGE_ALL_BY_MENU_CATEGORY_id_AND_ROLE_ID')")
    @GetMapping(value = "/page_all_by_menu_category_id_and_role_id")
    public ResponseEntity<Pagination.Result<ViewMenuEntity>> pageAllByMenuCategoryIdAndRoleId(@RequestParam Long menuCategoryId, @RequestParam Long roleId, @RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(viewMenuService.pageAllByMenuCategoryIdAndRoleId(menuCategoryId, roleId, page, rows), HttpStatus.OK);
    }

    /**
     * 新增视图菜单
     *
     * @param name           视图菜单名
     * @param icon           图标（icon）
     * @param url            url
     * @param menuCategoryId 视图菜单分类 id
     * @param sort           排序
     * @param remark         备注
     * @param roleId         角色 id
     * @return ResponseEntity<ViewMenuEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<ViewMenuEntity> addOne(@RequestParam String name, @RequestParam(required = false) String icon, @RequestParam String url, @RequestParam Long menuCategoryId, @RequestParam Long sort, @RequestParam String remark, @RequestParam Long roleId) {
        ViewMenuEntity viewMenuEntity = new ViewMenuEntity();
        viewMenuEntity.setName(name);
        viewMenuEntity.setIcon(icon);
        viewMenuEntity.setUrl(url);
        viewMenuEntity.setMenuCategoryId(menuCategoryId);
        viewMenuEntity.setSort(sort);
        viewMenuEntity.setRemark(remark);
        viewMenuEntity.setRoleId(roleId);
        ViewMenuEntity viewMenuEntity1 = viewMenuService.insertOne(viewMenuEntity);
        return new ResponseEntity<>(viewMenuEntity1, HttpStatus.OK);
    }

    /**
     * 保存视图菜单
     *
     * @param id     视图菜单 id
     * @param name   视图菜单名
     * @param icon   图标（icon）
     * @param url    url
     * @param sort   排序
     * @param remark 备注
     * @return ResponseEntity<ViewMenuEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<ViewMenuEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam(required = false) String icon, @RequestParam String url, @RequestParam Long sort, @RequestParam String remark) {
        ViewMenuEntity viewMenuEntity = new ViewMenuEntity();
        viewMenuEntity.setId(id);
        viewMenuEntity.setName(name);
        viewMenuEntity.setIcon(icon);
        viewMenuEntity.setUrl(url);
        viewMenuEntity.setSort(sort);
        viewMenuEntity.setRemark(remark);
        ViewMenuEntity viewMenuEntity1 = viewMenuService.updateOne(viewMenuEntity);
        return new ResponseEntity<>(viewMenuEntity1, HttpStatus.OK);
    }

    /**
     * 指定视图菜单 id ，批量删除视图菜单
     *
     * @param idList 视图菜单 id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(viewMenuService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定视图菜单 id ，获取视图菜单信息
     *
     * @param id 视图菜单 id
     * @return ResponseEntity<ViewMenuEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ViewMenuEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(viewMenuService.getOne(id), HttpStatus.OK);
    }

    /**
     * 指定菜单名，模糊搜索获取所有菜单
     *
     * @return ResponseEntity<List                               <                               ViewMenuEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU + "/SEARCH_ALL_BY_NAME')")
    @GetMapping(value = "/search_all_by_name")
    public ResponseEntity<List<ViewMenuEntity>> searchAllByName(@RequestParam String q) {
        return new ResponseEntity<>(viewMenuService.searchAllByName(q), HttpStatus.OK);
    }

}
