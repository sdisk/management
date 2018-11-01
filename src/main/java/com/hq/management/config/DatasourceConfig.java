package com.hq.management.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @program: management
 * @description: datasource
 * @author: Mr.Huang
 * @create: 2018-11-01 11:15
 **/
@Configuration
public class DatasourceConfig {
    @Bean(name = "datasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    /**
     * 配置事务管理器
     * @return
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

   /* //配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String ,String> initParams = new HashMap<>();
      *//*  initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");*//*
        initParams.put("allow","");//默认就是允许所有访问
       //initParams.put("deny","192.168.15.21");
        bean.setInitParameters(initParams);
        return bean;
    }

    //配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }*/
}
