package tzc.badminton.config.crypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tzc.badminton.base.Constant;
import tzc.badminton.exception.WindfallException;

/**
 * 生成具体加密解密策略类的工厂类
 * @author Planeswalker23
 * @date Created in 2019-11-09
 */
public class CryptFactory {

    private static Logger logger = LoggerFactory.getLogger(CryptFactory.class);

    /**
     * 根据传入的cryptType枚举类生成策略执行类
     * @param cryptStrategy 实现CryptStrategy接口的策略类
     * @return CryptStrategy
     */
    public static CryptStrategy build(Class<? extends CryptStrategy> cryptStrategy) {
        CryptStrategy strategy;
        try {
            strategy = (CryptStrategy) Class.forName(cryptStrategy.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            logger.error("生成[{}]策略类失败: {}", cryptStrategy, e);
            throw new WindfallException(Constant.REFLECT_ERROR);
        }
        return strategy;
    }
}
