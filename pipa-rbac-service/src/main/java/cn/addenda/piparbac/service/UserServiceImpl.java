package cn.addenda.piparbac.service;

import cn.addenda.me.lockedselect.LockedSelectHelper;
import cn.addenda.piparbac.manager.UserManager;
import cn.addenda.piparbac.manager.UserRoleManager;
import cn.addenda.piparbac.po.User;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserRoleManager userRoleManager;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    @ServiceResultConvertible
    public ServiceResult<Long> insert(User user) {
        if (Boolean.TRUE.equals(LockedSelectHelper.select(
                LockedSelectHelper.W_LOCK, () -> userManager.userIdExists(user.getUserId())))) {
            throw new ServiceException("用户ID已存在：" + user.getUserId() + "。");
        }
        if (Boolean.TRUE.equals(LockedSelectHelper.select(
                LockedSelectHelper.W_LOCK, () -> userManager.userEmailExists(user.getUserEmail())))) {
            throw new ServiceException("邮箱已存在：" + user.getUserEmail() + "。");
        }
        user.setStatus(StatusUtils.ON_JOB);
        userManager.insert(user);
        return ServiceResult.success(user.getSqc());
    }

    @Override
    public ServiceResult<PageInfo<User>> pageQuery(Integer pageNum, Integer pageSize, User user) {
        try {
            PageMethod.startPage(pageNum, pageSize);
            List<User> query = userManager.queryByNonNullFields(user);
            return ServiceResult.success(new PageInfo<>(query));
        } finally {
            PageMethod.clearPage();
        }
    }

    @Override
    public ServiceResult<User> queryBySqc(Long sqc) {
        return ServiceResult.success(userManager.queryBySqc(sqc));
    }

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> update(User user) {
        if (!userManager.sqcExists(user.getSqc())) {
            throw new ServiceException("sqc不存在：" + user.getSqc() + "。");
        }
        return TransactionUtils.doTransaction(() -> {
            userManager.update(user);
            return ServiceResult.success(true);
        });
    }

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> setStatus(Long sqc, String status) {
        if (!StatusUtils.ON_JOB.equals(status) &&
                !StatusUtils.RETIRE.equals(status) && !StatusUtils.LEAVE.equals(status)) {
            throw new ServiceException("不合法的状态：" + status + "。");
        }
        if (!userManager.sqcExists(sqc)) {
            throw new ServiceException("sqc不存在：" + sqc + "。");
        }
        return TransactionUtils.doTransaction(() -> {
            userManager.setStatus(sqc, status);
            return ServiceResult.success(true);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ServiceResultConvertible
    public ServiceResult<Boolean> deleteBySqc(Long sqc) {
        userManager.deleteBySqc(sqc);
        // 删除用户的时候同步删除：用户-角色的关联
        userRoleManager.deleteByUserSqc(sqc);
        return ServiceResult.success(true);
    }

    @Override
    public ServiceResult<User> queryByUserId(String userId) {
        return ServiceResult.success(userManager.queryByUserId(userId));
    }

}
