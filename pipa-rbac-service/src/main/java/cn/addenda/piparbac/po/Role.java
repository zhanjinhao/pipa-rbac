package cn.addenda.piparbac.po;

import cn.addenda.me.fieldfilling.entity.BaseEntity;
import cn.addenda.me.idfilling.annotation.IdScope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author addenda
 * @datetime 2022/1/17 20:31
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@IdScope(scopeName = "tRoleSqc", idFieldName = "sqc")
public class Role extends BaseEntity {

    private Long sqc;

    /**
     * 唯一索引
     */
    private String roleCode;

    private String roleName;

    private String status;

    public Role(Long sqc) {
        this.sqc = sqc;
    }
}
