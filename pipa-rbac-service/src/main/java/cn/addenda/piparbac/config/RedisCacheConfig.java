package cn.addenda.piparbac.config;

import cn.addenda.businesseasy.cache.CacheHelper;
import cn.addenda.businesseasy.cache.KVOperator;
import cn.addenda.businesseasy.lock.LockService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author addenda
 * @datetime 2022/10/27 19:32
 */
@Configuration
public class RedisCacheConfig {

    @Bean
    public CacheHelper redisCacheHelper(StringRedisTemplate stringRedisTemplate,
                                        @Qualifier("redissonLockService") LockService lockService) {
        return new CacheHelper(new RedisKVOperator(stringRedisTemplate), lockService);
    }

    private static class RedisKVOperator implements KVOperator<String, String> {

        private final StringRedisTemplate stringRedisTemplate;

        public RedisKVOperator(StringRedisTemplate stringRedisTemplate) {
            this.stringRedisTemplate = stringRedisTemplate;
        }

        @Override
        public void set(String key, String value) {
            stringRedisTemplate.opsForValue().set(key, value);
        }

        @Override
        public void set(String key, String value, long timeout, TimeUnit unit) {
            stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
        }

        @Override
        public String get(String key) {
            return stringRedisTemplate.opsForValue().get(key);
        }

        @Override
        public void delete(String key) {
            stringRedisTemplate.delete(key);
        }

        @Override
        public String remove(String key) {
            return stringRedisTemplate.opsForValue().getAndDelete(key);
        }
    }

}
