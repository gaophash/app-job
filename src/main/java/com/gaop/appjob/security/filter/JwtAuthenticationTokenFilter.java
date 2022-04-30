package com.gaop.appjob.security.filter;

import com.gaop.appjob.security.service.SecurityUserService;
import com.gaop.appjob.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0.0
 * @author: gaopeng
 * @date: 2022/4/26 13:58
 * @description: JwtAuthenticationTokenFilter 用来判断JWT是否有效
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * 直接将我们前面写好的service注入进来，通过service获取到当前用户的权限
     */
    @Autowired
    private SecurityUserService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取当请求头中的token，其实这里多余，完全可以使用HttpServletRequest来获取
        String authToken = request.getHeader("token");
        /**
         * token存在则校验token
         * 1. token是否存在
         * 2. token存在：
         *  2.1 校验token中的用户名是否失效
         */
        if (!StringUtils.isEmpty(authToken)){
            String username = JwtUtils.getUsernameFromToken(authToken);
            //SecurityContextHolder.getContext().getAuthentication()==null 未认证则为true
            if (!StringUtils.isEmpty(username) && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //如果token有效
                if (JwtUtils.validateToken(authToken,userDetails)){
                    // 将用户信息存入 authentication，方便后续校验
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        //继续执行下一个过滤器
        filterChain.doFilter(request,response);
    }
}
