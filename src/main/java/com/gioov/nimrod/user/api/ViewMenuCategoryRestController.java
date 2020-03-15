package com.gioov.nimrod.user.api;

import com.gioov.nimrod.common.easyui.ComboTree;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.entity.ViewMenuCategoryEntity;
import com.gioov.nimrod.user.service.ViewMenuCategoryService;
import com.gioov.tile.web.exception.BaseResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value = User.Api.VIEW_MENU_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
public class ViewMenuCategoryRestController {

    private static final String VIEW_MENU_CATEGORY = "/API/USER/VIEW_MENU_CATEGORY";

    @Autowired
    private ViewMenuCategoryService viewMenuCategoryService;

    /**
     * 获取所有父级视图菜单分类
     * @return ResponseEntity<List<ViewMenuCategoryEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/LIST_ALL_PARENT')")
    @GetMapping(value = "/list_all_parent")
    public ResponseEntity<List<ViewMenuCategoryEntity>> listAllParent(@RequestParam(required = false) Long roleId) {
        return new ResponseEntity<>(viewMenuCategoryService.listAllParent(roleId), HttpStatus.OK);
    }

    /**
     * 指定父级视图菜单分类 id，获取所有视图菜单分类
     * @param parentId 父级视图菜单分类 id
     * @return ResponseEntity<List<ViewMenuCategoryEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/LIST_ALL_BY_PARENT_ID')")
    @GetMapping(value = "/list_all_by_parent_id")
    public ResponseEntity<Object> listAllByParentId(@RequestParam Long parentId, @RequestParam(required = false) Long roleId) {
        return new ResponseEntity<>(viewMenuCategoryService.listAllByParentId(parentId, roleId), HttpStatus.OK);
    }

    /**
     * 新增视图菜单分类
     * @param name 视图菜单分类名称
     * @param icon 图标（icon）
     * @param parentId 父级视图菜单分类 id
     * @param sort 排序
     * @param remark 备注
     * @return <ViewMenuCategoryEntity>
     */
    @OperationLog(value = "新增视图菜单分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<ViewMenuCategoryEntity> addOne(@RequestParam String name, @RequestParam(required = false) String icon, @RequestParam(required = false) Long parentId, @RequestParam Long sort, @RequestParam String remark) {
        ViewMenuCategoryEntity viewMenuCategoryEntity = new ViewMenuCategoryEntity();
        viewMenuCategoryEntity.setName(name);
        viewMenuCategoryEntity.setIcon(icon);
        viewMenuCategoryEntity.setParentId(parentId);
        viewMenuCategoryEntity.setSort(sort);
        viewMenuCategoryEntity.setRemark(remark);
        ViewMenuCategoryEntity viewMenuCategoryEntity1 = viewMenuCategoryService.addOne(viewMenuCategoryEntity);
        return new ResponseEntity<>(viewMenuCategoryEntity1, HttpStatus.OK);
    }

    /**
     * 保存视图菜单分类
     * @param id 视图菜单分类 id
     * @param name 视图菜单分类名称
     * @param icon 图标（icon）
     * @param sort 排序
     * @param remark 备注
     * @return ResponseEntity<ViewMenuCategoryEntity>
     */
    @OperationLog(value = "保存视图菜单分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<ViewMenuCategoryEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam(required = false) String icon, @RequestParam Long sort, @RequestParam String remark) {
        ViewMenuCategoryEntity viewMenuCategoryEntity = new ViewMenuCategoryEntity();
        viewMenuCategoryEntity.setId(id);
        viewMenuCategoryEntity.setName(name);
        viewMenuCategoryEntity.setIcon(icon);
        viewMenuCategoryEntity.setSort(sort);
        viewMenuCategoryEntity.setRemark(remark);
        ViewMenuCategoryEntity viewMenuCategoryEntity1 = viewMenuCategoryService.saveOne(viewMenuCategoryEntity);
        return new ResponseEntity<>(viewMenuCategoryEntity1, HttpStatus.OK);
    }

    /**
     * 指定视图菜单分类 id，批量删除视图菜单分类
     * @param idList 视图菜单分类 id list
     * @return ResponseEntity<Integer>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "指定视图菜单分类 id，批量删除视图菜单分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) throws BaseResponseException {
        return new ResponseEntity<>(viewMenuCategoryService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定视图菜单分类 id，获取视图菜单分类
     * @param id 视图菜单分类 id
     * @return ResponseEntity<ViewMenuCategoryEntity>
     */
    @OperationLog(value = "指定视图菜单分类 id，获取视图菜单分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ViewMenuCategoryEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(viewMenuCategoryService.getOne(id), HttpStatus.OK);
    }


    /**
     * 指定角色 id，获取所有父级视图菜单分类
     * @param roleId 角色 id
     * @return ResponseEntity<List<ViewMenuCategoryEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/LIST_ALL_PARENT_BY_ROLE_ID')")
    @GetMapping(value = "/list_all_parent_by_role_id/{roleId}")
    public ResponseEntity<List<ViewMenuCategoryEntity>> listAllParentByRoleId(@PathVariable Long roleId) {
        return new ResponseEntity<>(viewMenuCategoryService.listAllParentByRoleId(roleId), HttpStatus.OK);
    }

    /**
     * 指定用户 id，获取所有父级视图菜单分类
     * @param userId 用户 id
     * @return ResponseEntity<List<ViewMenuCategoryEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/LIST_ALL_PARENT_BY_USER_ID')")
    @GetMapping(value = "/list_all_parent_by_user_id/{userId}")
    public ResponseEntity<List<ViewMenuCategoryEntity>> listAllParentByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(viewMenuCategoryService.listAllParentByUserId(userId), HttpStatus.OK);
    }

    /**
     * 指定用户 id、父级视图菜单分类 id，获取所有子级视图菜单分类
     * @param userId   用户 id
     * @param parentId 视图菜单分类父级 id
     * @return ResponseEntity<? extends Object>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/LIST_ALL_CHILD_BY_PARENT_ID_AND_USER_ID')")
    @GetMapping(value = "/list_all_child_by_parent_id_and_user_id")
    public ResponseEntity<Object> listAllChildByParentIdAndUserId(@RequestParam Long parentId, @RequestParam Long userId) {
        return new ResponseEntity<>(viewMenuCategoryService.listAllChildByParentIdAndUserId(parentId, userId), HttpStatus.OK);
    }

    /**
     * 指定用户 id、父级视图菜单分类 id，获取所有子级视图菜单分类和视图菜单
     * @param userId   用户 id
     * @param parentId 视图菜单分类父级 id
     * @return ResponseEntity< List<Map<String, Object>> >
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/LIST_ALL_CHILD_VIEW_MENU_CATEGORY_AND_VIEW_MENU_BY_PARENT_ID_AND_USER_ID')")
    @GetMapping(value = "/list_all_child_view_menu_category_and_view_menu_by_parent_id_and_user_id")
    public ResponseEntity<List<Map<String, Object>> > listAllChildViewMenuCategoryAndViewMenuByParentIdAndUserId(@RequestParam Long parentId, @RequestParam Long userId) {
        return new ResponseEntity<>(viewMenuCategoryService.listAllChildViewMenuCategoryAndViewMenuByParentIdAndUserId(parentId, userId), HttpStatus.OK);
    }

    /**
     * 获取所有菜单分类
     * @return ResponseEntity<? extends Object>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/LIST_ALL')")
    @GetMapping(value = "/list_all")
    public ResponseEntity<Object> listAll() {
        return new ResponseEntity<>(viewMenuCategoryService.listAll(), HttpStatus.OK);
    }

    /**
     * 指定菜单分类名，模糊搜索获取所有菜单分类
     * @return ResponseEntity<? extends  Object>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/SEARCH_ALL_BY_NAME')")
    @GetMapping(value = "/search_all_by_name")
    public ResponseEntity<Object> searchAllByName(@RequestParam String q) {
        return new ResponseEntity<>(viewMenuCategoryService.searchAllByName(q), HttpStatus.OK);
    }

    /**
     * 获取所有视图菜单分类，以 ComboTree 形式展示
     * @return ResponseEntity<List<ComboTree>>
     */
    @OperationLog(value = "获取所有视图菜单分类，以 ComboTree 形式展示", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_MENU_CATEGORY + "/LIST_ALL_AS_COMBO_TREE')")
    @GetMapping(value = "/list_all_as_combo_tree")
    public ResponseEntity<List<ComboTree>> listAllAsComboTree() {
        List<ComboTree> comboTreeResultList = new ArrayList<>();
        List<ComboTree> viewMenuCategoryComboTreeList = viewMenuCategoryService.listAllViewMenuCategoryComboTree();
        for(ComboTree comboTree : viewMenuCategoryComboTreeList) {
            if(comboTree.getParentId() == null) {
                comboTreeResultList.add(comboTree);
            }
        }
        for(ComboTree comboTree : comboTreeResultList) {
            comboTree.setChildren(viewMenuCategoryService.getViewMenuCategoryChildrenComboTree(comboTree.getId(), viewMenuCategoryComboTreeList));
        }
        return new ResponseEntity<>(comboTreeResultList, HttpStatus.OK);
    }
}
