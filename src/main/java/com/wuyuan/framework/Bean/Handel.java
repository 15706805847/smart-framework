package com.wuyuan.framework.Bean;

import java.lang.reflect.Method;

/**
 * @Author: kaiming.yin
 * @Description: 封装Action信息
 * @Date: Created in 11:58 2018/4/17
 * @ModifedBy:
 * @since 1.0.0
 */
public class Handel {

    private  Class<?> controllerClass;

    private Method actionMethod;

    public Handel(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

}
