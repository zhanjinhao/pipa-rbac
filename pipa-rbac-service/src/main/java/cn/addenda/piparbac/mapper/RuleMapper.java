package cn.addenda.piparbac.mapper;

import cn.addenda.me.constant.Constants;
import cn.addenda.me.fieldfilling.annotation.DQLFieldFilling;
import cn.addenda.piparbac.po.Rule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RuleMapper {

    void insert(Rule rule);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer ruleCodeExists(String ruleCode);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer sqcExists(Long sqc);

    void deleteBySqc(@Param("sqc") Long sqc);

    void updateNonNullFieldsBySqc(Rule rule);

    List<Rule> queryByNonNullFields(Rule rule);

    Rule queryByRuleCode(@Param("ruleCode") String ruleCode);

}
