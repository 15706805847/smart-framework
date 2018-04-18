package com.wuyuan.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: kaiming.yin
 * @Description: 依赖注入注解
 * @Date: Created in 17:42 2018/4/16
 * @ModifedBy:
 * @since 1.0.0
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
