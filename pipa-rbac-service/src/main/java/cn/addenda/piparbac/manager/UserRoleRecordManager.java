package cn.addenda.piparbac.manager;

import cn.addenda.piparbac.po.UserRole;
import cn.addenda.piparbac.po.UserRoleRecord;

/**
 * @author addenda
 * @datetime 2022/10/10 15:25
 */
public interface UserRoleRecordManager {

    UserRoleRecord queryUserRoleRecordByUserSqc(Long userSqc);

    void insert(UserRoleRecord userRoleRecord);

    void deleteByUserSqc(Long userSqc);

    UserRole queryLoginRole(Long userSqc);

}
