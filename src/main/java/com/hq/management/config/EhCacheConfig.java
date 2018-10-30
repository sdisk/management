package com.hq.management.config;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @program: management
 * @description: encache配置
 * @author: Mr.Huang
 * @create: 2018-10-30 09:43
 **/
@Configuration
@EnableCaching
public class EhCacheConfig {

    /**
     * EhCache的配置
     */
    @Bean
    public EhCacheManagerFactoryBean ehcache(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }
    /**
     * EhCache的配置
     */
    @Bean
    public EhCacheCacheManager cacheCacheManager(CacheManager cacheManager){
        return new EhCacheCacheManager(cacheManager);
    }
}
