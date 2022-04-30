package com.gaop.appjob.security;

import com.alibaba.fastjson.JSON;
import com.gaop.appjob.common.JsonResult;
import com.gaop.appjob.common.ResultCode;
import com.gaop.appjob.common.ResultTool;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0.0
 * @author: gaopeng
 * @date: 2022/4/26 13:54
 * @description: CustomizeLogoutSuccessHandler 退出登录 前端删除token 后端返回一个json数据就行
 */
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        JsonResult result = ResultTool.success(ResultCode.SUCCESS_LOGOUT);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));

    }
}
