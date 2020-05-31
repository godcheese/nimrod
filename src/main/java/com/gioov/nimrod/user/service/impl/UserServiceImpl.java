package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.others.FailureEntity;
import com.gioov.nimrod.common.security.SimpleUser;
import com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl;
import com.gioov.nimrod.mail.entity.MailEntity;
import com.gioov.nimrod.mail.service.MailService;
import com.gioov.nimrod.mail.service.impl.MailServiceImpl;
import com.gioov.nimrod.system.service.DictionaryService;
import com.gioov.nimrod.user.entity.*;
import com.gioov.nimrod.user.mapper.UserMapper;
import com.gioov.nimrod.user.mapper.UserVerifyCodeMapper;
import com.gioov.nimrod.user.service.*;
import com.gioov.tile.crypto.BCryptEncoderUtil;
import com.gioov.tile.util.DateUtil;
import com.gioov.tile.util.RandomUtil;
import com.gioov.tile.util.StringUtil;
import com.gioov.tile.web.exception.BaseResponseException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 32;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FailureEntity failureEntity;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MailService mailService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private UserVerifyCodeMapper userVerifyCodeMapper;
    @Autowired
    private UserVerifyCodeService userVerifyCodeService;
    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public UserEntity addOne(UserEntity userEntity) throws BaseResponseException {
        Date date = new Date();
        userEntity.setUsername(userEntity.getUsername().trim());
        if (userMapper.getOneByUsername(userEntity.getUsername()) != null) {
            throw new BaseResponseException(failureEntity.i18n("user.username_exists"));
        }
        userEntity.setPassword(encodePassword(userEntity.getPassword().trim()));
        userEntity.setGmtModified(date);
        userEntity.setGmtCreated(date);
        userMapper.insertOne(userEntity);
        return userEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public UserEntity saveOne(UserEntity userEntity) throws BaseResponseException {
        userEntity.setUsername(userEntity.getUsername().trim());
        UserEntity userEntity1 = userMapper.getOneByUsername(userEntity.getUsername());
        if (userEntity1 != null && !userEntity1.getId().equals(userEntity.getId())) {
            throw new BaseResponseException(failureEntity.i18n("user.username_exists"));
        }
        if (userEntity.getPassword() != null && !"".equals(userEntity.getPassword().trim())) {
            userEntity.setPassword(encodePassword(userEntity.getPassword().trim()));
        } else {
            UserEntity userEntity2 = userMapper.getOne(userEntity.getId());
            if (userEntity2 != null) {
                userEntity.setPassword(userEntity2.getPassword());
            }
        }
        userEntity.setGmtModified(new Date());
        userMapper.updateOne(userEntity);
        return userEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) {
        return userMapper.deleteAll(idList);
    }

    @Override
    public UserEntity getOne(Long id) {
        return userMapper.getOne(id);
    }

    @Override
    public UserEntity getCurrentUser() {
        SimpleUser simpleUser;
        if ((simpleUser = SimpleUserDetailsServiceImpl.getCurrentSimpleUser()) != null) {
            return userMapper.getOne(simpleUser.getId());
        }
        return null;
    }

    @Override
    public UserEntity getCurrentUserNoPassword() {
        UserEntity userEntity = getCurrentUser();
        if (userEntity != null) {
            userEntity.setPassword(null);
            return userEntity;
        }
        return null;
    }

    @Override
    public UserEntity getCurrentUser(HttpServletRequest request) {
        SimpleUser simpleUser;
        if ((simpleUser = SimpleUserDetailsServiceImpl.getCurrentSimpleUser(request)) != null) {
            return userMapper.getOne(simpleUser.getId());
        }
        return null;
    }

//    @Override
//    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BaseResponseException {
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            if (authentication != null) {
//                LOGGER.info("username1={}",((SimpleUser)authentication.getPrincipal()).getUsername());
//                new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
//                HttpSession httpSession = httpServletRequest.getSession(false);
//                        if(httpSession != null) {
//                            httpSession.invalidate();
//                        }
//                authentication = SecurityContextHolder.getContext().getAuthentication();
//
//
//                        if(authentication != null) {
//                            LOGGER.info("username2={}", ((SimpleUser) authentication.getPrincipal()).getUsername());
//                        }
//            }
//        } catch (Exception e) {
//            throw new BaseResponseException(FailureEntity.LOGOUT_FAIL);
//        }
//    }

    @Override
    public Pagination<UserEntity> pageAll(Integer page, Integer rows, String sorterField, String sorterOrder, UserEntity userEntity, String gmtCreatedStart, String gmtCreatedEnd, String gmtDeletedStart, String gmtDeletedEnd) {
        if (sorterField != null && !"".equals(sorterField) && sorterOrder != null && !"".equals(sorterOrder)) {
            sorterField = StringUtil.camelToUnderline(sorterField);
            String orderBy = sorterField + " " + sorterOrder;
            PageHelper.startPage(page, rows, orderBy);
        } else {
            PageHelper.startPage(page, rows);
        }
        Page<UserEntity> userEntityPage = userMapper.pageAll(userEntity, gmtCreatedStart, gmtCreatedEnd, gmtDeletedStart, gmtDeletedEnd);
        Pagination<UserEntity> pagination = new Pagination<>();
        List<UserEntity> userEntityList = new ArrayList<>();
        List<UserEntity> tempUserEntityList = userEntityPage.getResult();
        if (tempUserEntityList != null) {
            for (UserEntity userEntity2 : tempUserEntityList) {
                userEntity2.setPassword(null);
                userEntity2.setDepartment(departmentService.listAllByDepartmentId(userEntity2.getDepartmentId()));
                userEntityList.add(userEntity2);
            }
        }
        pagination.setRows(userEntityList);
        pagination.setTotal(userEntityPage.getTotal());
        return pagination;
    }

    @Override
    public Pagination<UserEntity> pageAllByDepartmentId(Long departmentId, Integer page, Integer rows) {
        Pagination<UserEntity> pagination = new Pagination<>();
        PageHelper.startPage(page, rows);
        Page<UserEntity> tempUserEntityPage = userMapper.pageAllByDepartmentId(departmentId);
        List<UserEntity> userEntityList = new ArrayList<>();
        if (tempUserEntityPage != null) {
            for (UserEntity userEntity : tempUserEntityPage.getResult()) {
                userEntity.setPassword(null);
                userEntityList.add(userEntity);
            }
            pagination.setRows(userEntityList);
            pagination.setTotal(tempUserEntityPage.getTotal());
        }
        return pagination;
    }

    @Override
    public UserEntity getOneByIdAndPassword(Long id, String password) {
        UserEntity userEntity = userMapper.getOne(id);
        if (userEntity.getId().equals(id) && checkPassword(password, userEntity.getPassword())) {
            return userEntity;
        }
        return null;
    }

    @Override
    public UserEntity getOneByUsernameAndPassword(String username, String password) {
        UserEntity userEntity = userMapper.getOneByUsername(username);
        if (checkPassword(password, userEntity.getPassword())) {
            return userEntity;
        }
        return null;
    }

    @Override
    public UserEntity getOneByEmailAndPassword(String email, String password) {
        UserEntity userEntity = userMapper.getOneByEmail(email);
        if (checkPassword(password, userEntity.getPassword())) {
            return userEntity;
        }
        return null;
    }

    @Override
    public UserEntity getOneByCellphoneAndPassword(String cellphone, String password) {
        UserEntity userEntity = userMapper.getOneByCellphone(cellphone);
        return checkPassword(password, userEntity.getPassword()) ? userEntity : null;
    }

    @Override
    public boolean checkPassword(String plainPassword, String cipherPassword) {
        return BCryptEncoderUtil.matches(plainPassword, cipherPassword);
    }

    @Override
    public String encodePassword(String plainPassword) {
        return BCryptEncoderUtil.encode(plainPassword);
    }

    @Override
    public UserEntity getOneByIdNoPassword(Long id) {
        UserEntity userEntity = userMapper.getOne(id);
        if (userEntity != null) {
            userEntity.setPassword(null);
            return userEntity;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int fakeDeleteAll(List<Long> idList) {
        return userMapper.fakeDeleteAll(idList, DateUtil.newDate());
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int revokeFakeDeleteAll(List<Long> idList) {
        return userMapper.revokeFakeDeleteAll(idList);
    }

    @Override
    public UserEntity profile(UserEntity userEntity) {
        List<RoleEntity> roleEntityList = new ArrayList<>();
        List<DepartmentEntity> departmentEntityList;
        if (userEntity != null) {
            userEntity.setPassword(null);
            List<UserRoleEntity> userRoleEntityList = userRoleService.listAllByUserId(userEntity.getId());
            if (!userRoleEntityList.isEmpty()) {
                for (UserRoleEntity userRoleEntity : userRoleEntityList) {
                    RoleEntity roleEntity = roleService.getOne(userRoleEntity.getRoleId());
                    if (roleEntity != null) {
                        roleEntityList.add(roleEntity);
                    }
                }
            }
            userEntity.setRoles(roleEntityList);
            departmentEntityList = departmentService.listAllByDepartmentId(userEntity.getDepartmentId());
            userEntity.setDepartments(departmentEntityList);
        }
        return userEntity;
    }

    @Override
    public UserEntity saveProfile(UserEntity userEntity) throws BaseResponseException {
        UserEntity userEntity1 = getCurrentUser();
        userEntity1.setAvatar(userEntity.getAvatar());
        userEntity1.setPassword(null);
        return saveOne(userEntity1);
    }

    @Override
    public boolean sendEmailVerifyCode(Long userId, String email) throws BaseResponseException {
        Integer isOrNotIs = Integer.valueOf(String.valueOf(dictionaryService.get("IS_OR_NOT", "IS")));
        MailEntity mailEntity = new MailEntity();
        Map<String, Object> variables = new HashMap<>(3);
        String webName = (String) dictionaryService.get("WEB", "NAME");
        variables.put("webName", webName);
        variables.put("webUrl", dictionaryService.get("WEB", "URL"));
        String verifyCode = RandomUtil.randomString(6, RandomUtil.NUMBER);
        variables.put("verifyCode", verifyCode);
        mailEntity.setTo(email);
        mailEntity.setSubject(webName);
        mailEntity.setText(mailService.loadHtmlTemplate(MailServiceImpl.MAIL_TEMPLATE_ROOT_PATH + "/email_verify_code", variables));
        mailEntity.setHtml(isOrNotIs);
        UserVerifyCodeEntity userVerifyCodeEntity = new UserVerifyCodeEntity();
        userVerifyCodeEntity.setUserId(userId);
        userVerifyCodeEntity.setVerifyFrom(email);
        userVerifyCodeEntity.setVerifyCode(verifyCode);
        userVerifyCodeService.addOne(userVerifyCodeEntity);
        return mailService.addOne(mailEntity) != null;
    }

    @Override
    public boolean sendEmailVerifyCode(UserEntity userEntity) throws BaseResponseException {
        if (!sendEmailVerifyCode(userEntity.getId(), userEntity.getEmail())) {
            throw new BaseResponseException(failureEntity.i18n("user_verify_code.send_fail"));
        }
        return true;
    }

    @Override
    public boolean checkEmailVerifyCode(UserEntity userEntity, String email, String emailVerifyCode) throws BaseResponseException {
        UserVerifyCodeEntity userVerifyCodeEntity = userVerifyCodeService.getOneByUserIdAndVerifyFrom(userEntity.getId(), email, true, emailVerifyCode);
        if (userVerifyCodeEntity != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean changeEmail(UserEntity userEntity, String emailVerifyCode, String newEmail, String newEmailVerifyCode) throws BaseResponseException {
        String oldEmail = userEntity.getEmail();
        if (!checkEmailVerifyCode(userEntity, userEntity.getEmail(), emailVerifyCode) || !checkEmailVerifyCode(userEntity, newEmail, newEmailVerifyCode)) {
            throw new BaseResponseException(failureEntity.i18n("user_verify_code.verification_code_error"));
        }
        userEntity.setEmail(newEmail);
        userEntity.setPassword(null);
        userVerifyCodeMapper.deleteAllByEmail(oldEmail);
        userVerifyCodeMapper.deleteAllByEmail(newEmail);
        userService.saveOne(userEntity);
        return true;
    }

    @Override
    public boolean changePassword(UserEntity userEntity, String password, String newPassword, String confirmNewPassword) throws BaseResponseException {
        password = password.trim();
        newPassword = newPassword.trim();
        confirmNewPassword = confirmNewPassword.trim();
        if (!newPassword.equalsIgnoreCase(confirmNewPassword)) {
            throw new BaseResponseException(failureEntity.i18n("user.new_password_and_confirm_new_password_error"));
        }
        if (!checkPassword(password, userEntity.getPassword())) {
            throw new BaseResponseException(failureEntity.i18n("user.original_password_error"));
        }
        if (newPassword.length() < PASSWORD_MIN_LENGTH || newPassword.length() > PASSWORD_MAX_LENGTH) {
            throw new BaseResponseException(failureEntity.i18n("user.new_password_must_length"));
        }
        if (checkPassword(newPassword, userEntity.getPassword())) {
            throw new BaseResponseException(failureEntity.i18n("user.same_as_the_original_password"));
        }
        userEntity.setPassword(newPassword);
        userService.saveOne(userEntity);
        return true;
    }
}
