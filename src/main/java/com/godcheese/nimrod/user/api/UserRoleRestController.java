package com.godcheese.nimrod.user.api;

import com.godcheese.nimrod.common.easyui.Pagination;
import com.godcheese.nimrod.common.operationlog.OperationLog;
import com.godcheese.nimrod.common.operationlog.OperationLogType;
import com.godcheese.nimrod.user.User;
import com.godcheese.nimrod.user.entity.UserRoleEntity;
import com.godcheese.nimrod.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.godcheese.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value = User.Api.USER_ROLE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRoleRestController {

    private static final String USER_ROLE = "/API/USER/USER_ROLE";

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 分页获取所有用户角色
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination < UserRoleEntity>>
     */
    @OperationLog(value = "分页获取所有用户角色", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER_ROLE + "/PAGE_ALL')")
    @GetMapping(value = "/page_all")
    public ResponseEntity<Pagination<UserRoleEntity>> pageAll(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(userRoleService.pageAll(page, rows), HttpStatus.OK);
    }

    /**
     * 新增用户角色
     *
     * @param userId 用户 id
     * @param roleId 角色 id
     * @return ResponseEntity<UserRoleEntity>
     */
    @OperationLog(value = "新增用户角色", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER_ROLE + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<UserRoleEntity> addOne(@RequestParam Long userId, @RequestParam Long roleId) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userId);
        userRoleEntity.setRoleId(roleId);
        UserRoleEntity userRoleEntity1 = userRoleService.addOne(userRoleEntity);
        return new ResponseEntity<>(userRoleEntity1, HttpStatus.OK);
    }

    /**
     * 指定用户 id、角色 id list，批量删除用户角色
     *
     * @param userId     用户 id
     * @param roleIdList 角色 id list
     * @return ResponseEntity<Integer>
     */
    @OperationLog(value = "指定用户 id、角色 id list，批量删除用户角色", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER_ROLE + "/DELETE_ALL_BY_USER_ID_AND_ROLE_ID_LIST')")
    @PostMapping(value = "/delete_all_by_user_id_and_role_id_list")
    public ResponseEntity<Integer> deleteAllByUserIdAndRoleIdList(@RequestParam Long userId, @RequestParam("roleIdList[]") List<Long> roleIdList) {
        return new ResponseEntity<>(userRoleService.deleteAllByUserIdAndRoleIdList(userId, roleIdList), HttpStatus.OK);
    }
}
