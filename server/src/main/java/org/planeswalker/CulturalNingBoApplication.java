package org.planeswalker;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.github.pagehelper.PageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * springboot启动器
 * @see MapperScan 开启 mapper 文件的扫描
 * @author Planeswalker23
 * @date Created in 2019-10-31
 */
@Slf4j
@SpringBootApplication
@MapperScan("org.planeswalker.mapper")
public class CulturalNingBoApplication {

    /**
     * 启动springboot程序的主入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CulturalNingBoApplication.class, args);
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

    /**
     * 分页插件，需要手动注入
     * @return {@link PageInterceptor}
     */
    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
