package com.wuyuan.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: kaiming.yin
 * @Description: 流操作工具类
 * @Date: Created in 15:54 2018/4/17
 * @ModifedBy:
 * @since 1.0.0
 */
public final class StreamUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    public static String getString(InputStream is){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine())!=null){
                sb.append(line);
            }
        }catch (Exception e){
            LOGGER.error("get String failure",e);
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
