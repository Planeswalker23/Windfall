package org.planeswalker.controller;

import org.planeswalker.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis 控制层
 * @author fanyidong
 * @date Created in 2020/11/18
 */
@RestController
public class RedisController {

    @Resource
    private RedisService redisService;

    /**
     * http://127.0.0.1:8080/redis/lock?key=fanyidong&value=lock&version=3
     * @param version
     * @param key
     * @param value
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/redis/lock")
    public Object lock(int version, String key, String value) throws InterruptedException {
        boolean islock = false;
        long now = System.currentTimeMillis();
        if (version==1) {
            // 存在分布式死锁问题
            islock = redisService.lock1(key, value);
        } else if (version==2) {
            // 存在自动解锁问题 && 存在无法区分不同客户端操作的问题
            // todo 通过 Redission 的 wtach dog 为分布式锁自动续期，定时监控此key，若依旧被该客户端持有，更新过期时间
            islock = redisService.lock2(key, RedisService.ServerSingle + now + 10000, 10, TimeUnit.SECONDS);
        } else if (version==3) {
            // 存在自动解锁问题
            islock = redisService.lock3(key, RedisService.ServerSingle + now + 10000, 10, TimeUnit.SECONDS);
        }
        // 加锁成功
        if (!islock) {
            return false;
        }
        // 处理业务大于过期时间
//        TimeUnit.SECONDS.sleep(11);
        // 解锁
        return redisService.unlock1(key);
    }

    /**
     * 测试可重入锁
     * http://127.0.0.1:8080/redis/lockAgain?key=fanyidong
     * @param key
     * @return
     */
    @GetMapping("redis/lockAgain")
    public Object lockAgain(String key) {
        if (redisService.get(key)==null) {
            return false;
        }
        return redisService.lock3(key, RedisService.ServerSingle + System.currentTimeMillis() + 10000, 10, TimeUnit.SECONDS);
    }

    /**
     * http://127.0.0.1:8080/redis/get?key=fanyidong
     * @param key
     * @return
     */
    @GetMapping("/redis/get")
    public Object get(String key) {
        return redisService.get(key);
    }
}
