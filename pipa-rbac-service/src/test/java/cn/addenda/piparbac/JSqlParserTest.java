package cn.addenda.piparbac;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;

/**
 * @author addenda
 * @datetime 2022/12/4 16:29
 */
public class JSqlParserTest {

    public static void main(String[] args) throws Exception {
        String sql = "select * from t_user lock in share mode";

        Statement stmt = CCJSqlParserUtil.parse(sql);

    }

}
