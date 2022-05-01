package com.gaop.appjob.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gaop.appjob.common.JsonResult;
import com.gaop.appjob.common.ResultCode;
import com.gaop.appjob.common.ResultTool;
import com.gaop.appjob.entity.Job;
import com.gaop.appjob.entity.vo.JobVO;
import com.gaop.appjob.mapper.JobMapper;
import com.gaop.appjob.service.IJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author GaoPeng
 * @since 2022-04-29
 */
@Service
@Slf4j
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

    @Override
    public JsonResult getJobs(JobVO jobVO) {

        List<Job> resultList = new ArrayList<>();
        try {
                LambdaQueryWrapper<Job> wrapper = new LambdaQueryWrapper<Job>();
                if (StringUtils.isNotBlank(jobVO.getJobName())) {
                    wrapper.like(Job::getJobName, jobVO.getJobName());
                }
                if (StringUtils.isNotBlank(jobVO.getPayFilter())) {
                    String[] payFilters = jobVO.getPayFilter().split("-");
                    String payMin = payFilters[0];
                    String payMax = payFilters[1];
                    wrapper.ge(Job::getPayMin, payMin);
                    wrapper.ge(Job::getPayMax, payMax);
                }
                if (StringUtils.isNotBlank(jobVO.getPayMonths())) {
                    wrapper.eq(Job::getPayMonth, jobVO.getPayMonths());
                }
                if (StringUtils.isNotBlank(jobVO.getWelFares())) {
                    wrapper.like(Job::getWelfare, jobVO.getWelFares());
                }
                wrapper.orderByDesc(Job::getCreateTime);
                resultList = super.list(wrapper);
        } catch (Exception e) {
            log.error("查询失败", e);
        }
        return ResultTool.success(ResultCode.SUCCESS, resultList);
    }
}



