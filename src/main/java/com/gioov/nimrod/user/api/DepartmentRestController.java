package com.gioov.nimrod.user.api;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.constant.Api;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.user.entity.DepartmentEntity;
import com.gioov.nimrod.user.service.DepartmentService;
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
@RequestMapping(value = Api.User.DEPARTMENT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DepartmentRestController {

    private static final String DEPARTMENT = "/API/USER/DEPARTMENT";

    @Autowired
    private DepartmentService departmentService;

    /**
     * 分页获取所有父级部门
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination.Result<DepartmentEntity>
     */
    @OperationLog(value = "分页获取所有父级部门", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/PAGE_ALL_PARENT')")
    @GetMapping(value = "/page_all_parent")
    public ResponseEntity<Pagination.Result<DepartmentEntity>> pageAllParent(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(departmentService.pageAllByParentIdIsNull(page, rows), HttpStatus.OK);
    }

    /**
     * 指定父级部门 id ，获取所有子级部门
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
     * @param name   部门名称
     * @param remark 备注
     * @return ResponseEntity<ApiEntity>
     */
    @OperationLog("新增部门")
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<DepartmentEntity> addOne(@RequestParam String name, @RequestParam Long parentId, @RequestParam String remark) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName(name);
        departmentEntity.setParentId(parentId);
        departmentEntity.setRemark(remark);
        DepartmentEntity departmentEntity1 = departmentService.insertOne(departmentEntity);
        return new ResponseEntity<>(departmentEntity1, HttpStatus.OK);
    }

    /**
     * 保存部门
     *
     * @param id     部门 id
     * @param name   部门名称
     * @param remark 备注
     * @return ResponseEntity<DepartmentEntity>
     */
    @OperationLog("保存部门")
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<DepartmentEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam String remark) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(id);
        departmentEntity.setName(name);
        departmentEntity.setRemark(remark);
        DepartmentEntity departmentEntity1 = departmentService.updateOne(departmentEntity);
        return new ResponseEntity<>(departmentEntity1, HttpStatus.OK);
    }

    /**
     * 指定部门 id list ，批量删除部门
     *
     * @param idList 部门 id list
     * @return ResponseEntity<Integer>
     */
    @OperationLog("批量删除部门")
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) throws BaseResponseException {
        return new ResponseEntity<>(departmentService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定部门 id ， 获取部门信息
     *
     * @param id 部门 id
     * @return ResponseEntity<DepartmentEntity>
     */
    @OperationLog("获取部门信息")
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + DEPARTMENT + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<DepartmentEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.getOne(id), HttpStatus.OK);
    }

}
