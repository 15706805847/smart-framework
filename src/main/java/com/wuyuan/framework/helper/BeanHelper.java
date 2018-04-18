package com.wuyuan.framework.helper;

import com.wuyuan.framework.util.ClassUtil;
import com.wuyuan.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: kaiming.yin
 * @Description: Bean助手类
 * @Date: Created in 10:22 2018/4/17
 * @ModifedBy:
 * @since 1.0.0
 */

public final class BeanHelper {

    /**
     * 定义bean映射，保存Bean类与Bean实例的对应关系
     */
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for(Class<?> beanClass : beanClassSet){
            Object object = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass,object);
        }
    }

    /**
     * 获取Bean映射
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class:"+cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

}
