package com.hq.management.common.aspect;

import com.hq.management.common.exception.InvalidKaptchaException;
import com.hq.management.common.exception.ManException;
import com.hq.management.common.tips.ErrorTip;
import com.hq.management.util.HttpKit;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.DisabledAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * @program: management
 * @description: 全局异常配置
 * @author: Mr.Huang
 * @create: 2018-10-30 11:36
 **/
@ControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 拦截业务异常
     */
    @ExceptionHandler(ManException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(ManException e) {

        HttpKit.getRequest().setAttribute("tip", e.getMessage());
        log.error("业务异常:", e);
        return new ErrorTip(e.getCode(), e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(RuntimeException e) {
        HttpKit.getRequest().setAttribute("tip", "服务器未知运行时异常");
        log.error("运行时异常:", e);
        return new ErrorTip(500, e.getMessage());
    }
    /**
     * 用户未登录异常
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unAuth(AuthenticationException e) {
        log.error("用户未登陆：", e);
        return "/login";
    }

    /**
     * 账号被冻结异常
     */
    @ExceptionHandler(DisabledAccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String accountLocked(DisabledAccountException e, Model model) {
        String username =  HttpKit.getRequest().getParameter("username");
        model.addAttribute("tips", "账号被冻结");
        return "/login";
    }

    /**
     * 账号密码错误异常
     */
    @ExceptionHandler(CredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String credentials(CredentialsException e, Model model) {
        String username =  HttpKit.getRequest().getParameter("username");
        model.addAttribute("tips", "账号密码错误");
        return "/login";
    }

    /**
     * 验证码错误异常
     */
    @ExceptionHandler(InvalidKaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String credentials(InvalidKaptchaException e, Model model) {
        String username =  HttpKit.getRequest().getParameter("username");
        model.addAttribute("tips", "验证码错误");
        return "/login";
    }

    /**
     * 无权访问该资源异常
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorTip credentials(UndeclaredThrowableException e) {
        HttpKit.getRequest().setAttribute("tip", "权限异常");
        log.error("权限异常!", e);
        return new ErrorTip(500, e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
   /* @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException e,Model model) {
        log.error("参数验证失败,"+e.getMessage());
        model.addAttribute("error","参数验证失败,"+e.getMessage());
        return "error/500";
    }*/
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e, Model model) {
        log.error("参数解析失败,"+e.getMessage());
        model.addAttribute("error","不支持当前媒体类型,"+e.getMessage());
        return "error/500";
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, Model model) {
        log.error("不支持当前请求方法,"+e.getMessage());
        model.addAttribute("error","不支持当前媒体类型,"+e.getMessage());
        return "error/500";
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public String handleHttpMediaTypeNotSupportedException(Exception e,Model model) {
        log.error("不支持当前媒体类型,"+e.getMessage());
        model.addAttribute("error","不支持当前媒体类型,"+e.getMessage());
        return "error/500";
    }

    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException e, Model model) {
        log.error("资源不存在,"+e.getMessage());
        model.addAttribute("error","资源不存在,"+e.getMessage());
        return "error/500";

    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException e,Model model) {
        log.error("空指针异常,"+e.getMessage());
        model.addAttribute("error","空指针异常,"+e.getMessage());
        return "error/500";
    }

}
