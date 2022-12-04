package cn.addenda.piparbac.mapper;

import cn.addenda.me.constant.Constants;
import cn.addenda.me.constraint.annotation.TableConstraint;
import cn.addenda.me.fieldfilling.annotation.DQLFieldFilling;
import cn.addenda.piparbac.po.Role;
import cn.addenda.piparbac.po.RoleModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/2/7 15:56
 */
public interface RoleModuleMapper {

    List<RoleModule> queryModuleOfRole(@Param("roleSqc") Long roleSqc);

    void deleteBySqc(@Param("sqc") Long sqc);

    void insert(RoleModule roleModule);

    @DQLFieldFilling(masterView = "role")
    @TableConstraint(tableSet = "t_role")
    List<Role> queryRoleOnModule(@Param("moduleSqc") Long moduleSqc);

    void deleteByRoleSqc(@Param("roleSqc") Long roleSqc);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer moduleSqcExists(@Param("moduleSqc") Long moduleSqc);

}
