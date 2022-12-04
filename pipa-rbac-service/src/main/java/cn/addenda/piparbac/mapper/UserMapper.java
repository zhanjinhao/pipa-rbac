package cn.addenda.piparbac.mapper;

import cn.addenda.me.constant.Constants;
import cn.addenda.me.fieldfilling.annotation.DQLFieldFilling;
import cn.addenda.piparbac.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/2/7 15:55
 */
public interface UserMapper {

    Integer insert(User user);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer userIdExists(@Param("userId") String userId);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer userEmailExists(@Param("userEmail") String userEmail);

    @DQLFieldFilling(tableNameSet = Constants.EMPTY)
    Integer sqcExists(@Param("sqc") Long sqc);

    void deleteBySqc(@Param("sqc") Long sqc);

    User queryByUserId(@Param("userId") String userId);

    List<User> queryByNonNullFields(User user);

    void updateNonNullFieldsBySqc(User user);

}
