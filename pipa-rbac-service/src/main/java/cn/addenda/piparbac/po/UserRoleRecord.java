package cn.addenda.piparbac.po;

import cn.addenda.me.fieldfilling.entity.BaseEntity;
import cn.addenda.me.idfilling.annotation.IdScope;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author addenda
 * @datetime 2022/1/17 20:46
 */
@Getter
@Setter
@ToString
@IdScope(scopeName = "tUserRoleRecord", idFieldName = "sqc")
public class UserRoleRecord extends BaseEntity {

    public static final String TYPE_ENTER = "ER";

    public static final String TYPE_CHANGE_ROLE = "CR";

    private Long sqc;

    private Long userSqc;

    private Long roleSqc;

    private String type;

}
