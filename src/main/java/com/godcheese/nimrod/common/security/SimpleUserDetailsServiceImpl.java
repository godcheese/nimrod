package com.godcheese.nimrod.common.security;

import com.godcheese.nimrod.common.operationlog.OperationLog;
import com.godcheese.nimrod.common.operationlog.OperationLogType;
import com.godcheese.nimrod.common.properties.AppProperties;
import com.godcheese.nimrod.system.service.DictionaryService;
import com.godcheese.nimrod.user.entity.RoleAuthorityEntity;
import com.godcheese.nimrod.user.entity.RoleEntity;
import com.godcheese.nimrod.user.entity.UserEntity;
import com.godcheese.nimrod.user.entity.UserRoleEntity;
import com.godcheese.nimrod.user.mapper.RoleAuthorityMapper;
import com.godcheese.nimrod.user.mapper.RoleMapper;
import com.godcheese.nimrod.user.mapper.UserMapper;
import com.godcheese.nimrod.user.mapper.UserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class SimpleUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUserDetailsServiceImpl.class);
    /**
     * 内置 SYSTEM_ADMIN。与数据库中的 SYSTEM_ADMIN 角色不一致，用户登录时，系统会提取 properties 中 app.system-admin-role 值与用户所赋予角色相比较，如若用户赋予角色在 app.system-admin-role 中存在，则将次内置 SYSTEM_ADMIN 赋予到此用户角色中。
     */
    public static final String SYSTEM_ADMIN = "BUILD_IN_SYSTEM_ADMIN_FLAG";

    /**
     * 角色前缀，Spring Security 角色会自带 ROLE_ 前缀
     */
    public static final String ROLE_PREFIX = "ROLE_";

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    private DictionaryService dictionaryService;

    private static final String IS_OR_NOT = "IS_OR_NOT";
    private static final String IS = "IS";

    private UserEntity userEntity;

    @Override
    @OperationLog(value = "用户名登录", type = OperationLogType.API)
    public SimpleUserDetails loadUserByUsername(String account) {
        // 从数据库中获取 user 实体
        UserEntity userEntity = userMapper.getOneByUsername(account);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Account " + account + " not found");
        }
        boolean enabled = true;
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = listAllSimpleGrantedAuthorityByUserId(userEntity.getId());
        for (SimpleGrantedAuthority simpleGrantedAuthority : simpleGrantedAuthorityList) {
            LOGGER.info("simpleGrantedAuthority.getAuthority={}", simpleGrantedAuthority.getAuthority());
        }
        if (userEntity.getEnabled() == null || !userEntity.getEnabled().equals(Integer.valueOf(String.valueOf(dictionaryService.get(IS_OR_NOT, IS))))) {
            boolean isExistSystemAdminRole = false;
            for (SimpleGrantedAuthority simpleGrantedAuthority : simpleGrantedAuthorityList) {
                if (simpleGrantedAuthority.getAuthority().equals(ROLE_PREFIX + SYSTEM_ADMIN)) {
                    isExistSystemAdminRole = true;
                }
            }
            if (!isExistSystemAdminRole) {
                enabled = false;
            }
        }

//        if(disabled) {
//            throw new UsernameNotFoundException("The account is disabled.");
//        }

//        LOGGER.info("disabled={}", disabled);

//        return new SimpleUser(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(), simpleGrantedAuthorityList);
        this.userEntity = userEntity;
        return SimpleUser.builder().id(userEntity.getId()).username(userEntity.getUsername()).password(userEntity.getPassword()).authorities(simpleGrantedAuthorityList).disabled(!enabled).build();
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    /**
     * 角色是否存在
     *
     * @param roleValue 角色值
     * @return boolean
     */
    private boolean isExistSystemAdminRole(String roleValue) {
        List<String> roleList = appProperties.getSystemAdminRole();
        if (roleList != null && !roleList.isEmpty()) {
            for (String role : roleList) {
                if (roleValue.equals(role)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 列出用户所拥有的角色和角色权限
     *
     * @param userId 用户 id
     * @return List<SimpleGrantedAuthority>
     */
    private List<SimpleGrantedAuthority> listAllSimpleGrantedAuthorityByUserId(Long userId) {
        List<UserRoleEntity> userRoleEntityList = userRoleMapper.listAllByUserId(userId);
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        // 装载角色
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList1 = new ArrayList<>();
        // 装载角色对应的权限
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList2;
        if (userRoleEntityList != null) {
            boolean isExistSystemAdminRole = false;
            for (UserRoleEntity userRoleEntity : userRoleEntityList) {
                RoleEntity roleEntity = roleMapper.getOne(userRoleEntity.getRoleId());
                if (roleEntity != null) {
                    // 读取和装载角色
                    Long roleId = roleEntity.getId();
                    String roleValue = roleEntity.getValue().toUpperCase();
                    if (isExistSystemAdminRole(roleValue)) {
                        isExistSystemAdminRole = true;
                    }
                    simpleGrantedAuthorityList1.add(new SimpleGrantedAuthority(ROLE_PREFIX + roleValue));
                    // 读取和装载角色权限
                    simpleGrantedAuthorityList2 = listAllSimpleGrantedAuthorityByRoleId(roleId);
                    if (!simpleGrantedAuthorityList2.isEmpty()) {
                        simpleGrantedAuthorityList1.addAll(simpleGrantedAuthorityList2);
                    }
                }
            }
            // 检查是否有重复的 ROLE_SYSTEM_ADMIN
            int i = 0;
            for (SimpleGrantedAuthority simpleGrantedAuthority : simpleGrantedAuthorityList1) {
                i++;
                if (!simpleGrantedAuthority.getAuthority().equals(ROLE_PREFIX + SYSTEM_ADMIN) && (i == simpleGrantedAuthorityList1.size()) && isExistSystemAdminRole) {
                    simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(ROLE_PREFIX + SYSTEM_ADMIN));
                }
            }
        }
        simpleGrantedAuthorityList.addAll(simpleGrantedAuthorityList1);
        return simpleGrantedAuthorityList;
    }

    /**
     * 列出角色所拥有的权限
     *
     * @param roleId 角色 id
     * @return List<SimpleGrantedAuthority>
     */
    private List<SimpleGrantedAuthority> listAllSimpleGrantedAuthorityByRoleId(Long roleId) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        List<RoleAuthorityEntity> roleAuthorityEntityList = roleAuthorityMapper.listAllByRoleId(roleId);
        if (roleAuthorityEntityList != null) {
            for (RoleAuthorityEntity roleAuthorityEntity : roleAuthorityEntityList) {
                simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(roleAuthorityEntity.getAuthority().toUpperCase()));
            }
        }
        return simpleGrantedAuthorityList;
    }

    public static SimpleUser getCurrentSimpleUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return (SimpleUser) principal;
            }
        }
        return null;
    }

    public static SimpleUser getCurrentSimpleUser(HttpServletRequest request) {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication authentication;
        if (securityContextImpl != null) {
            authentication = securityContextImpl.getAuthentication();
        } else {
            authentication = SecurityContextHolder.getContext().getAuthentication();
        }
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return (SimpleUser) principal;
            }
        }
        return null;
    }

    /**
     * 检测是否存在权限或系统管理员角色
     *
     * @param authorities
     * @param authority
     * @return
     */
    public static boolean isExistsAuthority(Collection<GrantedAuthority> authorities, String authority) {
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(authority) || grantedAuthority.getAuthority().equals(ROLE_PREFIX + SYSTEM_ADMIN)) {
                return true;
            }
        }
        return false;
    }
}
