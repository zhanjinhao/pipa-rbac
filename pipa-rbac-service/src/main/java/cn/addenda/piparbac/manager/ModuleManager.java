package cn.addenda.piparbac.manager;

import cn.addenda.piparbac.po.Module;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/10/10 11:27
 */
public interface ModuleManager {

    Long rootSqc();

    Long rootParentSqc();

    boolean moduleCodeExists(String moduleCode);

    void insert(Module module);

    boolean sqcExists(Long sqc);

    void deleteBySqc(Long sqc);

    void setStatus(Long sqc, String status);

    Module queryByModuleCode(String moduleCode);

    List<Module> queryByNonNullFields(Module module);

    Module queryBySqc(Long sqc);

    void update(Module module);

}
