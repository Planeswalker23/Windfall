package org.planeswalker.utils;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Errors;
import org.planeswalker.exception.WindfallException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * class的工具类
 * @author Planeswalker23
 * @date Created in 2019-11-08
 */
@Slf4j
public class ClassUtil {

    /**
     * 根据指定的annotation查找clazz类中被此注解修饰的属性
     * @param clazz 从此类的属性中判定
     * @param annotationClass 判定clazz的属性中是否包含此注解
     *                        若此类不属于注解会抛出{@link WindfallException}异常
     * @return List<Field>
     */
    public static List<Field> getAnnotationFieldsByAnnotation(Class clazz, Class annotationClass) {
        // 验证annotationClass是否属于注解类型
        if (!annotationClass.isAnnotation()) {
            log.error("[{}] {}", annotationClass.toString(), Errors.NOT_BELONG_TO_ANNOTATION);
            throw new WindfallException(annotationClass.toString() + Errors.NOT_BELONG_TO_ANNOTATION);
        }
        // 含有annotationClass注解的属性集合
        List<Field> annotationFields = new ArrayList<>();
        // 获取对象中的所有属性
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        fields.forEach(field -> {
            // 获取属性上的annotation注解，若存在，加入返回的列表中
            if (field.getAnnotation(annotationClass) != null) {
                annotationFields.add(field);
            }
        });
        return annotationFields;
    }

    /**
     * 根据属性名获取对象中的该属性的值
     * @param object 对象
     * @param fieldName 属性名
     * @return Object
     */
    public static Object getValueByFieldName(Object object, String fieldName) {
        try {
            // 根据fieldName获取该属性
            Field field = ClassUtil.getFieldFromClassByName(object, fieldName);
            return field.get(object);
        } catch (IllegalAccessException e) {
            log.error("获取属性对象值失败，Caused by: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据属性名为对象中的该属性赋值
     * @param object 对象
     * @param fieldName 属性名
     * @param newValue 赋值到属性的具体值
     */
    public static void setValueByFieldName(Object object, String fieldName, Object newValue) {
        try {
            // 根据fieldName获取该属性
            Field field = ClassUtil.getFieldFromClassByName(object, fieldName);
            field.set(object, newValue);
        } catch (IllegalAccessException e) {
            log.error("为属性对象赋值，Caused by: {}", e.getMessage(), e);
            throw new WindfallException(Errors.REFLECT_ERROR);
        }
    }

    /**
     * 根据fieldName从object对象中或该属性
     * @param object 目标类
     * @param fieldName 属性名
     * @return Field
     */
    private static Field getFieldFromClassByName(Object object, String fieldName) {
        Class clazz = object.getClass();
        Field field;
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(fieldName);
                // 开启访问权限，对private的属性
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                // 循环父类
                clazz = clazz.getSuperclass();
            }
        }
        log.error("获取属性对象[{}]失败，可能类[{}]且其父类都不存在此字段:", fieldName, object.getClass());
        return null;
    }
}
