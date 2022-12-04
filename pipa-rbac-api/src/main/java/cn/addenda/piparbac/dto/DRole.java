package cn.addenda.piparbac.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author addenda
 * @datetime 2022/10/19 19:27
 */
@Setter
@Getter
@ToString
public class DRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roleCode;

    private String roleName;

    private String status;

}
