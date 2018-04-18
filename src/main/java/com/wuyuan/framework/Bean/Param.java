package com.wuyuan.framework.Bean;

import java.util.Map;

/**
 * @Author: kaiming.yin
 * @Description: 请求参数对象
 * @Date: Created in 14:22 2018/4/17
 * @ModifedBy:
 * @since 1.0.0
 */
public class Param {
    private Map<String ,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public long getLong(String name){
        return (long) paramMap.get(name);
    }
}
