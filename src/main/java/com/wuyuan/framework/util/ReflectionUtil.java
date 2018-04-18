package com.wuyuan.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: kaiming.yin
 * @Description: 反射工具类
 * @Date: Created in 9:27 2018/4/17
 * @ModifedBy:
 * @since 1.0.0
 */
public final class ReflectionUtil  {

    private static final Logger log = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * @Author: kaiming.yin
     * @Description: 创造实例
     * @Date: Created in 9:50 2018/4/17
     */
    public static Object newInstance(Class<?> cls) {
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            log.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method,Object... args){
        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(obj,args);
        } catch (Exception e) {
            log.error("invoke method failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj , Field field, Object value){
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            log.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }
}
