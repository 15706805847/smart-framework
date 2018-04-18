package com.wuyuan.framework.helper;

import com.wuyuan.framework.ConfigConstant;
import com.wuyuan.framework.util.PropsUtil;

import java.util.Properties;

/**
 * @Author: kaiming.yin
 * @Description: 属性文件助手类
 * @Date: Created in 13:51 2018/4/16
 * @ModifedBy:
 * @since 1.0.0
 */
public final class ConfigHelper {

    private static final PropsUtil CONFIG_PROPS = new PropsUtil(ConfigConstant.CONFIG_FILE);

    /**
     * @Author: kaiming.yin
     * @Description: 获取JDBC驱动
     * @Date: Created in 14:31 2018/4/16
     */
    public static String  getJdbcDriver(){
        return CONFIG_PROPS.getString(ConfigConstant.JDBC_DRIVER);
    }

    /**
     * @Author: kaiming.yin
     * @Description: 获取JDBCUrl
     * @Date: Created in 14:34 2018/4/16
     */
    public static String getJdbcUrl(){
        return CONFIG_PROPS.getString(ConfigConstant.JDBC_URL);
    }

    /**
     * @Author: kaiming.yin
     * @Description: 获取JdbcUserNam
     * @Date: Created in 14:34 2018/4/16
     */
    public static String getJdbcUserName(){
        return CONFIG_PROPS.getString(ConfigConstant.JDBC_USERNAME);
    }

    /**
     * @Author: kaiming.yin
     * @Description: 获取JdbcPassword
     * @Date: Created in 14:34 2018/4/16
     */
    public static String getJdbcPassword(){
        return CONFIG_PROPS.getString(ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * @Author: kaiming.yin
     * @Description: 获取应用基础包名
     * @Date: Created in 14:34 2018/4/16
     */
    public static String getAppBasePackage(){

        return CONFIG_PROPS.getString(ConfigConstant.APP_BASE_PACKAGE);
    }
    /**
     * @Author: kaiming.yin
     * @Description: 获取应用jsp路径
     * @Date: Created in 14:34 2018/4/16
     */
    public static String getAppJspPath(){
        return CONFIG_PROPS.getString(ConfigConstant.APP_JSP_PATH);
    }

   /**
    * @Author: kaiming.yin
    * @Description: 获取静态资源路径
    * @Date: Created in 14:38 2018/4/16
    */
    public static String getAppAssetPath(){
        return CONFIG_PROPS.getString(ConfigConstant.APP_ASSET_PATH);
    }
}
