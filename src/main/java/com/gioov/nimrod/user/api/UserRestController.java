package com.gioov.nimrod.user.api;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.common.security.SimpleUserDetails;
import com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl;
import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.nimrod.user.mapper.UserMapper;
import com.gioov.nimrod.user.service.*;
import com.gioov.tile.web.exception.BaseResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value = User.Api.USER, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    private static final String USER = "/API/USER";

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ViewMenuService viewMenuService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    /**
     * 分页获取所有用户
     * @param page 页
     * @param rows 每页显示数量
     * @param sortField 排序字段
     * @param sortOrder 排序 order
     * @param username 用户名
     * @param email 电子邮箱
     * @param emailIsVerified 电子邮箱是否验证
     * @param departmentId 部门 id
     * @param enabled 是否启用
     * @param gmtCreatedStart
     * @param gmtCreatedEnd
     * @param gmtDeletedStart
     * @param gmtDeletedEnd
     * @return ResponseEntity<Pagination<UserEntity>>
     */
    @OperationLog(value = "分页获取所有用户", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/PAGE_ALL')")
    @GetMapping(value = "/page_all")
    public ResponseEntity<Pagination<UserEntity>> pageAll(@RequestParam Integer page, @RequestParam Integer rows, @RequestParam(required = false) String sortField, @RequestParam(required = false) String sortOrder, @RequestParam(required = false) String username, @RequestParam(required = false) String email, @RequestParam(required = false) Integer emailIsVerified, @RequestParam(required = false) Long departmentId, @RequestParam(required = false) Integer enabled, @RequestParam(required = false) String gmtCreatedStart, @RequestParam(required = false) String gmtCreatedEnd, @RequestParam(required = false) String gmtDeletedStart, @RequestParam(required = false) String gmtDeletedEnd) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setEmailIsVerified(emailIsVerified);
        userEntity.setDepartmentId(departmentId);
        userEntity.setEnabled(enabled);
        return new ResponseEntity<>(userService.pageAll(page, rows, sortField, sortOrder, userEntity, gmtCreatedStart, gmtCreatedEnd, gmtDeletedStart, gmtDeletedEnd), HttpStatus.OK);
    }

    /**
     * 指定部门 id，分页获取所有用户
     * @param departmentId 部门 id
     * @param page 页
     * @param rows 每页显示数量
     * @return ResponseEntity<Pagination<UserEntity>>
     */
    @OperationLog(value = "指定部门 id，分页获取所有用户", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/PAGE_ALL_BY_DEPARTMENT_ID')")
    @GetMapping(value = "/page_all_by_department_id")
    public ResponseEntity<Pagination<UserEntity>> pageAllByDepartmentId(@RequestParam Long departmentId, @RequestParam Integer page, @RequestParam Integer rows) {
        return new ResponseEntity<>(userService.pageAllByDepartmentId(departmentId, page, rows), HttpStatus.OK);
    }

    /**
     * 新增用户
     * @param username 用户名
     * @param password 密码
     * @param avatar 头像
     * @param email 电子邮箱
     * @param emailIsVerified 电子邮箱是否验证
     * @param departmentId 部门 id
     * @param enabled 是否启用
     * @param remark 备注
     * @return ResponseEntity<UserEntity>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "新增用户", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/ADD_ONE')")
    @PostMapping(value = "/add_one")
    public ResponseEntity<UserEntity> addOne(@RequestParam String username, @RequestParam String password, @RequestParam String avatar, @RequestParam String email, @RequestParam Integer emailIsVerified, @RequestParam Long departmentId, @RequestParam Integer enabled, @RequestParam String remark) throws BaseResponseException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setAvatar(avatar);
        userEntity.setEmail(email);
        userEntity.setEmailIsVerified(emailIsVerified);
        userEntity.setDepartmentId(departmentId);
        userEntity.setEnabled(enabled);
        userEntity.setRemark(remark);
        UserEntity userEntity1 = userService.addOne(userEntity);
        return new ResponseEntity<>(userEntity1, HttpStatus.OK);
    }

    /**
     * 保存用户
     * @param id 用户 id
     * @param username 用户名
     * @param password 密码
     * @param avatar 头像
     * @param email 电子邮箱
     * @param emailIsVerified 电子邮箱是否验证
     * @param departmentId departmentId
     * @param enabled 是否启用
     * @param remark 备注
     * @return ResponseEntity<UserEntity>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "保存用户", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/SAVE_ONE')")
    @PostMapping(value = "/save_one")
    public ResponseEntity<UserEntity> saveOne(@RequestParam Long id, @RequestParam String username, @RequestParam(required = false) String password, @RequestParam String avatar, @RequestParam String email, @RequestParam Integer emailIsVerified, @RequestParam Long departmentId, @RequestParam Integer enabled, @RequestParam String remark) throws BaseResponseException {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setAvatar(avatar);
        userEntity.setEmail(email);
        userEntity.setEmailIsVerified(emailIsVerified);
        userEntity.setDepartmentId(departmentId);
        userEntity.setEnabled(enabled);
        userEntity.setRemark(remark);
        UserEntity userEntity1 = userService.saveOne(userEntity);
        return new ResponseEntity<>(userEntity1, HttpStatus.OK);
    }

    /**
     * 指定用户 id list，删除用户
     * @param idList 用户 id list
     * @return ResponseEntity<HttpStatus>
     */
    @OperationLog(value = "指定用户 id list，删除用户", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/FAKE_DELETE_ALL')")
    @PostMapping(value = "/fake_delete_all")
    public ResponseEntity<Integer> fakeDeleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(userService.fakeDeleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定用户 id list，撤销删除用户
     * @param idList 用户 id list
     * @return ResponseEntity<Integer>
     */
    @OperationLog(value = "指定用户 id list，撤销删除用户", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/REVOKE_FAKE_DELETE_ALL')")
    @PostMapping(value = "/revoke_fake_delete_all")
    public ResponseEntity<Integer> revokeFakeDeleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(userService.revokeFakeDeleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定用户 id list，批量永久删除用户
     * @param idList 用户 id list
     * @return ResponseEntity<Integer>
     */
    @OperationLog(value = "指定用户 id list，批量永久删除用户", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/DELETE_ALL')")
    @PostMapping(value = "/delete_all")
    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
        return new ResponseEntity<>(userService.deleteAll(idList), HttpStatus.OK);
    }

    /**
     * 指定用户 id，获取用户（除密码和角色）
     * @param id 用户 id
     * @return ResponseEntity<UserEntity>
     */
    @OperationLog(value = "指定用户 id，获取用户（除密码和角色）", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<UserEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getOneByIdNoPassword(id), HttpStatus.OK);
    }

    /**
     * 获取当前用户（用户 id、用户名、头像、电子邮箱、权限、部门）
     * @param httpServletRequest HttpServletRequest
     * @return ResponseEntity<UserEntity>
     */
    @OperationLog(value = "获取当前用户（用户 id、用户名、头像、电子邮箱、权限、部门）", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/GET_CURRENT_USER')")
    @GetMapping(value = "/get_current_user")
    public ResponseEntity<Map<String, Object>> getCurrentUser(HttpServletRequest httpServletRequest) {
        SimpleUserDetails simpleUserDetails = SimpleUserDetailsServiceImpl.getCurrentSimpleUser(httpServletRequest);
        Map<String, Object> map = new HashMap<>(0);
        map.put("id", null);
        map.put("username", null);
        map.put("avatar", null);
        map.put("email", null);
        map.put("authority", new ArrayList<>(0));
        if(simpleUserDetails != null) {
            UserEntity userEntity = userMapper.getOne(simpleUserDetails.getId());
            map.put("id", userEntity.getId());
            map.put("username", userEntity.getUsername());
            map.put("avatar", userEntity.getAvatar());
            map.put("email", userEntity.getEmail());
            Collection<? extends GrantedAuthority> grantedAuthorityCollection = simpleUserDetails.getAuthorities();
            List<String> stringList = new ArrayList<>(0);
            if(grantedAuthorityCollection != null) {
                for (GrantedAuthority grantedAuthority : grantedAuthorityCollection) {
                    stringList.add(grantedAuthority.getAuthority());
                }
            }
            map.put("authority", stringList);
            map.put("department", departmentService.listAllByDepartmentId(userEntity.getDepartmentId()));
//            map.put("sideMenu", viewMenuService.listAllAsAntdVueMenuByUserId(userService.getCurrentUser().getId()));
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 指定用户 id，获取用户（除密码和角色）
     * @param id 用户 id
     * @return ResponseEntity<UserEntity>
     */
    @OperationLog(value = "指定用户 id，获取用户（除密码和角色）", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/ONE')")
    @GetMapping(value = "/profile/{id}")
    public ResponseEntity<UserEntity> profile(@PathVariable Long id) {
        return new ResponseEntity<>(userService.profile(userService.getOneByIdNoPassword(id)), HttpStatus.OK);
    }

    /**
     * 指定用户 id，获取用户（除密码和角色）
     * @return ResponseEntity<UserEntity>
     */
    @OperationLog(value = "指定用户 id，获取用户（除密码和角色）", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/ONE')")
    @GetMapping(value = "/get_profile_by_current_user")
    public ResponseEntity<UserEntity> getProfileByCurrentUser() {
        return new ResponseEntity<>(userService.profile(userService.getCurrentUser()), HttpStatus.OK);
    }

    /**
     * 保存用户
     * @param avatar 头像
     * @return ResponseEntity<UserEntity>
     * @throws BaseResponseException BaseResponseException
     */
    @OperationLog(value = "保存用户", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/SAVE_ONE')")
    @PostMapping(value = "/save_profile")
    public ResponseEntity<UserEntity> saveProfile(@RequestParam String avatar) throws BaseResponseException {
        UserEntity userEntity = new UserEntity();
        userEntity.setAvatar(avatar);
        return new ResponseEntity<>(userService.saveProfile(userEntity), HttpStatus.OK);
    }

    /**
     * 指定用户 id，获取用户（除密码和角色）
     * @return ResponseEntity<UserEntity>
     */
    @OperationLog(value = "指定用户 id，获取用户（除密码和角色）", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/SEND_EMAIL_VERIFY_CODE_BY_CURRENT_USER')")
    @PostMapping(value = "/send_email_verify_code_by_current_user")
    public ResponseEntity<Boolean> sendEmailVerifyCodeByCurrentUser(@RequestParam(required = false) String newEmail) throws BaseResponseException {
        UserEntity userEntity = userService.getCurrentUser();
        if(newEmail != null){
           return new ResponseEntity<>(userService.sendEmailVerifyCode(userEntity.getId(), newEmail), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userService.sendEmailVerifyCode(userEntity), HttpStatus.OK);
        }
    }

    /**
     * 指定用户 id，获取用户（除密码和角色）
     * @return ResponseEntity<UserEntity>
     */
    @OperationLog(value = "指定用户 id，获取用户（除密码和角色）", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/CHECK_VERIFY_CODE_BY_CURRENT_USER')")
    @PostMapping(value = "/check_email_verify_code_by_current_user")
    public ResponseEntity<Boolean> checkEmailVerifyCodeByCurrentUser(@RequestParam String emailVerifyCode) throws BaseResponseException {
        UserEntity userEntity = userService.getCurrentUser();
        return new ResponseEntity<>(userService.checkEmailVerifyCode(userEntity, userEntity.getEmail(), emailVerifyCode), HttpStatus.OK);
    }

    /**
     * 指定用户 id，获取用户（除密码和角色）
     * @return ResponseEntity<UserEntity>
     */
    @OperationLog(value = "指定用户 id，获取用户（除密码和角色）", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/CHANGE_EMAIL_BY_CURRENT_USER')")
    @PostMapping(value = "/change_email_by_current_user")
    public ResponseEntity<Boolean> changeEmailByCurrentUser(@RequestParam String emailVerifyCode, @RequestParam String newEmail, @RequestParam String newEmailVerifyCode) throws BaseResponseException {
        UserEntity userEntity = userService.getCurrentUser();
        return new ResponseEntity<>(userService.changeEmail(userEntity, emailVerifyCode, newEmail, newEmailVerifyCode), HttpStatus.OK);
    }

    /**
     * 指定用户 id，获取用户（除密码和角色）
     * @return ResponseEntity<UserEntity>
     */
    @OperationLog(value = "指定用户 id，获取用户（除密码和角色）", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + USER + "/CHANGE_PASSWORD_BY_CURRENT_USER')")
    @PostMapping(value = "/change_password_by_current_user")
    public ResponseEntity<Boolean> changePasswordByCurrentUser(@RequestParam String password, @RequestParam String newPassword, @RequestParam String confirmNewPassword) throws BaseResponseException {
        UserEntity userEntity = userService.getCurrentUser();
        return new ResponseEntity<>(userService.changePassword(userEntity, password, newPassword, confirmNewPassword), HttpStatus.OK);
    }
}
