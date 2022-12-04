package cn.addenda.piparbac.service;

import cn.addenda.piparbac.po.UserRole;
import cn.addenda.piparbac.po.UserRoleRecord;
import cn.addenda.se.result.ServiceResult;

/**
 * @author addenda
 * @datetime 2022/2/7 17:16
 */
public interface UserRoleRecordService {

    ServiceResult<Long> login(UserRoleRecord userRoleRecord);

    ServiceResult<Boolean> exit(Long userSqc);

    ServiceResult<UserRole> queryLoginRole(Long userSqc);

}
