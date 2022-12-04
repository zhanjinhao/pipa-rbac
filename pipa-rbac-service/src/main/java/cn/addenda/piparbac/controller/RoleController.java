package cn.addenda.piparbac.controller;

import cn.addenda.businesseasy.util.BEAssertUtils;
import cn.addenda.piparbac.po.Role;
import cn.addenda.piparbac.service.RoleService;
import cn.addenda.piparbac.vo.VRole;
import cn.addenda.se.result.ControllerResult;
import cn.addenda.se.utils.SeBeanUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author addenda
 * @datetime 2022/2/7 16:43
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/insert")
    public ControllerResult<Long> insert(@RequestBody VRole role) {
        BEAssertUtils.notNull(role);
        BEAssertUtils.notNull(role.getRoleCode(), "roleCode");
        BEAssertUtils.notNull(role.getRoleName(), "roleName");

        return ControllerResult.create(roleService.insert(SeBeanUtil.copyProperties(role, new Role())));
    }

    @GetMapping("/pageQuery")
    public ControllerResult<PageInfo<Role>> pageQuery(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody VRole role) {
        BEAssertUtils.notNull(pageNum, "pageNum");
        BEAssertUtils.notNull(pageSize, "pageSize");
        BEAssertUtils.notNull(role);

        return ControllerResult.create(roleService.pageQuery(pageNum, pageSize, SeBeanUtil.copyProperties(role, new Role())));
    }

    @GetMapping("/queryBySqc")
    public ControllerResult<Role> queryBySqc(@RequestParam("sqc") Long sqc) {
        BEAssertUtils.notNull(sqc, "sqc");

        return ControllerResult.create(roleService.queryBySqc(sqc));
    }

    @PutMapping("/update")
    public ControllerResult<Boolean> update(@RequestParam("sqc") Long sqc, @RequestBody VRole role) {
        BEAssertUtils.notNull(sqc, "sqc");
        BEAssertUtils.notNull(role);
        BEAssertUtils.notNull(role.getRoleName(), "roleName");

        BEAssertUtils.notModified(role.getRoleCode(), "roleCode");
        return ControllerResult.create(roleService.update(SeBeanUtil.copyProperties(role, new Role(sqc))));
    }

    @PutMapping("/setStatus")
    public ControllerResult<Boolean> setStatus(@RequestParam("sqc") Long sqc, @RequestBody String status) {
        BEAssertUtils.notNull(sqc, "sqc");
        BEAssertUtils.notNull(status);

        return ControllerResult.create(roleService.setStatus(sqc, status));
    }

    @DeleteMapping("/deleteBySqc")
    public ControllerResult<Boolean> deleteBySqc(@RequestBody Long sqc) {
        BEAssertUtils.notNull(sqc);

        return ControllerResult.create(roleService.deleteBySqc(sqc));
    }

}
