package com.gaop.appjob.security.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gaop.appjob.entity.*;
import com.gaop.appjob.mapper.TRoleAuthMapper;
import com.gaop.appjob.security.service.SecurityUserService;

import com.gaop.appjob.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @version 1.0.0
 * @author: gaopeng
 * @date: 2022/4/26 11:14
 * @description: SecurityUserServiceImpl
 */
@Service
public class SecurityUserServiceImpl implements SecurityUserService {

    @Autowired
    ITUserService itUserService;
    @Autowired
    ITUserRoleService itUserRoleService;
    @Autowired
    ITRoleAuthService itRoleAuthService;
    @Autowired
    ITRoleService itRoleService;
    @Autowired
    ITAuthService itAuthService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TUser tUser = itUserService.getOne(new QueryWrapper<TUser>().eq("username", username));
        if (StringUtils.isEmpty(tUser)) {
            throw new UsernameNotFoundException("根据用户名找不到该用户的信息！");
        }
        //查询该用户的角色
        LambdaQueryWrapper<TUserRole> wrapper = new LambdaQueryWrapper<TUserRole>();
        wrapper.eq(TUserRole::getStatus, 1);
        wrapper.eq(TUserRole::getUserId, tUser.getUserId());

        List<TUserRole> userRoles = itUserRoleService.list(wrapper);
        //获取权限集合
        Collection<? extends GrantedAuthority> authorities = merge(userRoles);
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUsername(username);
        //todo 此处为了方便，直接在数据库存储的明文，实际生产中应该存储密文，则这里不用再次加密
        securityUser.setPassword(passwordEncoder.encode(tUser.getPassword()));
        securityUser.setAuthorities(authorities);
        return securityUser;
    }

    /**
     * 查询角色
     */
    private List<String> listRoles(List<TUserRole> userRoles) {
        List<String> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userRoles)) {
            for (TUserRole param:userRoles) {
              TRole tRole =  itRoleService.getById(param.getRoleId());
                list.add(tRole.getName());
            }
        }
        return list;
    }

    private List<String> listAuths(List<TUserRole> userRoles) {
        List<String> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userRoles)) {
            userRoles.forEach(param -> {
                //查询该用户的角色
                LambdaQueryWrapper<TRoleAuth> wrapper = new LambdaQueryWrapper<TRoleAuth>();
                wrapper.eq(TRoleAuth::getStatus, 1);
                wrapper.eq(TRoleAuth::getRoleId, param.getRoleId());
                List<TRoleAuth> roleAuths = itRoleAuthService.list(wrapper);
                //查询权限
                if (!CollectionUtils.isEmpty(roleAuths)) {
                    for (TRoleAuth o:roleAuths) {
                        list.add(itAuthService.getById(o.getAuthId()).getUrl());
                    }
                }
            });
        }
        return list;
    }

    private Collection<? extends GrantedAuthority> merge(List<TUserRole> userRoles) {
        List<String> roles = listRoles(userRoles);
        List<String> auths = listAuths(userRoles);
        String[] a = {};
        roles.addAll(auths);
        return AuthorityUtils.createAuthorityList(roles.toArray(a));
    }
}
