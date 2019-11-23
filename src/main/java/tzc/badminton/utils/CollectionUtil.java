package tzc.badminton.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import tzc.badminton.base.Constant;
import tzc.badminton.exception.WindfallException;

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
            throw new WindfallException(Constant.EMPTY_COLLECTION);
        }
        if (collection.size() > Constant.ONE) {
            logger.error("查询结果大于一条记录，数据错误");
            throw new WindfallException(Constant.WRONG_DATA);
        }
    }
}
