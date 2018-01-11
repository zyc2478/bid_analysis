package com.autobid.util;

import org.apache.log4j.Logger;

/**
 * Created by Richard Zeng on 2017/9/26.
 */
public class Log4JUtil {

    private static Logger logger = null;

    public static Logger getLogger() {
/*        if (null == logger) {
            //Java8 废弃了Reflection.getCallerClass()
            //logger = Logger.getLogger(Reflection.getCallerClass().getName());
            //logger.debug("调用者类名"+Reflection.getCallerClass().getName());


        }*/
        return logger;
    }
}  