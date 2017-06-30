package com.weweb.cloud.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jackshen on 2017/6/25.
 */
public class PropertiesUtils {
    public static Properties loadProperties(String propertiesPath) {
        Properties properties=new Properties();
        InputStream inputStream = null;
        try {

            if (propertiesPath == null || propertiesPath.length() == 0)
                throw new IllegalAccessException();
            String suffix = ".properties";
            if (propertiesPath.lastIndexOf(suffix) == -1) {
                propertiesPath += suffix;
            }
            inputStream = getClassLoader().getResourceAsStream(propertiesPath);
            if (inputStream != null) {
                properties.load(inputStream);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(inputStream!=null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return properties;
    }
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }
    /**
     * 获取字符型属性
     */
    public static String getString(Properties props, String key) {
        String value = "";
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }
}
