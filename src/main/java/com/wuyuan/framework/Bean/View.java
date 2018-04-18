package com.wuyuan.framework.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: kaiming.yin
 * @Description: 返回视图对象
 * @Date: Created in 16:13 2018/4/17
 * @ModifedBy:
 * @since 1.0.0
 */
public class View {

    /**
     * 视图路径
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String , Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<String , Object>();
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public View addModel(String key,Object value){
        model.put(key, value);
        return this;
    }
}
