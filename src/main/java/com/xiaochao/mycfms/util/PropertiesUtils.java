package com.xiaochao.mycfms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <pre>
 * properties 工具类，
 * 传入key 获取 url
 * 例:key=B102004 
 *  value=http://172.16.9.71:8080/BankWeb_1.1.0/V1/json/B102004Biz
 * @date 2015年4月28日
 * @author LRB
 * @version 1.0
 * </pre>
 */
public class PropertiesUtils {
    private static Properties prop = null;
    static {
        try {
            // 从 classpath：下获取资源文件
            InputStream in = PropertiesUtils.class.getResourceAsStream("/properties/remoteInterface.properties");
            prop = new Properties();
            prop.load(in);
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHttpUrl(String key) {
        return prop.getProperty(key);
    }
    /*
     * public static void main(String[] args) { String httpUrl = PropertiesUtils.getHttpUrl("B102004");
     * System.out.println(httpUrl); }
     */
}
