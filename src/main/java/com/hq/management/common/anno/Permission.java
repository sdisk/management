package com.hq.management.common.anno;

import java.lang.annotation.*;

/**
 * @program: management
 * @description: 权限控制
 * @author: Mr.Huang
 * @create: 2018-10-29 14:49
 **/
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Permission {

    String [] value() default "";
}
