package cn.addenda.piparbac.config;

import cn.addenda.businesseasy.lock.LockService;
import cn.addenda.se.lock.LockConfigurer;
import cn.addenda.se.lock.LockHelper;
import cn.addenda.se.lock.LockUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author addenda
 * @datetime 2022/11/30 19:43
 */
@Configuration
public class LockConfig {

    @Bean
    public LockConfigurer lockConfigurer(@Qualifier("redissonLockService") LockService lockService) {
        return new LockConfigurer(lockService);
    }

    @Bean
    public LockHelper lockHelper() {
        return new LockHelper();
    }

    @Bean
    public LockUtils lockUtils(@Qualifier("redissonLockService") LockService lockService) {
        return new LockUtils("piparbac", lockService);
    }

}
