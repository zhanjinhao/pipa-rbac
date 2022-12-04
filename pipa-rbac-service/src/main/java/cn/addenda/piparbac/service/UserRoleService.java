package cn.addenda.piparbac.service;

import cn.addenda.piparbac.po.User;
import cn.addenda.piparbac.po.UserRole;
import cn.addenda.se.result.ServiceResult;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/2/7 17:16
 */
public interface UserRoleService {

    ServiceResult<Boolean> save(Long userSqc, List<Long> roleSqcList);

    ServiceResult<Boolean> setPermission(Long sqc, UserRole userRole);

    ServiceResult<List<UserRole>> queryRoleOfUser(Long userSqc);

    ServiceResult<List<User>> queryUserOnRole(Long roleSqc);

}
