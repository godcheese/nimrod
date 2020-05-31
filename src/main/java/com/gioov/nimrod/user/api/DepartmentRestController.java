package com.gioov.nimrod.user.api;

import com.gioov.nimrod.common.easyui.ComboTree;
import com.gioov.nimrod.common.easyui.TreeGrid;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.entity.DepartmentEntity;
import com.gioov.nimrod.user.service.DepartmentService;
import com.gioov.tile.web.exception.BaseResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value = User.Api.DEPARTMENT, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentRestController.class);

    private static final String DEPARTMENT = "/API/USER/DEPARTMENT";

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取所有父级部门
     *
     * @return List<DepartmentEntity>
     */
    @OperationLog(value = "获取所有父级部门", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/LIST_ALL_PARENT')")
    @GetMapping(value = "/list_all_parent")
    public ResponseEntity<List<DepartmentEntity>> listAllParent() {
        return new ResponseEntity<>(departmentService.listAllParent(), HttpStatus.OK);
    }

    /**
     * 指定父级部门 id，获取所有子级部门
     *
     * @param parentId API 分类父级 id
     * @return ResponseEntity<List < DepartmentEntity>>
     */
    @OperationLog(value = "获取所有子级部门", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/LIST_ALL_BY_PARENT_ID')")
    @GetMapping(value = "/list_all_by_parent_id/{parentId}")
    public ResponseEntity<List<DepartmentEntity>> listAllByParentId(@PathVariable Long parentId) {
        return new ResponseEntity<>(departmentService.listAllByParentId(parentId), HttpStatus.OK);
    }

    /**
     * 新增部门
     *
     * @param name     部门名称
     * @param parentId 父级部门 id
     * @param remark   备注
     * @return ResponseEntity<DepartmentEntity>
     */
    @OperationLog(value = "新增部门", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<DepartmentEntity> addOne(@RequestParam String name, @RequestParam Long parentId, @RequestParam String remark) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName(name);
        departmentEntity.setParentId(parentId);
        departmentEntity.setRemark(remark);
        DepartmentEntity departmentEntity1 = departmentService.addOne(departmentEntity);
        return new ResponseEntity<>(departmentEntity1, HttpStatus.OK);
    }

    /**
     * 保存部门
     *
     * @param id       部门 id
     * @param name     部门名称
     * @param parentId 父级部门 id
     * @param remark   备注
     * @return ResponseEntity<DepartmentEntity>
     */
    @OperationLog(value = "保存部门", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<DepartmentEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam Long parentId, @RequestParam String remark) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(id);
        departmentEntity.setName(name);
        departmentEntity.setParentId(parentId);
        departmentEntity.setRemark(remark);
        DepartmentEntity departmentEntity1 = departmentService.saveOne(departmentEntity);
        return new ResponseEntity<>(departmentEntity1, HttpStatus.OK);
    }

    /**
     * 指定部门 id list，批量删除部门
     *
     * @param idList 部门 id list
     * @return ResponseEntity<Integer>
     */
    @OperationLog(value = "指定部门 id list，批量删除部门", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) throws BaseResponseException {
        return new ResponseEntity<>(departmentService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定部门 id，获取部门
     *
     * @param id 部门 id
     * @return ResponseEntity<DepartmentEntity>
     */
    @OperationLog(value = "指定部门 id，获取部门", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<DepartmentEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.getOne(id), HttpStatus.OK);
    }

    @OperationLog(value = "根据子节点获取所有父级节点部门", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/LIST_ALL_BY_DEPARTMENT_ID')")
    @GetMapping(value = "/list_all_by_department_id/{id}")
    public List<DepartmentEntity> listAllByDepartmentId(@PathVariable Long id) {
        List<DepartmentEntity> departmentEntityResultList = new ArrayList<>(0);
        List<DepartmentEntity> departmentEntityList = departmentService.listAll();

        DepartmentEntity departmentEntity = departmentService.getOne(id);
        departmentEntityResultList.add(departmentEntity);
        forEachParent(departmentEntity, departmentEntityList, departmentEntityResultList);
        Collections.reverse(departmentEntityResultList);
        return departmentEntityResultList;
    }

    public void forEachParent(DepartmentEntity departmentEntity, List<DepartmentEntity> departmentEntityList, List<DepartmentEntity> departmentEntityResultList) {
        for (DepartmentEntity entity : departmentEntityList) {
            if (departmentEntity.getParentId() != null) {
                if (departmentEntity.getParentId().equals(entity.getId())) {
                    departmentEntityResultList.add(entity);
                    forEachParent(entity, departmentEntityList, departmentEntityResultList);
                }
            }
        }
    }

    /**
     * 获取所有部门，以 EasyUI Combo Tree 形式展示
     *
     * @return Pagination<DepartmentEntity>
     */
    @OperationLog(value = "分页获取所有父级部门", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/LIST_ALL_AS_COMBO_TREE')")
    @GetMapping(value = "/list_all_as_combo_tree")
    public ResponseEntity<List<ComboTree>> listAllAsComboTree() {

        List<ComboTree> comboTreeResultList = new ArrayList<>();
        List<ComboTree> departmentComboTreeList = departmentService.listAllDepartmentComboTree();

        for (ComboTree comboTree : departmentComboTreeList) {
            if (comboTree.getParentId() == null) {
                comboTreeResultList.add(comboTree);
            }
        }

        for (ComboTree comboTree : comboTreeResultList) {
            comboTree.setChildren(departmentService.getDepartmentChildrenComboTree(comboTree.getId(), departmentComboTreeList));
        }

        return new ResponseEntity<>(comboTreeResultList, HttpStatus.OK);
    }

    /**
     * 获取所有部门，以 EasyUI TreeGrid 形式展示
     *
     * @return Pagination<DepartmentEntity>
     */
    @OperationLog(value = "分页获取所有父级部门", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/LIST_ALL_AS_COMBO_TREE')")
    @GetMapping(value = "/list_all_as_tree_grid")
    public ResponseEntity<List<TreeGrid>> listAllAsTreeGrid() {

        List<TreeGrid> treeGridResultList = new ArrayList<>();
        List<TreeGrid> departmentTreeGridList = departmentService.listAllDepartmentTreeGrid();

        for (TreeGrid treeGrid : departmentTreeGridList) {
            if (treeGrid.getParentId() == null) {
                treeGridResultList.add(treeGrid);
            }
        }

        for (TreeGrid treeGrid : treeGridResultList) {
            treeGrid.setChildren(departmentService.getDepartmentChildrenTreeGrid(treeGrid.getId(), departmentTreeGridList));
        }

        return new ResponseEntity<>(treeGridResultList, HttpStatus.OK);
    }

}
