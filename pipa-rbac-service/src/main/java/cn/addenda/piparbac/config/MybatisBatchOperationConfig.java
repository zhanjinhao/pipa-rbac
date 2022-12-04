package cn.addenda.piparbac.config;

import cn.addenda.me.helper.MybatisBatchOperationHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author addenda
 * @datetime 2022/10/27 19:31
 */
@Configuration
public class MybatisBatchOperationConfig {

    @Bean
    public MybatisBatchOperationHelper mybatisBatchOperationHelper(SqlSessionFactory sqlSessionFactory) {
        return new MybatisBatchOperationHelper(sqlSessionFactory);
    }

}
