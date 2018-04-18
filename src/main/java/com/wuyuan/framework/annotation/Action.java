package com.wuyuan.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: kaiming.yin
 * @Description: 请求方法注解
 * @Date: Created in 17:40 2018/4/16
 * @ModifedBy:
 * @since 1.0.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    /**
     * @Author: kaiming.yin
     * @Description: 请求路径与类型
     * @Date: Created in 17:48 2018/4/16
     */
    String value();
}
