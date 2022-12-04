package cn.addenda.piparbac.rpc;

import cn.addenda.piparbac.dto.DUser;
import cn.addenda.piparbac.po.User;
import cn.addenda.piparbac.service.UserService;
import cn.addenda.se.result.ServiceResult;
import cn.addenda.se.utils.SeBeanUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author addenda
 * @datetime 2022/10/15 10:21
 */
@DubboService(protocol = "dubbo")
public class UserRpcImpl implements UserRpc {

    @Autowired
    private UserService userService;

    @Override
    public DUser queryByUserId(String userId) {
        ServiceResult<User> userServiceResult = userService.queryByUserId(userId);
        return SeBeanUtil.copyProperties(userServiceResult.getResult(), new DUser());
    }

}
