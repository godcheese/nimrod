package com.gioov.nimrod.user.api;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.Url;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.nimrod.user.service.UserService;
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
@RequestMapping(value = Url.Api.USER, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserRestController {

    private static final String USER = "/API/USER";

    @Autowired
    private UserService userService;

    /**
     * 分页获取用户
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination.Result < UserEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/PAGE_ALL')")
    @GetMapping(value = "/page_all")
    public ResponseEntity<Pagination.Result<UserEntity>> pageAll(@RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(userService.pageAll(page, rows), HttpStatus.OK);
    }

    /**
     * 分页获取用户
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination.Result < UserEntity>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/PAGE_ALL_BY_DEPARTMENT_ID')")
    @GetMapping(value = "/page_all_by_department_id/{departmentId}")
    public ResponseEntity<Pagination.Result<UserEntity>> pageAllByDepartmentId(@PathVariable Long departmentId, @RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(userService.pageAllByDepartmentId(departmentId, page, rows), HttpStatus.OK);
    }

    /**
     * 新增用户
     *
     * @param password 用户密码
     * @param username 用户名
     * @param email    用户电子邮箱
     * @param remark   备注
     * @return ResponseEntity<UserEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<UserEntity> addOne(@RequestParam String password, @RequestParam String username, @RequestParam String email, @RequestParam Integer emailIsVerified, @RequestParam Long departmentId, @RequestParam String remark) throws BaseResponseException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setEmail(email);
        userEntity.setEmailIsVerified(emailIsVerified);
        userEntity.setDepartmentId(departmentId);
        userEntity.setRemark(remark);
        UserEntity userEntity1 = userService.insertOne(userEntity);
        return new ResponseEntity<>(userEntity1, HttpStatus.OK);
    }

    /**
     * 保存用户
     *
     * @param id       用户 id
     * @param username 用户名
     * @param email    用户电子邮箱
     * @param remark   备注
     * @return ResponseEntity<UserEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<UserEntity> saveOne(@RequestParam Long id, @RequestParam String username, @RequestParam String email, @RequestParam Integer emailIsVerified, @RequestParam String remark) throws BaseResponseException {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setEmailIsVerified(emailIsVerified);
        userEntity.setRemark(remark);
        UserEntity userEntity1 = userService.updateOne(userEntity);
        return new ResponseEntity<>(userEntity1, HttpStatus.OK);
    }

    /**
     * 伪删除用户
     *
     * @param idList 用户 id list
     * @return ResponseEntity<HttpStatus>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/FAKE_DELETE_ALL')")
    @PostMapping(value = "/fake_delete_all")
    public ResponseEntity<Integer> fakeDeleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(userService.fakeDeleteAll(idList), HttpStatus.OK);
    }

    /**
     * 撤销伪删除用户
     *
     * @param idList 用户 id list
     * @return ResponseEntity<HttpStatus>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/REVOKE_FAKE_DELETE_ALL')")
    @PostMapping(value = "/revoke_fake_delete_all")
    public ResponseEntity<Integer> revokeFakeDeleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(userService.revokeFakeDeleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定用户 id ，批量删除用户
     *
     * @param idList 用户 id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(userService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定用户 id ，获取用户信息（除密码和角色）
     *
     * @param id 用户 id
     * @return ResponseEntity<UserEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<UserEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getOne(id), HttpStatus.OK);
    }

}
