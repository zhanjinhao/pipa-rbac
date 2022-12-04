package cn.addenda.piparbac.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author addenda
 * @datetime 2022/10/20 18:17
 */
@Setter
@Getter
@ToString
public class BUserRoleWithBizFields {

    private Long sqc;

    private Long userSqc;

    private Long roleSqc;

    private String userId;

    private String userName;

    private String userEmail;

    private String roleCode;

    private String roleName;

}
