package cn.addenda.piparbac.service;

import cn.addenda.piparbac.bo.BModuleTree;
import cn.addenda.piparbac.po.Role;
import cn.addenda.se.result.ServiceResult;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/2/7 17:16
 */
public interface RoleModuleService {

    ServiceResult<Boolean> save(Long roleSqc, List<Long> moduleSqcList);

    ServiceResult<BModuleTree> queryModuleOfRole(Long roleSqc, String accessType);

    ServiceResult<List<Role>> queryRoleOnModule(Long moduleSqc);

}
