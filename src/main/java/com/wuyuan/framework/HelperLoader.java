package com.wuyuan.framework;


import com.wuyuan.framework.helper.BeanHelper;
import com.wuyuan.framework.helper.ClassHelper;
import com.wuyuan.framework.helper.ControllerHelper;
import com.wuyuan.framework.helper.IocHelper;
import com.wuyuan.framework.util.ClassUtil;

/**
 * @Author: kaiming.yin
 * @Description: 加载相应的Helper类
 * @Date: Created in 14:10 2018/4/17
 * @ModifedBy:
 * @since 1.0.0
 */
public class HelperLoader {
    public static void init(){
        Class<?>[] classList = {BeanHelper.class, ClassHelper.class, ControllerHelper.class, IocHelper.class};
        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(),false);
        }
    }
}
