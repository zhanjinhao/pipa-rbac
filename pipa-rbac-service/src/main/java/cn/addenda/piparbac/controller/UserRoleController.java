package cn.addenda.piparbac.controller;

import cn.addenda.businesseasy.util.BEAssertUtils;
import cn.addenda.piparbac.po.UserRole;
import cn.addenda.piparbac.service.UserRoleService;
import cn.addenda.piparbac.vo.VUserRolePermission;
import cn.addenda.piparbac.vo.VUserWithAllFields;
import cn.addenda.se.result.ControllerResult;
import cn.addenda.se.utils.SeBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author addenda
 * @datetime 2022/2/8 12:56
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PutMapping("/save")
    public ControllerResult<Boolean> save(@RequestParam("userSqc") Long userSqc, @RequestBody List<Long> roleSqcList) {
        BEAssertUtils.notNull(userSqc, "userSqc");
        BEAssertUtils.notNull(roleSqcList);

        return ControllerResult.create(userRoleService.save(userSqc, roleSqcList));
    }

    @PutMapping("/setPermission")
    public ControllerResult<Boolean> setPermission(@RequestParam("sqc") Long sqc, @RequestBody VUserRolePermission rolePermission) {
        BEAssertUtils.notNull(sqc, "sqc");
        BEAssertUtils.notNull(rolePermission);
        BEAssertUtils.notNull(rolePermission.getAccessType(), "accessType");

        return ControllerResult.create(userRoleService.setPermission(sqc, SeBeanUtil.copyProperties(rolePermission, new UserRole())));
    }

    @GetMapping("/queryRoleOfUser")
    public ControllerResult<List<UserRole>> queryRoleOfUser(@RequestParam("userSqc") Long userSqc) {
        BEAssertUtils.notNull(userSqc, "userSqc");

        return ControllerResult.create(userRoleService.queryRoleOfUser(userSqc));
    }

    @GetMapping("/queryUserOnRole")
    public ControllerResult<List<VUserWithAllFields>> queryUserOnRole(@RequestParam("roleSqc") Long roleSqc) {
        BEAssertUtils.notNull(roleSqc, "roleSqc");

        return ControllerResult.create(userRoleService.queryUserOnRole(roleSqc),
                userList -> userList.stream()
                        .map(user -> SeBeanUtil.copyProperties(user, new VUserWithAllFields()))
                        .collect(Collectors.toList()));
    }

}
