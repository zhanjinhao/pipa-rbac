package cn.addenda.piparbac.service;

import cn.addenda.piparbac.bo.BUserRoleWithBizFields;
import cn.addenda.piparbac.dto.DRule;
import cn.addenda.piparbac.po.Rule;
import cn.addenda.piparbac.po.UserRole;
import cn.addenda.se.result.ServiceResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/10/19 19:18
 */
public interface RuleService {

    ServiceResult<PageInfo<Rule>> pageQuery(Integer pageNum, Integer pageSize, Rule rule);

    ServiceResult<Rule> queryBySqc(Long sqc);

    ServiceResult<Long> insert(Rule rule);

    ServiceResult<Boolean> deleteBySqc(Long sqc);

    ServiceResult<Boolean> update(Rule rule);

    ServiceResult<Boolean> setStatus(Long sqc, String status);

    ServiceResult<List<BUserRoleWithBizFields>> queryUserRoleOnRule(Long ruleSqc);

    List<Rule> queryRuleList(String userId);

}
