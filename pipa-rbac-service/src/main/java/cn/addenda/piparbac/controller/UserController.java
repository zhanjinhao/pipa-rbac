package cn.addenda.piparbac.controller;

import cn.addenda.businesseasy.util.BEAssertUtils;
import cn.addenda.piparbac.po.User;
import cn.addenda.piparbac.service.UserService;
import cn.addenda.piparbac.vo.VUser;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/insert")
    public ControllerResult<Long> insert(@RequestBody VUser user) {
        BEAssertUtils.notNull(user);
        BEAssertUtils.notNull(user.getUserId(), "userId");
        BEAssertUtils.notNull(user.getUserEmail(), "userEmail");
        BEAssertUtils.notNull(user.getUserName(), "userName");

        return ControllerResult.create(userService.insert(SeBeanUtil.copyProperties(user, new User())));
    }

    @GetMapping("/pageQuery")
    public ControllerResult<PageInfo<User>> pageQuery(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody VUser user) {
        BEAssertUtils.notNull(pageNum, "pageNum");
        BEAssertUtils.notNull(pageSize, "pageSize");
        BEAssertUtils.notNull(user);

        return ControllerResult.create(userService.pageQuery(pageNum, pageSize, SeBeanUtil.copyProperties(user, new User())));
    }

    @GetMapping("/queryBySqc")
    public ControllerResult<User> queryBySqc(@RequestParam("sqc") Long sqc) {
        BEAssertUtils.notNull(sqc, "sqc");

        return ControllerResult.create(userService.queryBySqc(sqc));
    }

    @PutMapping("/update")
    public ControllerResult<Boolean> update(@RequestParam("sqc") Long sqc, @RequestBody VUser user) {
        BEAssertUtils.notNull(sqc, "sqc");
        BEAssertUtils.notNull(user);
        BEAssertUtils.notNull(user.getUserName(), "userName");
        BEAssertUtils.notModified(user.getUserEmail(), "userEmail");
        BEAssertUtils.notModified(user.getUserId(), "userId");

        return ControllerResult.create(userService.update(SeBeanUtil.copyProperties(user, new User(sqc))));
    }

    @PutMapping("/setStatus")
    public ControllerResult<Boolean> setStatus(@RequestParam("sqc") Long sqc, @RequestBody String status) {
        BEAssertUtils.notNull(sqc, "sqc");
        BEAssertUtils.notNull(status);

        return ControllerResult.create(userService.setStatus(sqc, status));
    }

    @DeleteMapping("/deleteBySqc")
    public ControllerResult<Boolean> deleteBySqc(@RequestBody Long sqc) {
        BEAssertUtils.notNull(sqc);

        return ControllerResult.create(userService.deleteBySqc(sqc));
    }

}
