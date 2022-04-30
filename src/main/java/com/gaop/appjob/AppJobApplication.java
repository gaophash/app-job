package com.gaop.appjob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.gaop.appjob.mapper")
public class AppJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppJobApplication.class, args);
    }

}
