package com.wuyuan.framework.helper;

import com.wuyuan.framework.annotation.Inject;
import com.wuyuan.framework.util.ReflectionUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Author: kaiming.yin
 * @Description: 依赖注入助手类
 * @Date: Created in 10:50 2018/4/17
 * @ModifedBy:
 * @since 1.0.0
 */
public final class IocHelper {
    static{
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if(MapUtils.isNotEmpty(beanMap)){
            for(Map.Entry<Class<?>,Object> beanEntry : beanMap.entrySet()){
                Class<?> beanClass = beanEntry.getKey();
                Object beanObject = beanEntry.getValue();
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtils.isNotEmpty(beanFields)){
                    for(Field beanField : beanFields){
                        if(beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = BeanHelper.getBean(beanFieldClass);
                            if(beanFieldInstance!=null){
                                ReflectionUtil.setField(beanObject,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }

}
