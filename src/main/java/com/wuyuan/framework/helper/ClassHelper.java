package com.wuyuan.framework.helper;

import com.wuyuan.framework.annotation.Controller;
import com.wuyuan.framework.annotation.Service;
import com.wuyuan.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: kaiming.yin
 * @Description: 类操作助手类
 * @Date: Created in 17:56 2018/4/16
 * @ModifedBy:
 * @since 1.0.0
 */

public final class ClassHelper {

    /**
     * 定义类集合（用于存放所有加载的类）
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包下所有类
     * @return
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取应用包下所有Service类
     * @return
     */
    public static  Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET){
            if (cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包下所有Controller类
     * @return
     */
    public static  Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET){
            if (cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包下所有Bean类
     * @return
     */
    public static  Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> BeanClassSet = new HashSet<Class<?>>();
       BeanClassSet.addAll(getControllerClassSet());
       BeanClassSet.addAll(getServiceClassSet());
        return BeanClassSet;
    }
}
