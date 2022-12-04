package cn.addenda.piparbac.po;

import cn.addenda.me.fieldfilling.entity.BaseEntity;
import cn.addenda.me.idfilling.annotation.IdScope;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author addenda
 * @datetime 2022/1/17 20:45
 */
@Getter
@Setter
@ToString
@IdScope(scopeName = "tRoleModuleSqc", idFieldName = "sqc")
public class RoleModule extends BaseEntity {

    public RoleModule() {
    }

    public RoleModule(long roleSqc, long moduleSqc) {
        this.roleSqc = roleSqc;
        this.moduleSqc = moduleSqc;
    }

    private Long sqc;

    private Long roleSqc;

    private Long moduleSqc;

}
