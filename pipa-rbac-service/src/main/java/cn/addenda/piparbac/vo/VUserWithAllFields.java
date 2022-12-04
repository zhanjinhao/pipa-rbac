package cn.addenda.piparbac.vo;

import cn.addenda.piparbac.po.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author addenda
 * @datetime 2022/10/16 21:56
 */
@Setter
@Getter
@ToString(callSuper = true)
@JsonIgnoreProperties
public class VUserWithAllFields extends User {



}
