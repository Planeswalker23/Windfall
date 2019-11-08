package tzc.badminton.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tzc.badminton.base.Constant;
import tzc.badminton.exception.WindfallException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * json解析工具——jackson
 * @author Planeswalker23
 * @date Created in 2019-11-08
 */
@Component
public class JacksonUtil {

    private static ObjectMapper objectMapper;

    private static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

    /**
     * setter注入static对象objectMapper
     * @param objectMapperFromSpring 将spring容器自动根据配置文件new的ObjectMapper对象注入给util类中的objectMapper
     *                               否则会因为静态类对象的NPE
     */
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapperFromSpring){
        objectMapper = objectMapperFromSpring;
    }

    /**
     * 将任意对象转换成json格式
     * @param object 任意对象
     * @return
     */
    public static String toJSON(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            throw new WindfallException(Constant.TO_JSON_FAILED);
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
            logger.error(e.getMessage(), e);
            throw new WindfallException(Constant.TO_JSON_FAILED);
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
            logger.error(e.getMessage(), e);
            throw new WindfallException(Constant.TO_JSON_FAILED);
        }
    }
}
