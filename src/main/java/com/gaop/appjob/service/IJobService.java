package com.gaop.appjob.service;

import com.gaop.appjob.common.JsonResult;
import com.gaop.appjob.entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gaop.appjob.entity.vo.JobVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GaoPeng
 * @since 2022-04-29
 */
public interface IJobService extends IService<Job> {

    JsonResult<Job> getJobs(JobVO jobVO);

}
