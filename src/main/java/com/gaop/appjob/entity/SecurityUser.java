package com.gaop.appjob.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @version 1.0.0
 * @author: gaopeng
 * @date: 2022/4/28 17:46
 * @description: SecurityUser
 */
@Data
public class SecurityUser implements UserDetails {
    //用户名
    private String username;

    //密码
    private String password;

    //权限+角色集合
    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUser(String username, String password,Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public SecurityUser(){}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未被锁
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
