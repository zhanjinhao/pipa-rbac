package cn.addenda.piparbac.service;

import cn.addenda.piparbac.po.Module;
import cn.addenda.se.result.ServiceResult;
import com.github.pagehelper.PageInfo;

/**
 * @author addenda
 * @datetime 2022/2/7 17:16
 */
public interface ModuleService {

    ServiceResult<Long> rootSqc();

    ServiceResult<Long> insert(Module module);

    ServiceResult<PageInfo<Module>> pageQuery(Integer pageNum, Integer pageSize, Module module);

    ServiceResult<Module> queryBySqc(Long sqc);

    ServiceResult<Boolean> update(Module module);

    ServiceResult<Boolean> setStatus(Long sqc, String status);

    ServiceResult<Boolean> deleteBySqc(Long sqc);

    ServiceResult<Module> queryByModuleCode(String moduleCode);

}
