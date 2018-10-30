package com.hq.management.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hq.management.web.entity.SysUser;
import com.hq.management.web.mapper.SysUserMapper;
import com.hq.management.web.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * @program: management
 * @description:
 * @author: Mr.Huang
 * @create: 2018-10-30 16:05
 **/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
}
