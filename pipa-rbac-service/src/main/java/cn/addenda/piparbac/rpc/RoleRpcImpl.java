package cn.addenda.piparbac.rpc;

import cn.addenda.piparbac.dto.DRole;
import cn.addenda.piparbac.po.Role;
import cn.addenda.piparbac.service.RoleService;
import cn.addenda.se.result.ServiceResult;
import cn.addenda.se.utils.SeBeanUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author addenda
 * @datetime 2022/10/15 18:01
 */
@DubboService(protocol = "dubbo")
public class RoleRpcImpl implements RoleRpc {

    @Autowired
    private RoleService roleService;

    @Override
    public DRole queryByRoleCode(String roleCode) {
        ServiceResult<Role> roleServiceResult = roleService.queryByRoleCode(roleCode);
        return SeBeanUtil.copyProperties(roleServiceResult.getResult(), new DRole());
    }

}
