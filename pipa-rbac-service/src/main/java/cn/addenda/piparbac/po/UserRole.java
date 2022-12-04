package cn.addenda.piparbac.po;

import cn.addenda.me.fieldfilling.entity.BaseEntity;
import cn.addenda.me.idfilling.annotation.IdScope;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author addenda
 * @datetime 2022/1/17 20:44
 */
@Getter
@Setter
@ToString(callSuper = true)
@IdScope(scopeName = "tUserRoleSqc", idFieldName = "sqc")
public class UserRole extends BaseEntity {

    public UserRole() {
    }

    public UserRole(Long userSqc, Long roleSqc, String accessType, String ruleSqcList) {
        this.userSqc = userSqc;
        this.roleSqc = roleSqc;
        this.accessType = accessType;
        this.ruleSqcList = ruleSqcList;
    }

    private Long sqc;

    private Long userSqc;

    private Long roleSqc;

    private String accessType;

    private String ruleSqcList;

}
