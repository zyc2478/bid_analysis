package com.autobid.util;

import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

//import java.io.FileNotFoundException;

public class MainOrder {

    private static String fileName = System.getProperty("user.dir") + "/config.properties";

    public static void main(String[] args) throws IOException {
        Properties prop = readOrderedPropertiesFile();
        printProp(prop);
        writeOrderedPropertiesFile(prop);
    }

    /**
     * 输出properties的key和value
     */
    static void printProp(Properties properties) {
        System.out.println("---------（方式一）------------");
        for (String key : properties.stringPropertyNames()) {
            System.out.println(key + "=" + properties.getProperty(key));
        }

        System.out.println("---------（方式二）------------");
        Set<Object> keys = properties.keySet();//返回属性key的集合  
        for (Object key : keys) {
            System.out.println(key.toString() + "=" + properties.get(key));
        }

        System.out.println("---------（方式三）------------");
        Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();//返回的属性键值对实体  
        for (Map.Entry<Object, Object> entry : entrySet) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        System.out.println("---------（方式四）------------");
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            System.out.println(key + "=" + value);
        }
    }

    /**
     * 读Properties文件（有序）
     */
    private static Properties readOrderedPropertiesFile() {
        Properties properties = new OrderProperties();
        InputStreamReader inputStreamReader = null;
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
            //prop.load(in);//直接这么写，如果properties文件中有汉字，则汉字会乱码。因为未设置编码格式。  
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            properties.load(inputStreamReader);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return properties;
    }

    /**
     * 写Properties文件（有序）
     *
     */
    private static void writeOrderedPropertiesFile(Properties properties) throws IOException {
        //Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(fileName);
        //一定要先装载现有的配置文件
        properties.load(fis);
        properties.setProperty("phone", "10086");
        OutputStreamWriter outputStreamWriter = null;
        try {
            //保存属性到b.properties文件  
            FileOutputStream fileOutputStream = new FileOutputStream(fileName, false);//true表示追加打开,false每次都是清空再重写  
            //prop.store(oFile, "此参数是保存生成properties文件中第一行的注释说明文字");//这个会两个地方乱码  
            //prop.store(new OutputStreamWriter(oFile, "utf-8"), "汉字乱码");//这个就是生成的properties文件中第一行的注释文字乱码  
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
            properties.store(outputStreamWriter, "lll");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}  