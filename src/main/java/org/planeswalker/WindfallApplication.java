package org.planeswalker;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

/**
 * springboot启动器
 * tk.mybatis.spring.annotation.MapperScan tkMybatis工具的mapper扫描
 * org.springframework.boot.web.servlet.ServletComponentScan 开启过滤器扫描
 * @author Planeswalker23
 * @date Created in 2019-10-31
 */
@Slf4j
@SpringBootApplication
@MapperScan("org.planeswalker.mapper")
@ServletComponentScan("org.planeswalker.config")
public class WindfallApplication {

    /**
     * 启动springboot程序的主入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(WindfallApplication.class, args);
        log.info("==================启动成功================");
    }

    /**
     * 乐观锁拦截器
     * @return {@link com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor}
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
