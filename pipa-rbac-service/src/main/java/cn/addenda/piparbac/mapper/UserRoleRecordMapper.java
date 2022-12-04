package cn.addenda.piparbac.mapper;

import cn.addenda.me.fieldfilling.annotation.DQLFieldFilling;
import cn.addenda.piparbac.po.UserRole;
import cn.addenda.piparbac.po.UserRoleRecord;
import org.apache.ibatis.annotations.Param;

/**
 * @author addenda
 * @datetime 2022/2/7 15:56
 */
public interface UserRoleRecordMapper {

    int insert(UserRoleRecord userRoleRecord);

    UserRoleRecord queryUserRoleRecordByUserSqc(@Param("userSqc") Long userSqc);

    void deleteByUserSqc(@Param("userSqc") Long userSqc);

    @DQLFieldFilling(masterView = "t_user_role_record")
    UserRole queryLoginRole(@Param("userSqc") Long userSqc);

}
