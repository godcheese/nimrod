package com.gioov.nimrod.user.service.impl;

import com.gioov.common.crypto.BCryptEncoderUtil;
import com.gioov.common.mybatis.Pageable;
import com.gioov.common.util.DateUtil;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.security.SimpleUser;
import com.gioov.nimrod.common.security.SimpleUserDetails;
import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.nimrod.user.mapper.UserMapper;
import com.gioov.nimrod.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

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
        if (checkPassword(password, userEntity.getPassword())) {
            return userEntity;
        }
        return null;
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
    public SimpleUser getUserPrincipal(HttpServletRequest request) {
        Object object = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(object != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) object;
            return (SimpleUser) securityContextImpl.getAuthentication().getPrincipal();
        }
        return null;
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
    public UserEntity getCurrentUser() {
        UserEntity userEntity = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                SimpleUserDetails simpleUserDetails = (SimpleUserDetails) principal;
                userEntity = userMapper.getOne(simpleUserDetails.getId());
            }
        }
        return userEntity;
    }

    @Override
    public UserEntity getCurrentUser(HttpServletRequest request) {
        UserEntity userEntity = null;
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
                .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if (securityContextImpl != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserDetails) {
                    SimpleUserDetails simpleUserDetails = (SimpleUserDetails) principal;
                    userEntity = userMapper.getOne(simpleUserDetails.getId());
                }
            }
        }
        return userEntity;
    }

//    @Override
//    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BaseResponseException {
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            if (authentication != null) {
//                LOGGER.info("username1={}",((SimpleUserDetails)authentication.getPrincipal()).getUsername());
//                new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
//                HttpSession httpSession = httpServletRequest.getSession(false);
//                        if(httpSession != null) {
//                            httpSession.invalidate();
//                        }
//                authentication = SecurityContextHolder.getContext().getAuthentication();
//
//
//                        if(authentication != null) {
//                            LOGGER.info("username2={}", ((SimpleUserDetails) authentication.getPrincipal()).getUsername());
//                        }
//            }
//        } catch (Exception e) {
//            throw new BaseResponseException(FailureMessage.LOGOUT_FAIL);
//        }
//    }

    @Override
    public Pagination.Result<UserEntity> pageAll(Integer page, Integer rows) {
        Pagination.Result<UserEntity> paginationResult = new Pagination().new Result<>();
        List<UserEntity> userEntityList = new ArrayList<>();
        List<UserEntity> tempUserEntityList = userMapper.pageAll(new Pageable(page, rows));
        if (tempUserEntityList != null) {
            for (UserEntity userEntity : tempUserEntityList) {
                userEntity.setPassword(null);
                userEntityList.add(userEntity);
            }
        }
        paginationResult.setRows(userEntityList);
        paginationResult.setTotal(userMapper.countAll());
        return paginationResult;
    }

    @Override
    public Pagination.Result<UserEntity> pageAllByDepartmentId(Long departmentId, Integer page, Integer rows) {
        Pagination.Result<UserEntity> paginationResult = new Pagination().new Result<>();
        List<UserEntity> userEntityList = new ArrayList<>();
        List<UserEntity> tempUserEntityList = userMapper.pageAllByDepartmentId(departmentId, new Pageable(page, rows));
        if (tempUserEntityList != null) {
            for (UserEntity userEntity : tempUserEntityList) {
                userEntity.setPassword(null);
                userEntityList.add(userEntity);
            }
        }
        paginationResult.setRows(userEntityList);
        paginationResult.setTotal(userMapper.countAllByDepartmentId(departmentId));
        return paginationResult;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public UserEntity insertOne(UserEntity userEntity) throws BaseResponseException {
        Date date = new Date();
        if (userMapper.getOneByUsername(userEntity.getUsername()) != null) {
            throw new BaseResponseException("该用户名已存在");
        }
        userEntity.setGmtModified(date);
        userEntity.setGmtCreated(date);
        userMapper.insertOne(userEntity);
        return userEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public UserEntity updateOne(UserEntity userEntity) throws BaseResponseException {
        UserEntity userEntity1 = userMapper.getOne(userEntity.getId());
        UserEntity userEntity2 = userMapper.getOneByUsername(userEntity.getUsername());
        if (userEntity2 != null && !userEntity2.getId().equals(userEntity.getId())) {
            throw new BaseResponseException("该用户名已存在");
        }
        userEntity.setUsername(userEntity.getUsername());
        userEntity.setEmail(userEntity.getEmail());
        userEntity.setEmailIsVerified(userEntity.getEmailIsVerified());
        userEntity.setRemark(userEntity.getRemark());
        userEntity.setGmtModified(new Date());
        userMapper.updateOne(userEntity);
        return userEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) {
        return userMapper.deleteAll(idList);
    }

    @Override
    public UserEntity getOne(Long id) {
        UserEntity userEntity = userMapper.getOne(id);
        if (userEntity != null) {
            userEntity.setPassword(null);
        }
        return userEntity;
    }

}
