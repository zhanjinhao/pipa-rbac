package cn.addenda.piparbac.controller;

import cn.addenda.businesseasy.util.BEAssertUtils;
import cn.addenda.piparbac.po.Rule;
import cn.addenda.piparbac.service.RuleService;
import cn.addenda.piparbac.vo.VRule;
import cn.addenda.piparbac.vo.VUserRoleWithBizFields;
import cn.addenda.se.result.ControllerResult;
import cn.addenda.se.utils.SeBeanUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author addenda
 * @datetime 2022/10/19 19:16
 */
@RestController
@RequestMapping("/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @PostMapping("/insert")
    public ControllerResult<Long> insert(@RequestBody VRule rule) {
        BEAssertUtils.notNull(rule);
        BEAssertUtils.notNull(rule.getRuleCode(), "ruleCode");
        BEAssertUtils.notNull(rule.getRuleName(), "ruleName");
        BEAssertUtils.notNull(rule.getTableName(), "tableName");
        BEAssertUtils.notNull(rule.getCondition(), "condition");

        return ControllerResult.create(ruleService.insert(SeBeanUtil.copyProperties(rule, new Rule())));
    }

    @GetMapping("/pageQuery")
    public ControllerResult<PageInfo<Rule>> pageQuery(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody VRule rule) {
        BEAssertUtils.notNull(pageNum, "pageNum");
        BEAssertUtils.notNull(pageSize, "pageSize");
        BEAssertUtils.notNull(rule);

        return ControllerResult.create(ruleService.pageQuery(pageNum, pageSize, SeBeanUtil.copyProperties(rule, new Rule())));
    }

    @GetMapping("/queryBySqc")
    public ControllerResult<Rule> queryBySqc(@RequestParam("sqc") Long sqc) {
        BEAssertUtils.notNull(sqc, "sqc");

        return ControllerResult.create(ruleService.queryBySqc(sqc));
    }

    @PutMapping("/update")
    public ControllerResult<Boolean> update(@RequestParam("sqc") Long sqc, @RequestBody VRule rule) {
        BEAssertUtils.notNull(sqc, "sqc");
        BEAssertUtils.notNull(rule);
        BEAssertUtils.notNull(rule.getRuleName(), "ruleName");
        BEAssertUtils.notNull(rule.getTableName(), "tableName");
        BEAssertUtils.notNull(rule.getCondition(), "condition");

        BEAssertUtils.notModified(rule.getRuleCode(), "ruleCode");
        return ControllerResult.create(ruleService.update(SeBeanUtil.copyProperties(rule, new Rule(sqc))));
    }

    @PutMapping("/setStatus")
    public ControllerResult<Boolean> setStatus(@RequestParam("sqc") Long sqc, @RequestBody String status) {
        BEAssertUtils.notNull(sqc, "sqc");
        BEAssertUtils.notNull(status);

        return ControllerResult.create(ruleService.setStatus(sqc, status));
    }

    @DeleteMapping("/deleteBySqc")
    public ControllerResult<Boolean> deleteBySqc(@RequestBody Long sqc) {
        BEAssertUtils.notNull(sqc);

        return ControllerResult.create(ruleService.deleteBySqc(sqc));
    }

    @GetMapping("/queryUserRoleOnRule")
    public ControllerResult<List<VUserRoleWithBizFields>> queryUserRoleOnRule(@RequestParam("ruleSqc") Long ruleSqc) {
        BEAssertUtils.notNull(ruleSqc, "ruleSqc");

        return ControllerResult.create(ruleService.queryUserRoleOnRule(ruleSqc),
                urList -> urList.stream()
                        .map(ur -> SeBeanUtil.copyProperties(ur, new VUserRoleWithBizFields()))
                        .collect(Collectors.toList()));
    }

}
