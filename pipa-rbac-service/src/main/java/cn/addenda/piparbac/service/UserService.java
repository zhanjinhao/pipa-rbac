package cn.addenda.piparbac.service;

import cn.addenda.piparbac.po.User;
import cn.addenda.se.result.ServiceResult;
import com.github.pagehelper.PageInfo;

/**
 * @author addenda
 * @datetime 2022/2/7 17:15
 */
public interface UserService {

    ServiceResult<Long> insert(User user);

    ServiceResult<PageInfo<User>> pageQuery(Integer pageNum, Integer pageSize, User user);

    ServiceResult<User> queryBySqc(Long sqc);

    ServiceResult<Boolean> update(User user);

    ServiceResult<Boolean> setStatus(Long sqc, String status);

    ServiceResult<Boolean> deleteBySqc(Long sqc);

    ServiceResult<User> queryByUserId(String userId);

}
