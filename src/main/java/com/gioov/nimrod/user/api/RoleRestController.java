package com.gioov.nimrod.user.api;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.entity.RoleEntity;
import com.gioov.nimrod.user.service.RoleService;
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
@RequestMapping(value = User.Api.ROLE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleRestController {

    private static final String ROLE = "/API/USER/ROLE";

    @Autowired
    private RoleService roleService;

    /**
     * 分页获取所有角色
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination < RoleEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE + "/PAGE_ALL')")
    @GetMapping(value = "/page_all")
    public ResponseEntity<Pagination<RoleEntity>> pageAll(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(roleService.pageAll(page, rows), HttpStatus.OK);
    }

    /**
     * 获取所有角色
     *
     * @return ResponseEntity<List < RoleEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE + "/LIST_ALL')")
    @GetMapping(value = "/list_all")
    public ResponseEntity<List<RoleEntity>> listAll() {
        return new ResponseEntity<>(roleService.listAll(), HttpStatus.OK);
    }

    /**
     * 指定用户 id，获取用户角色
     *
     * @param userId 用户 id
     * @return ResponseEntity<List < RoleEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE + "/ONE')")
    @GetMapping(value = "/list_all_by_user_id/{userId}")
    public ResponseEntity<List<RoleEntity>> listAllByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(roleService.listAllByUserId(userId), HttpStatus.OK);
    }

    /**
     * 新增角色
     *
     * @param name   角色名称
     * @param value  角色值
     * @param remark 备注
     * @return ResponseEntity<RoleEntity>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "新增角色", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<RoleEntity> addOne(@RequestParam String name, @RequestParam String value, @RequestParam String remark) throws BaseResponseException {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(name);
        roleEntity.setValue(value);
        roleEntity.setRemark(remark);
        RoleEntity roleEntity1 = roleService.addOne(roleEntity);
        return new ResponseEntity<>(roleEntity1, HttpStatus.OK);
    }

    /**
     * 保存角色
     *
     * @param id     角色 id
     * @param name   角色名称
     * @param value  角色值
     * @param remark 备注
     * @return ResponseEntity<RoleEntity>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "保存角色", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<RoleEntity> saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam String value, @RequestParam String remark) throws BaseResponseException {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(id);
        roleEntity.setName(name);
        roleEntity.setValue(value);
        roleEntity.setRemark(remark);
        RoleEntity roleEntity1 = roleService.saveOne(roleEntity);
        return new ResponseEntity<>(roleEntity1, HttpStatus.OK);
    }

    /**
     * 指定角色 id list，批量删除角色
     *
     * @param idList 角色 id list
     * @return ResponseEntity<Integer>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "指定角色 id list，批量删除角色", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) throws BaseResponseException {
        return new ResponseEntity<>(roleService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定角色 id，获取角色
     *
     * @param id 角色 id
     * @return ResponseEntity<RoleEntity>
     */
    @OperationLog(value = "指定角色 id，获取角色", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<RoleEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(roleService.getOne(id), HttpStatus.OK);
    }

}
