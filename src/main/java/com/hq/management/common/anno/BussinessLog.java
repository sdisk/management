package com.hq.management.common.anno;

import com.hq.management.common.dict.AbstractDictMap;
import com.hq.management.common.dict.SystemDict;

import java.lang.annotation.*;

/**
 * @program: management
 * @description: 操作日志记录
 * @author: Mr.Huang
 * @create: 2018-10-29 14:31
 **/
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BussinessLog {

    /**
     * 业务名称
     */
    String value()default "";

    /**
     * 被修改实体的唯一标识
     */
    String key() default "id";

    /**
     * 字典(用于查找key的中文名称和字段的中文名称)
     */
     Class<? extends AbstractDictMap> dict() default SystemDict.class;
}
