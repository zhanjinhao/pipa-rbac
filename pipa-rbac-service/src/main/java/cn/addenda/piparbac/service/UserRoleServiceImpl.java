package cn.addenda.piparbac.service;

import cn.addenda.businesseasy.asynctask.TernaryResult;
import cn.addenda.businesseasy.util.BEListUtils;
import cn.addenda.me.lockedselect.LockedSelectHelper;
import cn.addenda.piparbac.manager.RoleManager;
import cn.addenda.piparbac.manager.RuleManager;
import cn.addenda.piparbac.manager.UserManager;
import cn.addenda.piparbac.manager.UserRoleManager;
import cn.addenda.piparbac.po.Module;
import cn.addenda.piparbac.po.User;
import cn.addenda.piparbac.po.UserRole;
import cn.addenda.se.result.ServiceException;
import cn.addenda.se.result.ServiceResult;
import cn.addenda.se.result.ServiceResultConvertible;
import cn.addenda.se.transaction.TransactionAttributeBuilder;
import cn.addenda.se.transaction.TransactionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author addenda
 * @datetime 2022/2/7 17:16
 */
@Slf4j
@Component
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleManager userRoleManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private RuleManager ruleManager;

    @Autowired
    private RoleManager roleManager;

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> save(Long userSqc, List<Long> roleSqcList) {
        if (!userManager.sqcExists(userSqc)) {
            throw new ServiceException("userSqc不存在：" + userSqc + "。");
        }

        TransactionAttribute rrAttribute = TransactionAttributeBuilder.newRRBuilder().build();
        return TransactionUtils.doTransaction(rrAttribute, () -> {
            // 从数据库查出来用户已经有的角色
            List<UserRole> userRoleListFromDb = LockedSelectHelper.select(
                    LockedSelectHelper.W_LOCK, () -> userRoleManager.queryRoleOfUser(userSqc));

            List<Long> roleSqcListFromDb = userRoleListFromDb.stream().map(UserRole::getRoleSqc).collect(Collectors.toList());
            TernaryResult<List<Long>, List<Long>, List<Long>> separate = BEListUtils.separate(roleSqcListFromDb, roleSqcList);

            // 数据库有参数没有，需要删除
            List<Long> deleteList = new ArrayList<>();
            for (Long roleSqc : separate.getFirstResult()) {
                Map<Long, Long> userRoleMapFromDb = userRoleListFromDb.stream()
                        .collect(Collectors.toMap(UserRole::getRoleSqc, UserRole::getSqc));
                deleteList.add(userRoleMapFromDb.get(roleSqc));
            }

            // 参数有数据库没有，需要增加
            List<UserRole> insertList = separate.getThirdResult().stream()
                    .map(item -> new UserRole(userSqc, item, Module.AT_WRITE, ruleManager.defaultRuleSqcList())).collect(Collectors.toList());

            userRoleManager.batchDeleteBySqc(deleteList);
            userRoleManager.batchInsert(insertList);

            return ServiceResult.success(true);
        });
    }

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> setPermission(Long sqc, UserRole userRole) {
        String accessType = userRole.getAccessType();
        if (!Module.AT_WRITE.equals(accessType) &&
                !Module.AT_READ.equals(accessType) && !Module.AT_LISTEN.equals(accessType)) {
            throw new ServiceException("不合法的进入权限：" + accessType + "。");
        }

        if (!userRoleManager.sqcExists(sqc)) {
            throw new ServiceException("sqc不存在：" + sqc + "。");
        }

        return TransactionUtils.doTransaction(() -> {
            userRoleManager.setPermission(sqc, userRole);
            return ServiceResult.success(true);
        });

    }

    @Override
    public ServiceResult<List<UserRole>> queryRoleOfUser(Long userSqc) {
        if (!userManager.sqcExists(userSqc)) {
            throw new ServiceException("用户ID不存在：" + userSqc + "。");
        }

        return ServiceResult.success(userRoleManager.queryRoleOfUser(userSqc));
    }

    @Override
    public ServiceResult<List<User>> queryUserOnRole(Long roleSqc) {
        if (!roleManager.sqcExists(roleSqc)) {
            throw new ServiceException("roleSqc不存在：" + roleSqc + "。");
        }

        return ServiceResult.success(userRoleManager.queryUserOnRole(roleSqc));
    }

}
