package com.hq.management.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hq.management.web.entity.SysLog;
import com.hq.management.web.mapper.SysLogMapper;
import com.hq.management.web.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: management
 * @description:
 * @author: Mr.Huang
 * @create: 2018-10-30 14:27
 **/
@Service
@Slf4j
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Override
    public void insertLog(String title, String uname, String url, String parms) {
        // TODO Auto-generated method stub
        SysLog sysLog  = new SysLog();
        sysLog.setCreateTime(new Date());
        sysLog.setTitle(title);
        sysLog.setUserName(uname);
        sysLog.setUrl(url);
        sysLog.setParams(parms);
        super.insert(sysLog);
        log.debug("记录日志:"+ sysLog.toString());
    }
}
