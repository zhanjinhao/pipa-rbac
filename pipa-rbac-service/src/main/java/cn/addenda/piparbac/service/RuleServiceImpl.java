package cn.addenda.piparbac.service;

import cn.addenda.piparbac.bo.BUserRoleWithBizFields;
import cn.addenda.piparbac.manager.*;
import cn.addenda.piparbac.po.Role;
import cn.addenda.piparbac.po.Rule;
import cn.addenda.piparbac.po.User;
import cn.addenda.piparbac.po.UserRole;
import cn.addenda.piparbac.utils.StatusUtils;
import cn.addenda.se.lock.LockUtils;
import cn.addenda.se.result.ServiceException;
import cn.addenda.se.result.ServiceResult;
import cn.addenda.se.result.ServiceResultConvertible;
import cn.addenda.se.result.StatusConst;
import cn.addenda.se.transaction.TransactionUtils;
import cn.addenda.se.utils.SeBeanUtil;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author addenda
 * @datetime 2022/10/19 19:18
 */
@Component
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleManager ruleManager;

    @Autowired
    private UserRoleManager userRoleManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private UserRoleRecordManager userRoleRecordManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ServiceResultConvertible
    public ServiceResult<Long> insert(Rule rule) {
        return LockUtils.doLock(LockUtils.SYSTEM_BUSY, "rule:ruleCode", () -> {
            if (ruleManager.ruleCodeExists(rule.getRuleCode())) {
                throw new ServiceException("ruleCode已存在：" + rule.getRuleCode() + "。");
            }

            rule.setStatus(StatusUtils.ACTIVE);
            ruleManager.insert(rule);
            return ServiceResult.success(rule.getSqc());
        }, rule.getRuleCode());
    }

    @Override
    public ServiceResult<PageInfo<Rule>> pageQuery(Integer pageNum, Integer pageSize, Rule rule) {
        try {
            PageMethod.startPage(pageNum, pageSize);
            List<Rule> query = ruleManager.queryByNonNullFields(rule);
            return ServiceResult.success(new PageInfo<>(query));
        } finally {
            PageMethod.clearPage();
        }
    }

    @Override
    public ServiceResult<Rule> queryBySqc(Long sqc) {
        return ServiceResult.success(ruleManager.queryBySqc(sqc));
    }

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> update(Rule rule) {
        if (!ruleManager.sqcExists(rule.getSqc())) {
            throw new ServiceException("sqc不存在：" + rule.getSqc() + "。 ");
        }

        return TransactionUtils.doTransaction(() -> {
            ruleManager.update(rule);
            return ServiceResult.success(true);
        });
    }

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> setStatus(Long sqc, String status) {
        StatusUtils.assertDAndAThrowSe(status);
        if (!ruleManager.sqcExists(sqc)) {
            throw new ServiceException("sqc不存在：" + sqc + "。 ");
        }

        return TransactionUtils.doTransaction(() -> {
            ruleManager.setStatus(sqc, status);
            return new ServiceResult<>(StatusConst.SUCCESS, true);
        });
    }

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> deleteBySqc(Long sqc) {
        // 如果rule被用户关联，则不可删除
        if (userRoleManager.ruleSqcExists(sqc)) {
            throw new ServiceException("此Rule正被用户使用，不可删除！");
        }

        return TransactionUtils.doTransaction(() -> {
            ruleManager.deleteBySqc(sqc);
            return ServiceResult.success(true);
        });
    }

    @Override
    public ServiceResult<List<BUserRoleWithBizFields>> queryUserRoleOnRule(Long ruleSqc) {
        if (!ruleManager.sqcExists(ruleSqc)) {
            throw new ServiceException("ruleSqc不存在！");
        }
        List<UserRole> userRoleList = userRoleManager.queryUserRoleOnRule(ruleSqc);
        List<BUserRoleWithBizFields> resultList = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            BUserRoleWithBizFields result = SeBeanUtil.copyProperties(userRole, new BUserRoleWithBizFields());
            User user = userManager.queryBySqc(result.getUserSqc());
            SeBeanUtil.copyProperties(user, result);
            Role role = roleManager.queryBySqc(result.getRoleSqc());
            SeBeanUtil.copyProperties(role, result);
            resultList.add(result);
        }
        return ServiceResult.success(resultList);
    }

    @Override
    public List<Rule> queryRuleList(String userId) {
        User user = userManager.queryByUserId(userId);
        if (user == null) {
            throw new ServiceException("用户不存在：" + userId + "。");
        }
        UserRole userRole = userRoleRecordManager.queryLoginRole(user.getSqc());
        if (userRole == null) {
            throw new ServiceException("用户未登录：" + userId + "。");
        }
        String ruleSqcListStr = userRole.getRuleSqcList();
        if (!StringUtils.hasText(ruleSqcListStr)) {
            return new ArrayList<>();
        }
        List<Long> ruleSqcList = Arrays.stream(ruleSqcListStr.split(","))
                .map(Long::valueOf).collect(Collectors.toList());
        return ruleManager.queryByRuleSqcList(ruleSqcList);
    }

}
