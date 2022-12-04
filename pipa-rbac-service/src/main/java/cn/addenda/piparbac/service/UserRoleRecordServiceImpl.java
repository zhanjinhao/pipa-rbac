package cn.addenda.piparbac.service;

import cn.addenda.businesseasy.util.BEListUtils;
import cn.addenda.me.lockedselect.LockedSelectHelper;
import cn.addenda.piparbac.manager.RoleManager;
import cn.addenda.piparbac.manager.UserManager;
import cn.addenda.piparbac.manager.UserRoleManager;
import cn.addenda.piparbac.manager.UserRoleRecordManager;
import cn.addenda.piparbac.po.UserRole;
import cn.addenda.piparbac.po.UserRoleRecord;
import cn.addenda.se.result.ServiceException;
import cn.addenda.se.result.ServiceResult;
import cn.addenda.se.result.ServiceResultConvertible;
import cn.addenda.se.transaction.TransactionAttributeBuilder;
import cn.addenda.se.transaction.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAttribute;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author addenda
 * @datetime 2022/2/7 17:16
 */
@Component
public class UserRoleRecordServiceImpl implements UserRoleRecordService {

    @Autowired
    private UserRoleRecordManager userRoleRecordManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private UserRoleManager userRoleManager;

    @Override
    @ServiceResultConvertible
    public ServiceResult<Long> login(UserRoleRecord userRoleRecord) {
        Long userSqc = userRoleRecord.getUserSqc();
        Long roleSqc = userRoleRecord.getRoleSqc();

        if (!userManager.sqcExists(userSqc)) {
            throw new ServiceException("userSqc不存在：" + userSqc + "。");
        }
        if (!roleManager.sqcExists(roleSqc)) {
            throw new ServiceException("roleSqc不存在：" + roleSqc + "。");
        }

        List<UserRole> userRoleList = BEListUtils.merge(userRoleManager.queryWRoleOfUser(userSqc), userRoleManager.queryRRoleOfUser(userSqc));
        if (!userRoleList.stream().map(UserRole::getRoleSqc).collect(Collectors.toSet()).contains(roleSqc)) {
            throw new ServiceException("用户 [" + userSqc + "] 无角色：[" + roleSqc + "]或无读写权限。");
        }

        TransactionAttribute rrAttribute = TransactionAttributeBuilder.newRRBuilder().build();
        return TransactionUtils.doTransaction(rrAttribute, () -> {

            // 查询出来用户现有的角色
            UserRoleRecord userRoleRecordFromDb = LockedSelectHelper.select(
                    LockedSelectHelper.W_LOCK, () -> userRoleRecordManager.queryUserRoleRecordByUserSqc(userSqc));

            // 如果不存在，表示登录
            if (userRoleRecordFromDb == null) {
                userRoleRecord.setType(UserRoleRecord.TYPE_ENTER);
                userRoleRecordManager.insert(userRoleRecord);
            }
            // 如果存在，表示转换角色
            else {
                userRoleRecordManager.deleteByUserSqc(userSqc);
                userRoleRecord.setType(UserRoleRecord.TYPE_CHANGE_ROLE);
                userRoleRecordManager.insert(userRoleRecord);
            }

            return ServiceResult.success(userRoleRecord.getSqc());
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ServiceResultConvertible
    public ServiceResult<Boolean> exit(Long userSqc) {

        userRoleRecordManager.deleteByUserSqc(userSqc);
        return ServiceResult.success(true);
    }

    @Override
    public ServiceResult<UserRole> queryLoginRole(Long userSqc) {
        if (!userManager.sqcExists(userSqc)) {
            throw new ServiceException("userSqc不存在：" + userSqc + "。");
        }

        return ServiceResult.success(userRoleRecordManager.queryLoginRole(userSqc));
    }

}
