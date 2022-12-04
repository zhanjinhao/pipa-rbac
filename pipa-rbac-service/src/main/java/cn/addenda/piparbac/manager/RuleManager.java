package cn.addenda.piparbac.manager;

import cn.addenda.piparbac.dto.DRule;
import cn.addenda.piparbac.po.Rule;
import cn.addenda.piparbac.po.UserRole;

import java.util.List;

public interface RuleManager {

    /**
     * 默认权限是全权限，即不进行任何过滤
     */
    String defaultRuleSqcList();

    List<Rule> queryByNonNullFields(Rule rule);

    Rule queryBySqc(Long sqc);

    boolean ruleCodeExists(String ruleCode);

    void insert(Rule rule);

    void deleteBySqc(Long sqc);

    boolean sqcExists(Long sqc);

    void update(Rule rule);

    void setStatus(Long sqc, String status);

    Rule queryByRuleCode(String ruleCode);

    List<Rule> queryByRuleSqcList(List<Long> ruleSqcList);

}
