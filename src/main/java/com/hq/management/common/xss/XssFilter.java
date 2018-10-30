package com.hq.management.common.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @program: management
 * @description: xss过滤
 * @author: Mr.Huang
 * @create: 2018-10-30 10:19
 **/
public class XssFilter implements Filter {

    FilterConfig filterConfig = null;
    private List<String> urlExclusion = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //对request进行过滤
       HttpServletRequest httpServletRequest = (HttpServletRequest) request;
       String path = httpServletRequest.getServletPath();
       if (urlExclusion != null && urlExclusion.contains(path)){
           chain.doFilter(request, response);
       } else{
           chain.doFilter(new XssHttpServletRequestWrapper(httpServletRequest), response);
       }

    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    public List<String> getUrlExclusion() {
        return urlExclusion;
    }

    public void setUrlExclusion(List<String> urlExclusion) {
        this.urlExclusion = urlExclusion;
    }
}
