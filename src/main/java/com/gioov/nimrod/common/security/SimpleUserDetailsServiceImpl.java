package com.gioov.nimrod.common.security;

import com.gioov.nimrod.common.properties.AppProperties;
import com.gioov.nimrod.system.service.DictionaryService;
import com.gioov.nimrod.user.entity.RoleAuthorityEntity;
import com.gioov.nimrod.user.entity.RoleEntity;
import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.nimrod.user.entity.UserRoleEntity;
import com.gioov.nimrod.user.mapper.RoleAuthorityMapper;
import com.gioov.nimrod.user.mapper.RoleMapper;
import com.gioov.nimrod.user.mapper.UserMapper;
import com.gioov.nimrod.user.mapper.UserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class SimpleUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUserDetailsServiceImpl.class);

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

    public static final String ROLE_PREFIX = "ROLE_";

    @Autowired
    private DictionaryService dictionaryService;

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
                LOGGER.info("roleValue={},role={}", roleValue, role);
                if (roleValue.equals(role)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public SimpleUserDetails loadUserByUsername(String account) {
        // 从数据库中获取 user 实体
        UserEntity userEntity = userMapper.getOneByUsername(account);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Account " + account + " not found");
        }
        boolean disabled = false;
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = listAllSimpleGrantedAuthorityByUserId(userEntity.getId());

        if(!userEntity.getDisabled().equals(Integer.valueOf(String.valueOf(dictionaryService.get("IS_OR_NOT", "NOT"))))) {
            boolean isExistSystemAdminRole = false;
            for(SimpleGrantedAuthority simpleGrantedAuthority : simpleGrantedAuthorityList) {
                String authority = simpleGrantedAuthority.getAuthority();
                if(authority.indexOf(ROLE_PREFIX) == 0 && isExistSystemAdminRole(authority.substring(ROLE_PREFIX.length()))) {
                    isExistSystemAdminRole = true;
                }
            }

            if(!isExistSystemAdminRole) {
                disabled = true;
            }
        }

//        if(disabled) {
//            throw new UsernameNotFoundException("The account is disabled.");
//        }

//        LOGGER.info("disabled={}", disabled);

//        return new SimpleUser(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(), simpleGrantedAuthorityList);
        return SimpleUser.builder().id(userEntity.getId()).username(userEntity.getUsername()).password(userEntity.getPassword()).authorities(simpleGrantedAuthorityList).disabled(disabled).build();
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
                    if (simpleGrantedAuthorityList2 != null && !simpleGrantedAuthorityList2.isEmpty()) {
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

}
