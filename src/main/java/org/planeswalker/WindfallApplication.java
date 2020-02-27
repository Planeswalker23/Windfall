package org.planeswalker;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot启动器
 * @see MapperScan 开启 mapper 文件的扫描
 * @author Planeswalker23
 * @date Created in 2019-10-31
 */
@Slf4j
@SpringBootApplication
@MapperScan("org.planeswalker.mapper")
public class WindfallApplication {

    /**
     * 启动springboot程序的主入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(WindfallApplication.class, args);
        log.info("==================启动成功================");
    }
}
