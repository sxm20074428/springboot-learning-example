package com.sxm.springboot.utils.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * toJSON && fromJSON 的工具类
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/3/9 9:47
 * @since 0.1
 */
public class JacksonHelper implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(JacksonHelper.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 是否打印美观格式
     */
    private static boolean isPretty = false;

    static {
        if (isPretty) {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

    }


    /**
     * Java对象转Json字符串
     *
     * @param obj Java对象，可以是对象，数组，List,Map等
     * @return json 字符串
     * @author 苏晓蒙
     * @time 2017/3/9 10:08
     * @version 0.1
     * @since 0.1
     */
    public static String convertObjectToJson(Object obj) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            logger.error("convertObjectToJson:", e);
        }
        return jsonString;
    }

    /**
     * Json字符串转Java对象
     *
     * @param jsonString
     * @param clazz
     * @return Object
     * @author 苏晓蒙
     * @time 2017/3/9 10:08
     * @version 0.1
     * @since 0.1
     */
    public static Object convertJsonToObject(String jsonString, Class<?> clazz) {

        if (jsonString == null || "".equals(jsonString)) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            logger.error("convertJsonToObject:", e);
        }
        return null;
    }


    /**
     * JSON串转换为Java泛型对象，可以是各种类型
     *
     * @param <T>
     * @param jsonString    JSON字符串
     * @param typeReference TypeReference,例如: new TypeReference< List<FamousUser> >(){}
     * @return List对象列表
     * @author 苏晓蒙
     * @time 2017/3/9 10:08
     * @version 0.1
     * @since 0.1
     */
    public static <T> T convertJsonToGenericObject(String jsonString, TypeReference<T> typeReference) {

        if (jsonString == null || "".equals(jsonString)) {
            return null;
        }

        try {
            return (T) objectMapper.readValue(jsonString, typeReference);
        } catch (Exception e) {
            logger.warn("convertJsonToGenericObject:" + e);
        }
        return null;
    }

}
