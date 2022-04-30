package com.gaop.appjob.security;

import com.alibaba.fastjson.JSON;
import com.gaop.appjob.common.JsonResult;
import com.gaop.appjob.common.ResultCode;
import com.gaop.appjob.common.ResultTool;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0.0
 * @author: gaopeng
 * @date: 2022/4/26 11:50
 * @description: CustomizeAccessDeniedHandler 权限控制 接口访问 分 管理员和普通用户
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        JsonResult result = ResultTool.fail(ResultCode.NO_PERMISSION);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
