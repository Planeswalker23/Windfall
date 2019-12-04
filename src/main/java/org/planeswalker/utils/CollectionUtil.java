package org.planeswalker.utils;

import org.planeswalker.base.Constant;
import org.planeswalker.base.Errors;
import org.planeswalker.exception.WindfallException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 集合工具类
 * @author Planeswalker23
 * @date Created in 2019-11-09
 */
public class CollectionUtil {

    private static Logger logger = LoggerFactory.getLogger(CollectionUtil.class);

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
            logger.error("查询结果大于一条记录，数据错误");
            throw new WindfallException(Errors.WRONG_DATA);
        }
    }
}
