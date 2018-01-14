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
     * @Description: JSON String convert to entity object
     * @Param: [jsonStr, obj]
     * @Return: java.lang.Object
     * @Date: 18/1/14 下午5:28
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

    /*
     * @Method: objectToJson
     * @Description: Object convert to JSON String
     * @Param: [obj]
     * @Return: net.sf.json.JSONObject
     * @Date: 18/1/14 下午5:27
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
