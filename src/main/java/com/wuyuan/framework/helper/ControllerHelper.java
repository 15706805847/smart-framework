package com.wuyuan.framework.helper;

import com.wuyuan.framework.Bean.Handel;
import com.wuyuan.framework.Bean.Request;
import com.wuyuan.framework.annotation.Action;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: kaiming.yin
 * @Description: 控制器助手类
 * @Date: Created in 12:03 2018/4/17
 * @ModifedBy:
 * @since 1.0.0
 */
public final class ControllerHelper {

    /**
     * 用于存放请求和处理器的映射关系
     */
    private static final Map<Request,Handel> ACTION_MAP = new HashMap<Request,Handel>();

    static {
        Set<Class<?>> contorllerSet = ClassHelper.getBeanClassSet();
        if(CollectionUtils.isNotEmpty(contorllerSet)){
            for (Class<?> controllerClass : contorllerSet){
                Method[] controllerMethods = controllerClass.getDeclaredMethods();
                if(ArrayUtils.isNotEmpty(controllerMethods)){
                    for(Method controllerMethod : controllerMethods){
                        if(controllerMethod.isAnnotationPresent(Action.class)){
                            Action action = controllerMethod.getAnnotation(Action.class);
                            String mapping = action.value();
                            if(mapping.matches("\\W+:/\\W*")){
                                String[] array = mapping.split(":");
                                if(ArrayUtils.isNotEmpty(array)&&array.length==2){
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod,requestPath);
                                    Handel handel = new Handel(controllerClass,controllerMethod);
                                    ACTION_MAP.put(request,handel);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取Handel
     * @return
     */
    public static Handel getHandel(String requestMethod, String requestPath){
        Request request = new Request(requestMethod,requestPath);
        return ACTION_MAP.get(request);
    }
}
