package cn.addenda.piparbac.mapper;

import cn.addenda.me.constant.Constants;
import cn.addenda.me.fieldfilling.annotation.DQLFieldFilling;
import cn.addenda.piparbac.po.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/2/7 15:56
 */
public interface RoleMapper {

    void insert(Role role);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer roleCodeExists(@Param("roleCode") String roleCode);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer sqcExists(@Param("sqc") Long sqc);

    void deleteBySqc(@Param("sqc") Long sqc);

    void updateNonNullFieldsBySqc(Role role);

    Role queryByRoleCode(@Param("roleCode") String roleCode);

    List<Role> queryByNonNullFields(Role role);

}
