package cn.addenda.piparbac.rpc;

import cn.addenda.businesseasy.util.BEAssertUtils;
import cn.addenda.piparbac.dto.DRule;
import cn.addenda.piparbac.po.Rule;
import cn.addenda.piparbac.service.RuleService;
import cn.addenda.se.utils.SeBeanUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author addenda
 * @datetime 2022/12/4 14:11
 */
@DubboService(protocol = "dubbo")
public class RuleRpcImpl implements RuleRpc {

    @Autowired
    private RuleService ruleService;

    @Override
    public List<DRule> queryRuleList(String userId) {
        BEAssertUtils.notNull(userId);
        List<Rule> rules = ruleService.queryRuleList(userId);
        return rules.stream().map(r -> SeBeanUtil.copyProperties(r, new DRule())).collect(Collectors.toList());
    }

}
