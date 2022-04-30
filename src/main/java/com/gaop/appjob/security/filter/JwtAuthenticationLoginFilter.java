package com.gaop.appjob.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0.0
 * @author: gaopeng
 * @date: 2022/4/28 17:17
 * @description: JwtAuthenticationLoginFilter 登录认证的filter，参照UsernamePasswordAuthenticationFilter，添加到这之前的过滤器
 */
@Component
public class JwtAuthenticationLoginFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * 构造方法，调用父类的，设置登录地址/login，请求方式POST
     */
    //TODO 这里可以修改登录地址 看个人业务
    public JwtAuthenticationLoginFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        //获取表单提交数据
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        //封装到token中提交
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username,password);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
