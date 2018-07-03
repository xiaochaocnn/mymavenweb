package com.xiaochao.mycfms.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Configuration {
    private String confID = "";
    private Properties propertie;
 
    /**
     * 初始化Configuration类
     */
    public Configuration() {
        propertie = new Properties();
    }

    /**
     * 初始化Configuration类
     * 
     * @param fileName 要读取的配置文件的路径+名称
     */
    public Configuration(String fileName) {
    	propertie = new Properties();
    	InputStream in=null;
        try {
                in =  Configuration.class.getClassLoader().getResourceAsStream(fileName);
                propertie.load(in);
                in.close();
        }catch (IOException ex) {
            System.out.println("装载文件--->失败!");
            ex.printStackTrace();
        }
    }// end ReadConfigInfo(...)

    /**
     * @Title: getConfID
     * @Description: 配置标示
     * @return
     * @author zhangweirong
     */
    public String getConfID() {
        return confID;
    }

    /**
     * @Title: setConfID
     * @Description: 设置配置标示
     * @param confID
     * @author zhangweirong
     */
    public void setConfID(String confID) {
        this.confID = confID;
    }

    /**
     * 重载函数，得到key的值
     * 
     * @param key 取得其值的键
     * @return key的值
     */
    public String getValue(String key) {
        if (propertie.containsKey(key)) {
            String value = propertie.getProperty(key);// 得到某一属性的值
            return value;
        } else
            return "";
    }// end getValue(...)

    /**
     * 重载函数，得到key的值
     * 
     * @param fileName properties文件的路径+文件名
     * @param key 取得其值的键
     * @return key的值
     */
    public String getValue(String fileName, String key) {
        try {
            String value = "";
            if (propertie.containsKey(key)) {
                value = propertie.getProperty(key);
                return value;
            } else
                return value;
        }  catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }// end getValue(...)

    /**
     * 清除properties文件中所有的key和其值
     */
    public void clear() {
        propertie.clear();
    }// end clear();

    /**
     * 改变或添加一个key的值，当key存在于properties文件中时该key的值被value所代替， 当key不存在时，该key的值是value
     * 
     * @param key 要存入的键
     * @param value 要存入的值
     */
    public void setValue(String key, String value) {
        propertie.setProperty(key, value);
    }// end setValue(...)

    /**
     * 将更改后的文件数据存入指定的文件中，该文件可以事先不存在。
     * 
     * @param fileName 文件路径+文件名称
     * @param description 对该文件的描述
     */
    public void saveFile(String fileName, String description) {
        try {
            OutputStream outputFile = new FileOutputStream(fileName);
            propertie.store(outputFile, description);
            outputFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
