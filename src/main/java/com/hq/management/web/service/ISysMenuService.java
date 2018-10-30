package com.hq.management.web.service;

import com.baomidou.mybatisplus.service.IService;
import com.hq.management.web.entity.SysMenu;

import java.util.List;

/**
 * @program: management
 * @description:
 * @author: Mr.Huang
 * @create: 2018-10-30 15:46
 **/
public interface ISysMenuService extends IService<SysMenu> {
    List<String> findMenusByUid(String id);
}
