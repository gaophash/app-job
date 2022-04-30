package com.gaop.appjob.security;


import com.alibaba.fastjson.JSON;
import com.gaop.appjob.common.ResultCode;
import com.gaop.appjob.common.ResultTool;
import com.gaop.appjob.utils.JwtUtils;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @version 1.0.0
 * @author: gaopeng
 * @date: 2022/4/26 13:40
 * @description: CustomizeAuthenticationSuccessHandler 登录成功
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成令牌
        String accessToken = JwtUtils.createToken(userDetails.getUsername());
        //生成刷新令牌，如果accessToken令牌失效，则使用refreshToken重新获取令牌（refreshToken过期时间必须大于accessToken）
        String refreshToken = JwtUtils.refreshToken(accessToken);
        Map<String,String> result = new HashMap<>();
        result.put("token",accessToken);
        result.put("refreshToken",refreshToken);
        result.put("userInfo",userDetails.toString());
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        // 把Json数据放入到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(ResultTool.success(ResultCode.SUCCESS_LOGIN,result)));
    }


}
