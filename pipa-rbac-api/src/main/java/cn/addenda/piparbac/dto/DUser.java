package cn.addenda.piparbac.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class DUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String userName;

    private String userEmail;

    private String status;

}