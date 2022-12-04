package cn.addenda.piparbac.po;

import cn.addenda.me.fieldfilling.entity.BaseEntity;
import cn.addenda.me.idfilling.annotation.IdScope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author addenda
 * @datetime 2022/10/19 19:23
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@IdScope(scopeName = "tRuleSqc", idFieldName = "sqc")
public class Rule extends BaseEntity {

    private Long sqc;

    private String ruleCode;

    private String ruleName;

    private String tableName;

    private String condition;

    private String status;

    public Rule(Long sqc) {
        this.sqc = sqc;
    }
}
