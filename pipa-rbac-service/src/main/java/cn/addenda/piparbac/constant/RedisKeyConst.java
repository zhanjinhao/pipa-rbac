package cn.addenda.piparbac.constant;

public class RedisKeyConst {

    private RedisKeyConst() {
    }

    public static final Long CACHE_DEFAULT_TTL = 30 * 60 * 1000L;

    public static final String USER_SQC_KEY = "piparbac:user:sqc:";
    public static final String USER_USERID_KEY = "piparbac:user:userid:";

    public static final String MODULE_SQC_KEY = "piparbac:module:sqc:";
    public static final String MODULE_MODULECODE_KEY = "piparbac:module:modulecode:";

    public static final String ROLE_SQC_KEY = "piparbac:role:sqc:";
    public static final String ROLE_ROLECODE_KEY = "piparbac:role:rolecode:";

    public static final String RULE_SQC_KEY = "piparbac:rule:sqc:";
    public static final String RULE_RULECODE_KEY = "piparbac:rule:rulecode:";

}
