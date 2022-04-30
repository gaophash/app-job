package com.gaop.appjob.controller;


import com.gaop.appjob.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GaoPeng
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    IJobService iJobService;



}

