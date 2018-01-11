package com.autobid.util;

//import java.io.BufferedInputStream;
//import java.io.FileNotFoundException;

import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

//import java.io.InputStreamReader;
//import java.io.Reader;

//import org.apache.log4j.Logger;

public class ConfUtil {

    //private static Logger logger = Logger.getLogger(ConfUtil.class);

    private static Properties prop = new OrderProperties();
    //private static Reader in = new InputStreamReader(ConfUtil.class.getResourceAsStream("config.properties"));

    //InputStream in = ConfUtil.class.getClassLoader().getResourceAsStream("Config.properties");

    private static String fileName = System.getProperty("user.dir") + "/config.properties";

    //private static String fileName = "config.properties";

    public static ConfBean readAllToBean() throws Exception {
        InputStream in = new FileInputStream(fileName);
        prop.load(in);
        Set<String> keySet = prop.stringPropertyNames();
        ConfBean cb = new ConfBean();
        //��ȡ����
        for (String propName : keySet) {
            cb.setAmountBegin(prop.getProperty("amount_begin"));

            //���»����ַ�����Ϊ�շ��ַ�������������ĸ��д��ǰ�����set�õ�������
            String methodName = "set" + camelCaseName(captureName(propName));

            //������ͺ������õ�Method����
            Method method = cb.getClass().getDeclaredMethod(methodName, String.class);

            //����ִ�и�method�ķ�������set��ֵ����������ֵΪ�����ļ�������
            method.invoke(cb, prop.getProperty(propName));
        }

        //System.out.println(cb.getClass().getDeclaredMethod("getAmountBegin").invoke(cb));
        in.close();
        return cb;
    }


    private static String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

    private static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static String getProperty(String key) throws IOException {
        //InputStream in = new BufferedInputStream (new FileInputStream(fileName));
        //InputStream in = ConfUtil.class.getClassLoader().getResourceAsStream(fileName);
        //InputStream in = new FileInputStream(fileName);
        //prop.load(in);     ///���������б�

        InputStreamReader inputStreamReader;
        InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
        //prop.load(in);//ֱ����ôд�����properties�ļ����к��֣����ֻ����롣��Ϊδ���ñ����ʽ��
        inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        prop.load(inputStreamReader);
        inputStreamReader.close();
        //System.out.println(key + ":"+value);
        return prop.getProperty(key);
    }

    public static void setProperty(String key, String value) throws IOException {
        InputStreamReader inputStreamReader;
        InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
        //prop.load(in);//ֱ����ôд�����properties�ļ����к��֣����ֻ����롣��Ϊδ���ñ����ʽ��
        inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        //һ��Ҫ��װ�������ļ�
        prop.load(inputStreamReader);
        FileOutputStream oFile = new FileOutputStream(fileName);//new FileOutputStream(fileName, true)
        prop.setProperty(key, value);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String nowDate = sdf.format(new Date());
        prop.store(oFile, "Copyright (c) SkyWalker Studio, modified date is " + nowDate);
        oFile.close();
    }

/*    public void readConf() throws IOException {
        //��ȡ�����ļ�
        //InputStream in = new BufferedInputStream (new FileInputStream(fileName));
        //InputStream in = ConfUtil.class.getClassLoader().getResourceAsStream(fileName);
        InputStream in = new FileInputStream(fileName);
        prop.load(in);
        //prop.load(in);     ///���������б�
        Iterator<String> it = prop.stringPropertyNames().iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key + ":" + prop.getProperty(key));
        }
        in.close();
    }*/
}