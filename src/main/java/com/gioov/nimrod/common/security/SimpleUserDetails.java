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

    @Override
    Collection<? extends GrantedAuthority> getAuthorities();

    @Override
    String getPassword();

    Long getId();

    @Override
    String getUsername();

    @Override
    boolean isAccountNonExpired();

    @Override
    boolean isAccountNonLocked();

    @Override
    boolean isCredentialsNonExpired();

    @Override
    boolean isEnabled();
    // ~ Methods

}
