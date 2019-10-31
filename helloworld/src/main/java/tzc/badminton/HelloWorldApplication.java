package tzc.badminton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot启动器
 * @author Planeswalker23
 * @date Created in 2019-10-11
 */
@SpringBootApplication
public class HelloWorldApplication {

    /**
     * 启动springboot程序的主入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}
