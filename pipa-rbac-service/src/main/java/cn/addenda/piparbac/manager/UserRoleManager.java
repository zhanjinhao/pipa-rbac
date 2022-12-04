package cn.addenda.piparbac.manager;

import cn.addenda.piparbac.po.User;
import cn.addenda.piparbac.po.UserRole;

import java.util.List;

public interface UserRoleManager {

    void deleteByUserSqc(Long userSqc);

    void batchDeleteBySqc(List<Long> deleteList);

    void batchInsert(List<UserRole> insertList);

    boolean sqcExists(Long sqc);

    void setPermission(Long sqc, UserRole userRole);

    List<UserRole> queryRoleOfUser(Long userSqc);

    List<UserRole> queryWRoleOfUser(Long userSqc);

    List<UserRole> queryRRoleOfUser(Long userSqc);

    List<User> queryUserOnRole(Long roleSqc);

    List<UserRole> queryUserRoleOnRule(Long ruleSqc);

    boolean roleSqcExists(Long roleSqc);

    boolean ruleSqcExists(Long ruleSqc);

}
