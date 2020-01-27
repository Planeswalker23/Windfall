package org.planeswalker.utils;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.base.Errors;
import org.planeswalker.exception.WindfallException;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 集合工具类
 * @author Planeswalker23
 * @date Created in 2019-11-09
 */
@Slf4j
public class CollectionUtil {

    /**
     * 验证集合中只有一个元素
     * 若集合为空，抛异常
     * 若集合中包含超过一个元素，抛异常
     * @param collection
     */
    public static void isOneDate(Collection collection) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new WindfallException(Errors.EMPTY_COLLECTION);
        }
        if (collection.size() > Constant.ONE) {
            log.error("查询结果大于一条记录，数据错误");
            throw new WindfallException(Errors.WRONG_DATA);
        }
    }
}
