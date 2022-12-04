package cn.addenda.piparbac.manager;

import cn.addenda.piparbac.po.Role;
import cn.addenda.piparbac.po.RoleModule;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/10/15 15:14
 */
public interface RoleModuleManager {

    List<RoleModule> queryModuleOfRole(Long roleSqc);

    void batchDeleteBySqc(List<Long> deleteList);

    void batchInsert(List<RoleModule> insertList);

    List<Role> queryRoleOnModule(Long moduleSqc);

    void deleteByRoleSqc(Long sqc);

    boolean moduleSqcExists(Long moduleSqc);

}
