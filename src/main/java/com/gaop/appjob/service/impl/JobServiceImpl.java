package com.gaop.appjob.service.impl;

import com.gaop.appjob.entity.Job;
import com.gaop.appjob.mapper.JobMapper;
import com.gaop.appjob.service.IJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GaoPeng
 * @since 2022-04-29
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

}
