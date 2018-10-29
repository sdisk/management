package com.hq.management.common.aspect;

import com.hq.management.common.anno.BussinessLog;
import com.hq.management.util.IpUtil;
import com.hq.management.util.ShiroUtil;
import com.hq.management.util.SpringContextHolder;
import com.hq.management.web.entity.SysLog;
import com.hq.management.web.entity.SysUser;
import com.hq.management.web.service.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @program: management
 * @description: 日志记录
 * @author: Mr.Huang
 * @create: 2018-10-29 15:19
 **/
@Aspect
@Component
public class LogAop {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAop.class);

    @Pointcut(value = "@annotation(com.hq.management.common.anno.BussinessLog)")
    public void cutService(){

    }
    @Around("cutService()")
    public Object recodeSysLog(ProceedingJoinPoint  point)throws Throwable{

        Object result = point.proceed();
        try {
            handle(point);
        } catch (Exception e) {
            LOGGER.error("日志记录出错!", e);
        }

        return result;

    }
    public void handle(ProceedingJoinPoint point) throws Exception{
        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)){
            throw new IllegalAccessException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();

        //如果当前用户未登陆，不记录日志
        SysUser sysUser = ShiroUtil.getSessionUser();
        if (null == sysUser){
            return;
        }
        //获取拦截方法的参数
        String className = point.getTarget().getClass().getName();
        Object [] params = point.getArgs();

        //获取操作名称
        BussinessLog annotation = currentMethod.getAnnotation(BussinessLog.class);
        String bussinessName = annotation.value();
        String key = annotation.key();
        Class dictClass = annotation.dict();

        StringBuilder sb = new StringBuilder();
        for (Object param : params){
            sb.append(param);
            sb.append(" & ");
        }
        //如果涉及到修改，比对变化
       /* String msg;
        if (bussinessName.indexOf("修改") != -1 || bussinessName.indexOf("编辑") != -1){

        } else {

        }*/
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (null != annotation){
           SysLog log = new SysLog();
           log.setCreateTime(new Date());
           log.setUserName(sysUser.getUserName());
           log.setTitle(bussinessName);
           log.setMethodName(methodName);
           log.setParams(sb.toString());
           log.setUrl(IpUtil.getIpAddr(request));
           SpringContextHolder.getBean(ISysLogService.class).insert(log);
           LOGGER.debug("记录日志:" + log.toString());
       }
    }
}
