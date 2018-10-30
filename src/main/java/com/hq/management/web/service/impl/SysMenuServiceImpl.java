package com.hq.management.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hq.management.web.entity.SysMenu;
import com.hq.management.web.mapper.SysMenuMapper;
import com.hq.management.web.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: management
 * @description:
 * @author: Mr.Huang
 * @create: 2018-10-30 15:53
 **/
@Service
public class SysMenuServiceImpl  extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    /**
     * 菜单服务
     */
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<String> findMenusByUid(String id) {
        return null;
    }
}
