package cn.addenda.piparbac.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author addenda
 * @datetime 2022/10/19 20:24
 */
@Setter
@Getter
@ToString
public class VUserRoleWithBizFields {

    private Long sqc;

    private Long userSqc;

    private Long roleSqc;

    private String userId;

    private String userName;

    private String userEmail;

    private String roleCode;

    private String roleName;

}
