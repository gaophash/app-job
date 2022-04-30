package com.gaop.appjob.controller;

import com.gaop.appjob.common.JsonResult;
import com.gaop.appjob.common.ResultCode;
import com.gaop.appjob.common.ResultTool;
import com.gaop.appjob.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0.0
 * @author: gaopeng
 * @date: 2022/4/28 17:28
 * @description: LoginController
 */
@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public JsonResult login(String username,String password){
        // 生成一个包含账号密码的认证信息
        Authentication token = new UsernamePasswordAuthenticationToken(username, password);
        // AuthenticationManager校验这个认证信息，返回一个已认证的Authentication
        Authentication authentication = authenticationManager.authenticate(token);
        // 将返回的Authentication存到上下文中
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResultTool.success(ResultCode.SUCCESS_LOGIN);
    }

}
