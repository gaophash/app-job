package com.gaop.appjob.security;

import com.alibaba.fastjson.JSON;
import com.gaop.appjob.common.JsonResult;
import com.gaop.appjob.common.ResultCode;
import com.gaop.appjob.common.ResultTool;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0.0
 * @author: gaopeng
 * @date: 2022/4/26 11:53
 * @description: CustomizeAuthenticationFailureHandler  登录账户时失败的处理
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        //返回json数据
        JsonResult result = null;
        if (e instanceof AccountExpiredException) {
            //账号过期
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            result = ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            result = ResultTool.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
        } else if (e instanceof DisabledException) {
            //账号不可用
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_DISABLE);
        } else if (e instanceof LockedException) {
            //账号锁定
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_LOCKED);
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }else{
            //其他错误
            result = ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        // 把Json数据放入到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(result));

    }
}
