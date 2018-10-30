package com.hq.management.web.service;

import com.baomidou.mybatisplus.service.IService;
import com.hq.management.web.entity.SysLog;

/**
 * @program: management
 * @description: log
 * @author: Mr.Huang
 * @create: 2018-10-29 16:33
 **/
public interface ISysLogService extends IService<SysLog> {

    /**
     * 记录日志
     * @param title
     * @param uname
     * @param url
     * @param parms
     */
    void insertLog(String title,String uname,String url,String parms);
}
