package cn.addenda.piparbac.manager;

import cn.addenda.businesseasy.cache.CacheHelper;
import cn.addenda.piparbac.constant.RedisKeyConst;
import cn.addenda.piparbac.mapper.RuleMapper;
import cn.addenda.piparbac.po.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author addenda
 * @datetime 2022/10/13 19:04
 */
@Component
public class RuleManagerImpl implements RuleManager {

    @Autowired
    private RuleMapper ruleMapper;

    @Autowired
    private CacheHelper redisCacheHelper;

    @Override
    public String defaultRuleSqcList() {
        return "0";
    }

    @Override
    public List<Rule> queryByNonNullFields(Rule rule) {
        return ruleMapper.queryByNonNullFields(rule);
    }

    @Override
    public Rule queryBySqc(Long sqc) {
        return redisCacheHelper.queryWithPerformanceFirst(RedisKeyConst.RULE_SQC_KEY,
                sqc, Rule.class, this::doQueryBySqc, RedisKeyConst.CACHE_DEFAULT_TTL);
    }

    private Rule doQueryBySqc(Long sqc) {
        Rule rule = new Rule();
        rule.setSqc(sqc);
        List<Rule> rules = ruleMapper.queryByNonNullFields(rule);
        if (rules.isEmpty()) {
            return null;
        }
        return rules.get(0);
    }

    @Override
    public boolean ruleCodeExists(String ruleCode) {
        Integer integer = ruleMapper.ruleCodeExists(ruleCode);
        return integer != null && integer != 0;
    }

    @Override
    public void insert(Rule rule) {
        ruleMapper.insert(rule);
    }

    @Override
    public void deleteBySqc(Long sqc) {
        ruleMapper.deleteBySqc(sqc);
    }

    @Override
    public boolean sqcExists(Long sqc) {
        Integer integer = ruleMapper.sqcExists(sqc);
        return integer != null && integer != 0;
    }

    @Override
    public void update(Rule rule) {
        ruleMapper.updateNonNullFieldsBySqc(rule);
    }

    @Override
    public void setStatus(Long sqc, String status) {
        Rule rule = new Rule();
        rule.setSqc(sqc);
        rule.setStatus(status);
        ruleMapper.updateNonNullFieldsBySqc(rule);
    }

    @Override
    public Rule queryByRuleCode(String ruleCode) {
        return redisCacheHelper.queryWithPerformanceFirst(RedisKeyConst.RULE_RULECODE_KEY,
                ruleCode, Rule.class, ruleMapper::queryByRuleCode, RedisKeyConst.CACHE_DEFAULT_TTL);
    }

    @Override
    public List<Rule> queryByRuleSqcList(List<Long> ruleSqcList) {
        return ruleSqcList.stream().map(this::queryBySqc).collect(Collectors.toList());
    }

}
