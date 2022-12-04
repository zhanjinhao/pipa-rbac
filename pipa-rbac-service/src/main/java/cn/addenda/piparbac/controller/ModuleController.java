package cn.addenda.piparbac.controller;

import cn.addenda.businesseasy.util.BEAssertUtils;
import cn.addenda.piparbac.po.Module;
import cn.addenda.piparbac.service.ModuleService;
import cn.addenda.piparbac.vo.VModule;
import cn.addenda.se.result.ControllerResult;
import cn.addenda.se.utils.SeBeanUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author addenda
 * @datetime 2022/2/7 16:43
 */
@RestController
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/rootSqc")
    public ControllerResult<Long> rootSqc() {
        return ControllerResult.create(moduleService.rootSqc());
    }

    @PostMapping("/insert")
    public ControllerResult<Long> insert(@RequestBody VModule module) {
        BEAssertUtils.notNull(module);
        BEAssertUtils.notNull(module.getModuleCode(), "moduleCode");
        BEAssertUtils.notNull(module.getModuleName(), "moduleName");
        BEAssertUtils.notNull(module.getParentSqc(), "parentSqc");
        BEAssertUtils.notNull(module.getAction(), "action");
        BEAssertUtils.notNull(module.getShowType(), "showType");
        BEAssertUtils.notNull(module.getResponseToType(), "responseToType");

        return ControllerResult.create(moduleService.insert(SeBeanUtil.copyProperties(module, new Module())));
    }

    @GetMapping("/pageQuery")
    public ControllerResult<PageInfo<Module>> pageQuery(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody VModule module) {
        BEAssertUtils.notNull(pageNum, "pageNum");
        BEAssertUtils.notNull(pageSize, "pageSize");
        BEAssertUtils.notNull(module);

        return ControllerResult.create(moduleService.pageQuery(pageNum, pageSize, SeBeanUtil.copyProperties(module, new Module())));
    }

    @GetMapping("/queryBySqc")
    public ControllerResult<Module> queryBySqc(@RequestParam("sqc") Long sqc) {
        BEAssertUtils.notNull(sqc, "sqc");

        return ControllerResult.create(moduleService.queryBySqc(sqc));
    }

    @PutMapping("/setStatus")
    public ControllerResult<Boolean> setStatus(@RequestParam("sqc") Long sqc, @RequestBody String status) {
        BEAssertUtils.notNull(sqc, "sqc");
        BEAssertUtils.notNull(status);

        return ControllerResult.create(moduleService.setStatus(sqc, status));
    }

    @PutMapping("/update")
    public ControllerResult<Boolean> update(@RequestParam("sqc") Long sqc, @RequestBody VModule module) {
        BEAssertUtils.notNull(sqc, "sqc");
        BEAssertUtils.notNull(module);
        BEAssertUtils.notNull(module.getModuleName(), "moduleName");
        BEAssertUtils.notNull(module.getAction(), "action");
        BEAssertUtils.notNull(module.getAccessType(), "accessType");
        BEAssertUtils.notNull(module.getShowType(), "showType");
        BEAssertUtils.notNull(module.getResponseToType(), "responseToType");

        BEAssertUtils.notModified(module.getModuleCode(), "moduleCode");
        BEAssertUtils.notModified(module.getParentSqc(), "parentSqc");

        return ControllerResult.create(moduleService.update(SeBeanUtil.copyProperties(module, new Module(sqc))));
    }

    @DeleteMapping("/deleteBySqc")
    public ControllerResult<Boolean> deleteBySqc(@RequestBody Long sqc) {
        BEAssertUtils.notNull(sqc);

        return ControllerResult.create(moduleService.deleteBySqc(sqc));
    }

}
