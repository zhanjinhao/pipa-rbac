package cn.addenda.piparbac;

import cn.addenda.businesseasy.util.BEArrayUtils;
import cn.addenda.piparbac.po.User;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author addenda
 * @datetime 2022/11/30 19:54
 */
public class SpringELTest {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        StandardEvaluationContext strContext = new StandardEvaluationContext(123);
        String str = parser.parseExpression("T(String).valueOf(#this)").getValue(strContext, String.class);
        System.out.println(str);

        User user1 = new User();
        user1.setUserId("1234");
        user1.setUserEmail("1234@qq.com");
        StandardEvaluationContext pojoContext = new StandardEvaluationContext(user1);
        String pojo = parser.parseExpression("#this.userId").getValue(pojoContext, String.class);
        System.out.println(pojo);

        User user2 = new User();
        user2.setUserId("2234");
        user2.setUserEmail("2234@qq.com");
        StandardEvaluationContext listContext = new StandardEvaluationContext(BEArrayUtils.asArrayList(user1, user2));
        String list = parser.parseExpression("#this[1].userId").getValue(listContext, String.class);
        System.out.println(list);

        Map<String, User> map = new HashMap<>();
        map.put("user1", user1);
        StandardEvaluationContext mapContext = new StandardEvaluationContext(map);
        String mapStr = parser.parseExpression("#this['user1'].userId").getValue(mapContext, String.class);
        System.out.println(mapStr);
    }

}
