package cn.addenda.piparbac.service;

import cn.addenda.me.lockedselect.LockedSelectHelper;
import cn.addenda.piparbac.manager.ModuleManager;
import cn.addenda.piparbac.manager.RoleModuleManager;
import cn.addenda.piparbac.po.Module;
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
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleManager moduleManager;

    @Autowired
    private RoleModuleManager roleModuleManager;

    @Override
    public ServiceResult<Long> rootSqc() {
        return ServiceResult.success(moduleManager.rootSqc());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    @ServiceResultConvertible
    public ServiceResult<Long> insert(Module module) {
        assertModule(module);
        if (Boolean.TRUE.equals(LockedSelectHelper.select(
                LockedSelectHelper.W_LOCK, () -> moduleManager.moduleCodeExists(module.getModuleCode())))) {
            throw new ServiceException("moduleCode已存在：" + module.getModuleCode() + "。 ");
        }
        // 特殊处理根目录
        if (moduleManager.rootParentSqc().equals(module.getParentSqc())) {
            if (Boolean.TRUE.equals(LockedSelectHelper.select(
                    LockedSelectHelper.W_LOCK, () -> moduleManager.sqcExists(0L)))) {
                throw new ServiceException("根目录已存在！");
            }
            module.setSqc(0L);
        }
        module.setStatus(StatusUtils.ACTIVE);
        moduleManager.insert(module);
        return ServiceResult.success(module.getSqc());
    }

    @Override
    public ServiceResult<PageInfo<Module>> pageQuery(Integer pageNum, Integer pageSize, Module module) {
        try {
            PageMethod.startPage(pageNum, pageSize);
            List<Module> query = moduleManager.queryByNonNullFields(module);
            return ServiceResult.success(new PageInfo<>(query));
        } finally {
            PageMethod.clearPage();
        }
    }

    @Override
    public ServiceResult<Module> queryBySqc(Long sqc) {
        return ServiceResult.success(moduleManager.queryBySqc(sqc));
    }

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> update(Module module) {
        assertModule(module);
        if (!moduleManager.sqcExists(module.getSqc())) {
            throw new ServiceException("sqc不存在：" + module.getSqc() + "。");
        }

        return TransactionUtils.doTransaction(() -> {
            moduleManager.update(module);
            return ServiceResult.success(true);
        });
    }

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> setStatus(Long sqc, String status) {
        StatusUtils.assertDAndAThrowSe(status);
        if (!moduleManager.sqcExists(sqc)) {
            throw new ServiceException("sqc不存在：" + sqc + "。 ");
        }

        return TransactionUtils.doTransaction(() -> {
            moduleManager.setStatus(sqc, status);
            return ServiceResult.success(true);
        });
    }

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> deleteBySqc(Long sqc) {
        // 如果module被角色关联，则不可删除
        if (roleModuleManager.moduleSqcExists(sqc)) {
            throw new ServiceException("此Module正被角色使用，不可删除！");
        }

        return TransactionUtils.doTransaction(() -> {
            moduleManager.deleteBySqc(sqc);
            return ServiceResult.success(true);
        });
    }

    @Override
    public ServiceResult<Module> queryByModuleCode(String moduleCode) {
        return ServiceResult.success(moduleManager.queryByModuleCode(moduleCode));
    }

    private void assertModule(Module module) {
        if (!Module.AT_READ.equals(module.getAccessType())
                && !Module.AT_WRITE.equals(module.getAccessType())) {
            throw new ServiceException("不合法的accessType：" + module.getAccessType() + "。");
        }
        if (!Module.ST_NAVIGATION.equals(module.getShowType())
                && !Module.ST_PAGE.equals(module.getShowType()) && !Module.ST_FUNCTION.equals(module.getShowType())) {
            throw new ServiceException("不合法的showType：" + module.getShowType() + "。");
        }
        if (!Module.RTT_CURRENT.equals(module.getResponseToType())
                && !Module.RTT_NEW.equals(module.getResponseToType()) && !Module.RTT_JUMP.equals(module.getResponseToType())) {
            throw new ServiceException("不合法的responseToType：" + module.getResponseToType() + "。");
        }
    }

}
