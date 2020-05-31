package com.gioov.nimrod.common.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-04-14
 */
public interface SimpleUserDetails extends UserDetails, Serializable {

    /**
     * 权限
     *
     * @return
     */
    @Override
    Collection<? extends GrantedAuthority> getAuthorities();

    /**
     * 密码
     *
     * @return
     */
    @Override
    String getPassword();

    /**
     * id
     *
     * @return
     */
    Long getId();

    /**
     * 用户名
     *
     * @return
     */
    @Override
    String getUsername();

    /**
     * 账号是否未过期
     *
     * @return
     */
    @Override
    boolean isAccountNonExpired();

    /**
     * 账号是否未锁定
     *
     * @return
     */
    @Override
    boolean isAccountNonLocked();

    /**
     * 凭证是否未过期
     *
     * @return
     */
    @Override
    boolean isCredentialsNonExpired();

    /**
     * 是否启用
     *
     * @return
     */
    @Override
    boolean isEnabled();
    // ~ Methods

}
