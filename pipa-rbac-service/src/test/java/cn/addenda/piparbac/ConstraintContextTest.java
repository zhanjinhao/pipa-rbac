package cn.addenda.piparbac;

import cn.addenda.me.constraint.ConstraintContext;
import cn.addenda.piparbac.dto.DRule;
import cn.addenda.piparbac.manager.RoleModuleManager;
import cn.addenda.piparbac.po.Role;
import cn.addenda.piparbac.rpc.RuleRpc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/12/4 15:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConstraintContextTest {

    @Autowired
    private RuleRpc ruleRpc;

    @Autowired
    private RoleModuleManager roleModuleManager;

    @Test
    public void test1() {
        List<DRule> dRules = ruleRpc.queryRuleList("126");
        dRules.forEach(System.out::println);
        System.out.println("------------------------");
        for (DRule dRule : dRules) {
            ConstraintContext.addTableConstraint(dRule.getTableName(), dRule.getCondition());
        }
        try {
            List<Role> roles = roleModuleManager.queryRoleOnModule(10000L);
            roles.forEach(System.out::println);
        } finally {
            ConstraintContext.clearConstraint();
        }
    }

}
