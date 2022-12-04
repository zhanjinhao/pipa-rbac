package cn.addenda.piparbac.vo;

import cn.addenda.piparbac.po.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author addenda
 * @datetime 2022/10/19 19:06
 */
@Setter
@Getter
@ToString(callSuper = true)
@JsonIgnoreProperties
public class VRoleWithAllFields extends Role {
}
