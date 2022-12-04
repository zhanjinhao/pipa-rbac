package cn.addenda.piparbac.mapper;

import cn.addenda.me.constant.Constants;
import cn.addenda.me.fieldfilling.annotation.DQLFieldFilling;
import cn.addenda.piparbac.po.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/2/7 15:56
 */
public interface ModuleMapper {

    void insert(Module module);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer moduleCodeExists(@Param("moduleCode") String moduleCode);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer sqcExists(@Param("sqc") Long sqc);

    void deleteBySqc(@Param("sqc") Long sqc);

    void updateNonNullFieldsBySqc(Module module);

    Module queryByModuleCode(@Param("moduleCode") String moduleCode);

    List<Module> queryByNonNullFields(Module module);

}
