package org.planeswalker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot启动器
 * ServletComponentScan注解 开启WebFilter注解扫描
 * tk.mybatis.spring.annotation.MapperScan tkMybatis工具的mapper扫描
 * @author Planeswalker23
 * @date Created in 2019-10-31
 */
@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = "org.planeswalker.mapper")
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    /**
     * 启动springboot程序的主入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("==================启动成功================");
    }
}
