package com.hq.management.web.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hq.management.web.entity.SysMenu;
import com.hq.management.web.entity.SysRoleMenu;
import com.hq.management.web.mapper.SysRoleMenuMapper;
import com.hq.management.web.service.ISysMenuService;
import com.hq.management.web.service.ISysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: management
 * @description:
 * @author: Mr.Huang
 * @create: 2018-10-30 15:55
 **/
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Autowired
    private ISysMenuService sysMenuService;
    @Override
    public Set<String> findMenusByUid(String id) {
        List<String> list = sysMenuService.findMenusByUid(id);
        return new HashSet<>(list);
    }
}
