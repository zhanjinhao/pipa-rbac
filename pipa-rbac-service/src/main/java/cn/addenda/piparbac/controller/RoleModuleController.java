package cn.addenda.piparbac.controller;

import cn.addenda.businesseasy.util.BEAssertUtils;
import cn.addenda.piparbac.bo.BModuleTree;
import cn.addenda.piparbac.service.RoleModuleService;
import cn.addenda.piparbac.vo.VRoleWithAllFields;
import cn.addenda.se.result.ControllerResult;
import cn.addenda.se.utils.SeBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author addenda
 * @datetime 2022/2/7 16:43
 */
@RestController
@RequestMapping("/roleModule")
public class RoleModuleController {

    @Autowired
    private RoleModuleService roleModuleService;

    @PutMapping("/save")
    public ControllerResult<Boolean> save(@RequestParam("roleSqc") Long roleSqc, @RequestBody List<Long> moduleSqcList) {
        BEAssertUtils.notNull(roleSqc, "roleSqc");
        BEAssertUtils.notNull(moduleSqcList);

        return ControllerResult.create(roleModuleService.save(roleSqc, moduleSqcList));
    }

    @GetMapping("/queryModuleOfRole")
    public ControllerResult<BModuleTree> queryModuleOfRole(@RequestParam("roleSqc") Long roleSqc, @RequestParam("accessType") String accessType) {
        BEAssertUtils.notNull(roleSqc, "roleSqc");
        BEAssertUtils.notNull(accessType, "accessType");

        return ControllerResult.create(roleModuleService.queryModuleOfRole(roleSqc, accessType));
    }

    @GetMapping("/queryRoleOnModule")
    public ControllerResult<List<VRoleWithAllFields>> queryRoleOnModule(@RequestParam("moduleSqc") Long moduleSqc) {
        BEAssertUtils.notNull(moduleSqc, "moduleSqc");

        return ControllerResult.create(roleModuleService.queryRoleOnModule(moduleSqc),
                roleList -> roleList.stream()
                        .map(role -> SeBeanUtil.copyProperties(role, new VRoleWithAllFields()))
                        .collect(Collectors.toList()));
    }

}
