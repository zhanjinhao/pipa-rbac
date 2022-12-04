package cn.addenda.piparbac.config;

import cn.addenda.se.springcontext.ValueResolverUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author addenda
 * @datetime 2022/10/23 22:15
 */
@Configuration
public class ValueResolverConfig {

    @Bean
    public ValueResolverUtil valueResolverUtil() {
        return new ValueResolverUtil();
    }

}
