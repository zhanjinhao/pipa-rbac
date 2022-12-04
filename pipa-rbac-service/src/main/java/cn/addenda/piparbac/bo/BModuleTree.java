package cn.addenda.piparbac.bo;

import cn.addenda.piparbac.po.Module;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author addenda
 * @datetime 2022/2/7 15:54
 */
@Setter
@Getter
@ToString(callSuper = true)
public class BModuleTree extends Module {

    private List<BModuleTree> bModuleTreeList = new ArrayList<>();

}
