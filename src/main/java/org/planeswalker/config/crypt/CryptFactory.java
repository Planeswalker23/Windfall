package org.planeswalker.config.crypt;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Errors;
import org.planeswalker.exception.WindfallException;

/**
 * 生成具体加密解密策略类的工厂类
 * @author Planeswalker23
 * @date Created in 2019-11-09
 */
@Slf4j
public class CryptFactory {

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
            log.error("生成[{}]策略类失败: {}", cryptStrategy, e);
            throw new WindfallException(Errors.REFLECT_ERROR);
        }
        return strategy;
    }
}
