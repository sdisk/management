package com.hq.management.web.service;

import com.baomidou.mybatisplus.service.IService;
import com.hq.management.web.entity.SysUserRole;

import java.util.Set;

/**
 * @program: management
 * @description:
 * @author: Mr.Huang
 * @create: 2018-10-30 15:10
 **/
public interface ISysUserRoleService extends IService<SysUserRole> {
    Set<String> findRolesByUid(String id);
}
