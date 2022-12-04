package cn.addenda.piparbac.mapper;

import cn.addenda.me.constant.Constants;
import cn.addenda.me.fieldfilling.annotation.DQLFieldFilling;
import cn.addenda.piparbac.po.User;
import cn.addenda.piparbac.po.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/2/7 15:57
 */
public interface UserRoleMapper {

    void insert(UserRole userRole);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer sqcExists(@Param("sqc") Long sqc);

    void updateNonNullFieldsBySqc(UserRole userRole);

    List<UserRole> queryRoleOfUser(@Param("userSqc") Long userSqc, @Param("accessType") String accessType);

    void deleteBySqc(@Param("sqc") Long sqc);

    @DQLFieldFilling(masterView = "user")
    List<User> queryUserOnRole(@Param("roleSqc") Long roleSqc);

    List<UserRole> queryByNonNullFields(UserRole userRole);

    void deleteByUserSqc(@Param("userSqc") Long userSqc);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer roleSqcExists(@Param("roleSqc") Long roleSqc);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer ruleSqcExists(@Param("ruleSqc") Long ruleSqc);

}
