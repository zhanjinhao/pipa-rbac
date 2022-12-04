package cn.addenda.piparbac;

import cn.addenda.se.lock.EnableLockManagement;
import cn.addenda.se.multidatasource.EnableMultiDataSource;
import cn.addenda.se.paramreslog.EnableParamResLog;
import cn.addenda.se.propertyrefresh.EnablePropertyRefresh;
import cn.addenda.se.result.EnableServiceResultConversion;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author addenda
 * @datetime 2022/1/14 15:44
 */
@EnablePropertyRefresh
@EnableLockManagement(namespace = "piparbac", order = Ordered.LOWEST_PRECEDENCE - 60)
@EnableTransactionManagement(order = Ordered.LOWEST_PRECEDENCE - 70)
@EnableMultiDataSource(order = Ordered.LOWEST_PRECEDENCE - 80)
@EnableParamResLog(order = Ordered.LOWEST_PRECEDENCE - 90)
@EnableServiceResultConversion(order = Ordered.LOWEST_PRECEDENCE - 100)
@MapperScan("cn.addenda.piparbac.mapper")
@SpringBootApplication
public class PipaRBACApplication {

    public static void main(String[] args) {
        SpringApplication.run(PipaRBACApplication.class, args);
    }

}
