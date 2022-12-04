package cn.addenda.piparbac.rpc;

import cn.addenda.piparbac.dto.DRule;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/11/26 20:45
 */
public interface RuleRpc {

    List<DRule> queryRuleList(String userId);

}
