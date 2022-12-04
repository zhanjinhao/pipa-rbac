package cn.addenda.piparbac.service;

import cn.addenda.me.lockedselect.LockedSelectHelper;
import cn.addenda.piparbac.manager.RoleManager;
import cn.addenda.piparbac.manager.RoleModuleManager;
import cn.addenda.piparbac.manager.UserRoleManager;
import cn.addenda.piparbac.po.Role;
import cn.addenda.piparbac.utils.StatusUtils;
import cn.addenda.se.result.ServiceException;
import cn.addenda.se.result.ServiceResult;
import cn.addenda.se.result.ServiceResultConvertible;
import cn.addenda.se.transaction.TransactionUtils;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/2/7 17:16
 */
@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private UserRoleManager userRoleManager;

    @Autowired
    private RoleModuleManager roleModuleManager;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    @ServiceResultConvertible
    public ServiceResult<Long> insert(Role role) {
        if (Boolean.TRUE.equals(LockedSelectHelper.select(
                LockedSelectHelper.W_LOCK, () -> roleManager.roleCodeExists(role.getRoleCode())))) {
            throw new ServiceException("roleCode已存在：" + role.getRoleCode() + "。 ");
        }

        role.setStatus(StatusUtils.ACTIVE);
        roleManager.insert(role);
        return ServiceResult.success(role.getSqc());
    }

    @Override
    public ServiceResult<PageInfo<Role>> pageQuery(Integer pageNum, Integer pageSize, Role role) {
        try {
            PageMethod.startPage(pageNum, pageSize);
            List<Role> query = roleManager.queryByNonNullFields(role);
            return ServiceResult.success(new PageInfo<>(query));
        } finally {
            PageMethod.clearPage();
        }
    }

    @Override
    public ServiceResult<Role> queryBySqc(Long sqc) {
        return ServiceResult.success(roleManager.queryBySqc(sqc));
    }

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> update(Role role) {
        if (!roleManager.sqcExists(role.getSqc())) {
            throw new ServiceException("sqc不存在：" + role.getSqc() + "。 ");
        }

        return TransactionUtils.doTransaction(() -> {
            roleManager.update(role);
            return ServiceResult.success(true);
        });
    }

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> setStatus(Long sqc, String status) {
        StatusUtils.assertDAndAThrowSe(status);
        if (!roleManager.sqcExists(sqc)) {
            throw new ServiceException("sqc不存在：" + sqc + "。 ");
        }

        return TransactionUtils.doTransaction(() -> {
            roleManager.setStatus(sqc, status);
            return ServiceResult.success(true);
        });
    }

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> deleteBySqc(Long sqc) {
        // 如果角色被用户关联，则不可删除
        if (userRoleManager.roleSqcExists(sqc)) {
            throw new ServiceException("此角色正被用户使用，不可删除！");
        }

        return TransactionUtils.doTransaction(() -> {
            roleManager.deleteBySqc(sqc);
            // 删除角色的时候同步删除：角色-module的关联
            roleModuleManager.deleteByRoleSqc(sqc);
            return ServiceResult.success(true);
        });
    }

    @Override
    public ServiceResult<Role> queryByRoleCode(String roleCode) {
        return ServiceResult.success(roleManager.queryByRoleCode(roleCode));
    }

}
