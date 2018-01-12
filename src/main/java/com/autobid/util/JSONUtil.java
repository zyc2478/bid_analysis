package com.autobid.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.IOException;

/**
 * 实体类和JSON对象之间相互转化（依赖包jackson-all-1.7.6.jar、jsoup-1.5.2.jar）
 * @author Richard Zeng
 *
 */
public class JSONUtil {


    /*
     * @Method: JSONToObj
     * @Description: 将JSON转化为实体Object
     * @Param: [jsonStr, obj]
     * @Return: java.lang.Object
     * @Date: 2018/1/12 15:23
     */
    public static <T> Object JSONToObj(String jsonStr, Class<T> obj) {
        T t = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            t = objectMapper.readValue(jsonStr,
                    obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * @param obj
     * @param <T>
     * @return
     * @throws JSONException
     * @throws IOException
     */
    public static <T> JSONObject objectToJson(T obj) throws JSONException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Convert object to JSON string
        String jsonStr = "";
        try {
            jsonStr = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw e;
        }
        return JSONObject.fromObject(jsonStr);
    }
}
