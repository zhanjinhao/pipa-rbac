package cn.addenda.piparbac.service;

import cn.addenda.businesseasy.asynctask.TernaryResult;
import cn.addenda.businesseasy.util.BEListUtils;
import cn.addenda.me.lockedselect.LockedSelectHelper;
import cn.addenda.piparbac.bo.BModuleTree;
import cn.addenda.piparbac.manager.ModuleManager;
import cn.addenda.piparbac.manager.RoleManager;
import cn.addenda.piparbac.manager.RoleModuleManager;
import cn.addenda.piparbac.po.Module;
import cn.addenda.piparbac.po.Role;
import cn.addenda.piparbac.po.RoleModule;
import cn.addenda.se.result.ServiceException;
import cn.addenda.se.result.ServiceResult;
import cn.addenda.se.result.ServiceResultConvertible;
import cn.addenda.se.transaction.TransactionAttributeBuilder;
import cn.addenda.se.transaction.TransactionUtils;
import cn.addenda.se.utils.SeBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAttribute;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author addenda
 * @datetime 2022/2/7 17:16
 */
@Component
public class RoleModuleServiceImpl implements RoleModuleService {

    @Autowired
    private RoleModuleManager roleModuleManager;

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private ModuleManager moduleManager;

    @Override
    @ServiceResultConvertible
    public ServiceResult<Boolean> save(Long roleSqc, List<Long> moduleSqcList) {
        if (!roleManager.sqcExists(roleSqc)) {
            throw new ServiceException("roleSqc不存在：" + roleSqc + "。");
        }

        TransactionAttribute rrAttribute = TransactionAttributeBuilder.newRRBuilder().build();
        return TransactionUtils.doTransaction(rrAttribute, () -> {
            // 从数据库查出来角色已经有的模块
            List<RoleModule> roleModuleListFromDb = LockedSelectHelper.select(
                    LockedSelectHelper.W_LOCK, () -> roleModuleManager.queryModuleOfRole(roleSqc));

            List<Long> moduleSqcListFromDb = roleModuleListFromDb.stream().map(RoleModule::getModuleSqc).collect(Collectors.toList());
            TernaryResult<List<Long>, List<Long>, List<Long>> separate = BEListUtils.separate(moduleSqcListFromDb, moduleSqcList);

            // 数据库有&参数没有，需要删除
            List<Long> deleteList = new ArrayList<>();
            for (Long moduleSqc : separate.getFirstResult()) {
                Map<Long, Long> roleModuleMapFromDb = roleModuleListFromDb.stream()
                        .collect(Collectors.toMap(RoleModule::getModuleSqc, RoleModule::getSqc));
                deleteList.add(roleModuleMapFromDb.get(moduleSqc));
            }

            // 参数有&数据库没有，需要增加
            List<RoleModule> insertList = separate.getThirdResult().stream()
                    .map(item -> new RoleModule(roleSqc, item)).collect(Collectors.toList());

            roleModuleManager.batchDeleteBySqc(deleteList);
            roleModuleManager.batchInsert(insertList);

            return ServiceResult.success(true);
        });
    }

    @Override
    public ServiceResult<BModuleTree> queryModuleOfRole(Long roleSqc, String accessType) {
        if (!roleManager.sqcExists(roleSqc)) {
            throw new ServiceException("roleSqc不存在：" + roleSqc + "。");
        }
        List<RoleModule> roleModuleList = roleModuleManager.queryModuleOfRole(roleSqc);

        // 查询出来这个角色下的目录
        List<Module> moduleList = roleModuleList.stream().map(
                item -> moduleManager.queryBySqc(item.getModuleSqc())).collect(Collectors.toList());

        if (Module.AT_LISTEN.equals(accessType)) {
            return ServiceResult.success(getRoot());
        } else if (Module.AT_WRITE.equals(accessType)) {
            moduleList.removeIf(item -> Module.AT_READ.equals(item.getAccessType()));
        } else if (Module.AT_READ.equals(accessType)) {
            // no-op
        } else {
            throw new ServiceException("不合法的accessType！");
        }

        // 按照 <parent, List<child>> 分组
        Map<Long, List<Long>> parentSqcModuleMap = new HashMap<>();
        for (Module module : moduleList) {
            List<Long> tree = parentSqcModuleMap.computeIfAbsent(module.getParentSqc(), s -> new ArrayList<>());
            tree.add(module.getSqc());
        }

        Map<Long, Module> moduleMap = moduleList.stream().collect(Collectors.toMap(Module::getSqc, a -> a));
        BModuleTree root = getRoot();
        Queue<BModuleTree> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BModuleTree parent = queue.poll();
            Long sqc = parent.getSqc();
            List<Long> childrenSqc = parentSqcModuleMap.get(sqc);

            if (childrenSqc != null && !childrenSqc.isEmpty()) {
                List<BModuleTree> bModuleTreeList = parent.getBModuleTreeList();
                for (Long childSqc : childrenSqc) {
                    BModuleTree child = SeBeanUtil.copyProperties(moduleMap.get(childSqc), new BModuleTree());
                    bModuleTreeList.add(child);
                    queue.offer(child);
                }
            }
        }

        return ServiceResult.success(root);
    }

    private BModuleTree getRoot() {
        Long rootSqc = moduleManager.rootSqc();
        Module module = moduleManager.queryBySqc(rootSqc);
        return SeBeanUtil.copyProperties(module, new BModuleTree());
    }

    @Override
    public ServiceResult<List<Role>> queryRoleOnModule(Long moduleSqc) {
        if (!moduleManager.sqcExists(moduleSqc)) {
            throw new ServiceException("moduleSqc不存在：" + moduleSqc + "。");
        }

        return ServiceResult.success(roleModuleManager.queryRoleOnModule(moduleSqc));
    }

}
