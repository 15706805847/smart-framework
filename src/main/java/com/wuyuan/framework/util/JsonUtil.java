package com.wuyuan.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: kaiming.yin
 * @Description: JSON工具类
 * @Date: Created in 14:33 2018/4/17
 * @ModifedBy:
 * @since 1.0.0
 */
public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO转为JSON
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj){
        String json = null;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("convert POJO to JSON failure",e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * Json转POJO
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> cls){
        T pojo = null;
        try {
            pojo = OBJECT_MAPPER.readValue(json,cls);
        } catch (IOException e) {
            LOGGER.error("convert JSon to POJO failure",e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
