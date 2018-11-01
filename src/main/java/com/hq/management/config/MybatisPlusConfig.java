package com.hq.management.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: management
 * @description: mybatis-plus
 * @author: Mr.Huang
 * @create: 2018-11-01 11:17
 **/
@Configuration
@MapperScan("com.hq.management.web.mapper")
public class MybatisPlusConfig {
    /**
     * 自动识别数据库类型的分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

    /**
     * SQL执行效率插件
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }
}
