package com.hq.management.web.service;

import com.baomidou.mybatisplus.service.IService;
import com.hq.management.web.entity.SysRoleMenu;

import java.util.Set;

/**
 * @program: management
 * @description:
 * @author: Mr.Huang
 * @create: 2018-10-30 15:11
 **/
public interface ISysRoleMenuService extends IService<SysRoleMenu> {
    Set<String> findMenusByUid(String id);
}
