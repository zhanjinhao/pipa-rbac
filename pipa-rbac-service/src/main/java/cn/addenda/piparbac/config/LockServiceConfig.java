package cn.addenda.piparbac.config;

import cn.addenda.businesseasy.lock.LockService;
import cn.addenda.businesseasy.lock.RedissonLockService;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author addenda
 * @datetime 2022/12/2 18:52
 */
@Configuration
public class LockServiceConfig {

    @Bean
    public LockService redissonLockService(RedissonClient redissonClient) {
        return new RedissonLockService(redissonClient);
    }

}
