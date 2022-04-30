package com.gaop.appjob.service.impl;

import com.gaop.appjob.entity.TUser;
import com.gaop.appjob.mapper.TUserMapper;
import com.gaop.appjob.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GaoPeng
 * @since 2022-04-28
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
