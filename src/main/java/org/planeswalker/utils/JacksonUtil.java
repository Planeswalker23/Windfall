package org.planeswalker.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Errors;
import org.planeswalker.exception.WindfallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * json解析工具——jackson
 * @author Planeswalker23
 * @date Created in 2019-11-08
 */
@Slf4j
@Component
public class JacksonUtil {

    private static ObjectMapper objectMapper;

    /**
     * setter注入static对象objectMapper
     * @param objectMapperFromSpring 将spring容器自动根据配置文件new的ObjectMapper对象注入给util类中的objectMapper
     *                               否则会因为静态类对象的NPE
     *        FAIL_ON_UNKNOWN_PROPERTIES-false 忽略String中存在而实体类不存在的字段，防止报错
     */
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapperFromSpring){
        // String转化为实体类时，忽略String中存在而实体类不存在的字段，防止报错
        objectMapperFromSpring.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper = objectMapperFromSpring;
    }

    /**
     * 将任意对象转换成json格式
     * @param object 任意对象
     * @return
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new WindfallException(Errors.TO_JSON_FAILED);
        }
    }

    /**
     * 将字符串转换成指定类型的对象
     * @param json 被转换的字符串
     * @param clazz 指定对象的类型
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new WindfallException(Errors.TO_JSON_FAILED);
        }
    }

    /**
     * 将字符串转换成list对象
     * @param json 被转换的字符串
     * @param clazz 指定对象的类型
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<? extends T> clazz){
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new WindfallException(Errors.TO_JSON_FAILED);
        }
    }
}
