package cn.addenda.piparbac.config;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
import org.springframework.context.annotation.Configuration;

/**
 * @author addenda
 * @datetime 2022/10/2 10:02
 */
@Configuration
@NacosPropertySources({
        @NacosPropertySource(
                groupId = "rbac",
                dataId = "dependentUrl.properties",
                autoRefreshed = true, type = ConfigType.PROPERTIES),
})
public class NacosConfig {
}
