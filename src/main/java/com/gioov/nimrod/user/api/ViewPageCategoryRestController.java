package com.gioov.nimrod.user.api;

import com.gioov.nimrod.common.easyui.ComboTree;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.entity.ViewPageCategoryEntity;
import com.gioov.nimrod.user.service.ViewPageCategoryService;
import com.gioov.tile.web.exception.BaseResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value = User.Api.VIEW_PAGE_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
public class ViewPageCategoryRestController {

    private static final String VIEW_PAGE_CATEGORY = "/API/SYSTEM/VIEW_PAGE_CATEGORY";

    @Autowired
    private ViewPageCategoryService viewPageCategoryService;

    /**
     * 新增视图页面分类
     *
     * @param name     视图页面分类名称
     * @param parentId 父级视图菜单 id
     * @param sort     排序
     * @param remark   备注
     * @return ResponseEntity<ViewPageCategoryEntity>
     */
    @OperationLog(value = "新增视图页面分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<ViewPageCategoryEntity> addOne(@RequestParam String name, @RequestParam(required = false) Long parentId, @RequestParam Long sort, @RequestParam String remark) {
        ViewPageCategoryEntity viewPageCategoryEntity = new ViewPageCategoryEntity();
        viewPageCategoryEntity.setName(name);
        viewPageCategoryEntity.setParentId(parentId);
        viewPageCategoryEntity.setSort(sort);
        viewPageCategoryEntity.setRemark(remark);
        ViewPageCategoryEntity viewPageCategoryEntity1 = viewPageCategoryService.addOne(viewPageCategoryEntity);
        return new ResponseEntity<>(viewPageCategoryEntity1, HttpStatus.OK);
    }

    /**
     * 保存视图页面分类
     *
     * @param id       视图页面分类 id
     * @param name     视图页面分类名称
     * @param parentId 父级视图页面分类 id
     * @param sort     排序
     * @param remark   备注
     * @return ResponseEntity<ViewPageCategoryEntity>
     */
    @OperationLog(value = "保存视图页面分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<ViewPageCategoryEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam Long parentId, @RequestParam Long sort, @RequestParam String remark) {
        ViewPageCategoryEntity viewPageCategoryEntity = new ViewPageCategoryEntity();
        viewPageCategoryEntity.setId(id);
        viewPageCategoryEntity.setName(name);
        viewPageCategoryEntity.setParentId(parentId);
        viewPageCategoryEntity.setSort(sort);
        viewPageCategoryEntity.setRemark(remark);
        ViewPageCategoryEntity viewPageCategoryEntity1 = viewPageCategoryService.saveOne(viewPageCategoryEntity);
        return new ResponseEntity<>(viewPageCategoryEntity1, HttpStatus.OK);
    }

    /**
     * 指定视图页面分类 id，批量删除视图页面分类
     *
     * @param idList 视图页面分类 id list
     * @return ResponseEntity<Integer>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "指定视图页面分类 id，批量删除视图页面分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) throws BaseResponseException {
        return new ResponseEntity<>(viewPageCategoryService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定视图页面分类 id，获取视图页面分类
     *
     * @param id 视图页面分类 id
     * @return ResponseEntity<ViewPageCategoryEntity>
     */
    @OperationLog(value = "指定视图页面分类 id，获取视图页面分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ViewPageCategoryEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(viewPageCategoryService.getOne(id), HttpStatus.OK);
    }

    /**
     * 获取所有父级视图页面分类
     *
     * @return ResponseEntity<List < ViewPageCategoryEntity>>
     */
    @OperationLog(value = "获取所有父级视图页面分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/LIST_ALL_PARENT')")
    @GetMapping(value = "/list_all_parent")
    public ResponseEntity<List<ViewPageCategoryEntity>> listAllParent() {
        return new ResponseEntity<>(viewPageCategoryService.listAllParent(), HttpStatus.OK);
    }

    /**
     * 指定父级视图页面分类 id，获取所有视图页面分类
     *
     * @param parentId 父级视图页面分类 id
     * @return ResponseEntity<List < ViewPageCategoryEntity>>
     */
    @OperationLog(value = "指定父级视图页面分类 id，获取所有视图页面分类", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/LIST_ALL_BY_PARENT_ID')")
    @GetMapping(value = "/list_all_by_parent_id")
    public ResponseEntity<List<ViewPageCategoryEntity>> listAllByParentId(@RequestParam Long parentId) {
        return new ResponseEntity<>(viewPageCategoryService.listAllByParentId(parentId), HttpStatus.OK);
    }

    /**
     * 获取所有视图页面分类，以 ComboTree 形式展示
     *
     * @return ResponseEntity<List < ComboTree>>
     */
    @OperationLog(value = "获取所有视图页面分类，以 ComboTree 形式展示", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_CATEGORY + "/LIST_ALL_AS_COMBO_TREE')")
    @GetMapping(value = "/list_all_as_combo_tree")
    public ResponseEntity<List<ComboTree>> listAllAsComboTree() {
        List<ComboTree> comboTreeResultList = new ArrayList<>();
        List<ComboTree> viewPageCategoryComboTreeList = viewPageCategoryService.listAllViewPageCategoryComboTree();
        for (ComboTree comboTree : viewPageCategoryComboTreeList) {
            if (comboTree.getParentId() == null) {
                comboTreeResultList.add(comboTree);
            }
        }
        for (ComboTree comboTree : comboTreeResultList) {
            comboTree.setChildren(viewPageCategoryService.getViewPageCategoryChildrenComboTree(comboTree.getId(), viewPageCategoryComboTreeList));
        }
        return new ResponseEntity<>(comboTreeResultList, HttpStatus.OK);
    }
}
