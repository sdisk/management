package com.hq.management.web.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hq.management.web.entity.SysUserRole;
import com.hq.management.web.mapper.SysUserRoleMapper;
import com.hq.management.web.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: management
 * @description:
 * @author: Mr.Huang
 * @create: 2018-10-30 16:06
 **/
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public Set<String> findRolesByUid(String id) {
        List<SysUserRole> lists = this.selectList(new EntityWrapper<SysUserRole>().eq("userId", id));
        Set<String> sets = new HashSet<>(lists.size());
        for (SysUserRole sysUserRole:lists){
            sets.add(sysUserRole.getUserId());
        }
        return sets;
    }
}
