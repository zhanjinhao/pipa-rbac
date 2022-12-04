package cn.addenda.piparbac.rpc;

import cn.addenda.piparbac.dto.DRole;

public interface RoleRpc {

    DRole queryByRoleCode(String roleCode);

}
