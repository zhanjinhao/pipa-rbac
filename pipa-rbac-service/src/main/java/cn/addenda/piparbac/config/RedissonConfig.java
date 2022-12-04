package cn.addenda.piparbac.config;

import cn.addenda.se.springcontext.ValueResolverUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(ValueResolverUtil valueResolverUtil) {
        // 配置
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://"
                        + valueResolverUtil.resolveFromContext("${redis.host}")
                        + ":"
                        + valueResolverUtil.resolveFromContext("${redis.port}"))
                .setPassword(valueResolverUtil.resolveFromContext("${redis.password}"));
        // 创建RedissonClient对象
        return Redisson.create(config);
    }

}
