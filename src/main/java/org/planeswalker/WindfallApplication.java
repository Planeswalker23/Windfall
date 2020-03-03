package org.planeswalker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot启动器
 * @author Planeswalker23
 * @date Created in 2019-10-31
 */
@Slf4j
@SpringBootApplication
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
