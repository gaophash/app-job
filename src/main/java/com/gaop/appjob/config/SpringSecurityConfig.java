package com.gaop.appjob.config;


import com.gaop.appjob.security.*;
import com.gaop.appjob.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @version 1.0.0
 * @author: gaopeng
 * @date: 2022/4/26 14:27
 * @description: SpringSecurityConfig   SpringSecurity的配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private CustomizeAccessDeniedHandler customizeAccessDeniedHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;

    /**
     * 对请求进行鉴权的配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                //禁用表单登录，前后端分离用不上
                .disable()
                //应用登录过滤器的配置，配置分离
                .apply(jwtAuthenticationSecurityConfig)

                .and()
                // 设置URL的授权
                .authorizeRequests()
                // 这里需要将登录页面放行,permitAll()表示不再拦截，/login 登录的url，/refreshToken刷新token的url
                //TODO 此处正常项目中放行的url还有很多，比如swagger相关的url，druid的后台url，一些静态资源
                .antMatchers("/hello","/refreshToken","/login2","/swagger-resources/**",
                        "/webjars/**", "/v2/**", "/swagger-ui.html/**",  "/doc.html/**","/job/**")
                .permitAll()
                //hasRole()表示需要指定的角色才能访问资源
                //.antMatchers("/hello").hasRole("admin")
                // anyRequest() 所有请求   authenticated() 必须被认证
                .anyRequest()
                .authenticated()
                //处理异常情况：认证失败和权限不足
                .and()
                .exceptionHandling()
                //认证未通过，不允许访问异常处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                //认证通过，但是没权限处理器
                .accessDeniedHandler(customizeAccessDeniedHandler)

                .and()
                //禁用session，JWT校验不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                //将TOKEN校验过滤器配置到过滤器链中，否则不生效，放到UsernamePasswordAuthenticationFilter之前
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                // 关闭csrf
                .csrf().disable();

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }

    // 自定义的Jwt Token校验过滤器
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * 默认开启密码加密，前端传入的密码Security会在加密后和数据库中的密文进行比对，一致的话就登录成功
     * 所以必须提供一个加密对象，供security加密前端明文密码使用
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
