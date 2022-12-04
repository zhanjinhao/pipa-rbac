package cn.addenda.piparbac.controller;

import cn.addenda.businesseasy.util.BEAssertUtils;
import cn.addenda.piparbac.po.UserRole;
import cn.addenda.piparbac.po.UserRoleRecord;
import cn.addenda.piparbac.service.UserRoleRecordService;
import cn.addenda.piparbac.vo.VUserRoleRecord;
import cn.addenda.se.result.ControllerResult;
import cn.addenda.se.utils.SeBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author addenda
 * @datetime 2022/10/7 17:17
 */
@RestController
@RequestMapping("/userRoleRecord")
public class UserRoleRecordController {

    @Autowired
    private UserRoleRecordService userRoleRecordService;

    @PostMapping("/login")
    public ControllerResult<Long> login(@RequestBody VUserRoleRecord userRoleRecord) {
        BEAssertUtils.notNull(userRoleRecord);
        BEAssertUtils.notNull(userRoleRecord.getUserSqc(), "userSqc");
        BEAssertUtils.notNull(userRoleRecord.getRoleSqc(), "roleSqc");

        return ControllerResult.create(userRoleRecordService.login(SeBeanUtil.copyProperties(userRoleRecord, new UserRoleRecord())));
    }

    @DeleteMapping("/exit")
    public ControllerResult<Boolean> exit(@RequestParam("userSqc") Long userSqc) {
        BEAssertUtils.notNull(userSqc, "userSqc");

        return ControllerResult.create(userRoleRecordService.exit(userSqc));
    }

    @GetMapping("/queryLoginRole")
    public ControllerResult<UserRole> queryLoginRole(@RequestParam("userSqc") Long userSqc) {
        BEAssertUtils.notNull(userSqc, "userSqc");

        return ControllerResult.create(userRoleRecordService.queryLoginRole(userSqc));
    }

}
