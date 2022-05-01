package com.gaop.appjob.controller;


import com.gaop.appjob.common.JsonResult;
import com.gaop.appjob.common.ResultCode;
import com.gaop.appjob.common.ResultTool;
import com.gaop.appjob.entity.Job;
import com.gaop.appjob.entity.vo.JobVO;
import com.gaop.appjob.service.IJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
@Slf4j
@Api(tags = {"职位接口"})
public class JobController {

    @Autowired
    IJobService iJobService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiImplicitParam(name = "job", value = "职位参数", dataType = "object", paramType = "save", required = true, defaultValue = "")
    public JsonResult save(@RequestBody Job job) {
        try {
            //设置发布时间
            job.setPublishTime(LocalDateTime.now());
            //设置创建时间
            job.setCreateTime(LocalDateTime.now());
            iJobService.save(job);
        } catch (Exception e) {
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        return ResultTool.success(ResultCode.SUCCESS,job);
    }

    @RequestMapping(value = "/getJobs",method = RequestMethod.GET)
    @ApiImplicitParam(name = "job", value = "职位参数", dataType = "object", paramType = "find", required = true, defaultValue = "")
    public JsonResult getJobs(@RequestParam(value = "payFilter",required = false) String payFilter,@RequestParam(value = "payMonth",required = false) String payMonth,
                              @RequestParam(value = "jobName",required = false) String jobName,@RequestParam(value = "welFares",required = false) String welFares,
                              @RequestParam(value = "workingYears",required = false) String workingYears) {
        JobVO jobVO = new JobVO();
        jobVO.setJobName(jobName);
        jobVO.setPayFilter(payFilter);
        jobVO.setPayMonths(payMonth);
        jobVO.setWelFares(welFares);
        jobVO.setWorkingYears(workingYears);
      return   iJobService.getJobs(jobVO);
    }

}

