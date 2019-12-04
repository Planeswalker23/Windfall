package org.planeswalker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot启动器
 * tk.mybatis.spring.annotation.MapperScan tkMybatis工具的mapper扫描
 * @author Planeswalker23
 * @date Created in 2019-10-31
 */
@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = "org.planeswalker.mapper")
public class WindfallApplication {

    private static Logger logger = LoggerFactory.getLogger(WindfallApplication.class);

    /**
     * 启动springboot程序的主入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(WindfallApplication.class, args);
        logger.info("==================启动成功================");
    }
}
