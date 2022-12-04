package cn.addenda.piparbac.po;

import cn.addenda.me.fieldfilling.entity.BaseEntity;
import cn.addenda.me.idfilling.annotation.IdScope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author addenda
 * @datetime 2022/1/17 20:39
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@IdScope(scopeName = "tModuleSqc", idFieldName = "sqc")
public class Module extends BaseEntity {

    /**
     * 只能接收短信、邮件...，不能进入系统
     */
    public static final String AT_LISTEN = "L";

    /**
     * 只能读系统数据，不能写系统数据
     */
    public static final String AT_READ = "R";

    /**
     * 可以读系统数据，也能写系统数据
     */
    public static final String AT_WRITE = "W";

    /**
     * 目录数树
     */
    public static final String ST_NAVIGATION = "N";

    /**
     * 页面
     */
    public static final String ST_PAGE = "P";

    /**
     * 函数
     */
    public static final String ST_FUNCTION = "F";

    /**
     * 当前页面
     */
    public static final String RTT_CURRENT = "C";

    /**
     * 新页面
     */
    public static final String RTT_NEW = "N";

    /**
     * 跳到其他业务的界面
     */
    public static final String RTT_JUMP = "J";

    private Long sqc;

    /**
     * 唯一索引
     */
    private String moduleCode;

    private String moduleName;

    private String accessType;

    private String showType;

    private String responseToType;

    /**
     * 最顶层的目录是ROOT，它的sqc是0
     */
    private Long parentSqc;

    private String action;

    private String status;

    public Module(Long sqc) {
        this.sqc = sqc;
    }
}
