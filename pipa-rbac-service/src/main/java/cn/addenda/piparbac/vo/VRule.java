package cn.addenda.piparbac.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author addenda
 * @datetime 2022/10/19 19:22
 */
@Setter
@Getter
@ToString
public class VRule {

    private String ruleCode;

    private String ruleName;

    private String tableName;

    private String condition;

}
