package com.gaop.appjob.entity.vo;

import lombok.Data;
import lombok.ToString;

/**
 * TODO
 * com.gaop.appjob.entity
 * gaop  接收界面传过来的参数 封装成实体进行接收  工作经验 福利待遇 薪资范围 职位名称 薪次
 * 2022/5/1
 */
@Data
@ToString
public class JobVO {
    private String payFilter;
    private String payMonths;
    private String welFares;
    private String workingYears;
    private String jobName;
}

