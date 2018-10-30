package com.hq.management.web.controller;

import com.google.code.kaptcha.Constants;
import com.hq.management.common.controller.BaseController;
import com.hq.management.common.exception.InvalidKaptchaException;
import com.hq.management.util.ToolUtil;
import com.hq.management.web.entity.SysLog;
import com.hq.management.web.entity.SysUser;
import com.hq.management.web.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * @program: management
 * @description: login
 * @author: Mr.Huang
 * @create: 2018-10-30 11:19
 **/
@Controller
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    private ISysLogService sysLogService;

    @GetMapping(value = "/login")
    public String login(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return REDIRECT + "/";
        } else {
            return "login";
        }

    }
    @PostMapping(value = "/login")
    public String doLogin(Model model, HttpServletRequest request){
        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();
        String captcha = super.getPara("captcha").trim();
        String remember = super.getPara("remember");
        String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (ToolUtil.isEmpty(captcha) || !captcha.equalsIgnoreCase(code)){
            throw new InvalidKaptchaException();
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if ("on".equals(remember)){
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        try{
            subject.login(token);
        } catch (UnknownAccountException uae){
            model.addAttribute("error", "未知用户");
            return REDIRECT + "/login";
        }  catch (IncorrectCredentialsException ice) {
            model.addAttribute("error", "密码错误");
            return REDIRECT + "/login";
        } catch (LockedAccountException lae) {
            model.addAttribute("error", "账号已锁定");
            return REDIRECT + "/login";
        } catch (AuthenticationException ae) {
            model.addAttribute("error", "服务器繁忙");
            return REDIRECT + "/login";
        }
        //记录登录日志
        Subject subject1 = SecurityUtils.getSubject();
        log.debug(subject == subject1 ? "登录前后一致" : "登录前后不一致");
        SysUser user = (SysUser) subject1.getPrincipal();
        sysLogService.insertLog("用户登录成功",user.getUserName(), super.getHttpServletRequest().getRequestURI(),"");
        return REDIRECT + "/";
    }
    @GetMapping(value = "/loginOut")
    public String loginOut(){
        SecurityUtils.getSubject().logout();
        deleteAllCookie();
        return REDIRECT + "/login";
    }
    @GetMapping({"", "/", "/index"})
    public String index(){
        return "index";
    }
}
