package cn.addenda.piparbac.po;

import cn.addenda.me.fieldfilling.entity.BaseEntity;
import cn.addenda.me.idfilling.annotation.IdScope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author addenda
 * @datetime 2022/1/17 20:28
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@IdScope(scopeName = "tUserSqc", idFieldName = "sqc")
public class User extends BaseEntity {

    private Long sqc;

    /**
     * 唯一索引
     */
    private String userId;

    private String userName;

    private String userEmail;

    private String status;

    public User(Long sqc) {
        this.sqc = sqc;
    }
}
