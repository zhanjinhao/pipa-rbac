package cn.addenda.piparbac.config;

import cn.addenda.se.transaction.TransactionHelper;
import cn.addenda.se.transaction.TransactionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author addenda
 * @datetime 2022/10/23 20:12
 */
@Configuration
public class TransactionUtilsConfig {

    @Bean
    public TransactionHelper transactionHelper() {
        return new TransactionHelper();
    }

    @Bean
    public TransactionUtils transactionUtils() {
        return new TransactionUtils();
    }

}
