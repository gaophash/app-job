package com.gaop.appjob.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO 解决请求带特殊字符原因
 * com.gaop.appjob.config
 * gaop
 * 2022/5/1
 */
@Configuration
public class TomcatConfig {
    /**
     * 配置转义字符,解决当请求路径中特殊字符，高版本tomcat解析失败的问题
     */
    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
        fa.addConnectorCustomizers(connector -> {
            connector.setProperty("relaxedQueryChars", "(),/:;<=>?@[\\]{}");
            connector.setProperty("rejectIllegalHeader", "false");
        });
        return fa;
    }

}
