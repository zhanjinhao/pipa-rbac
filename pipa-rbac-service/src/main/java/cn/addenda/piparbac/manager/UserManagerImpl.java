package cn.addenda.piparbac.manager;

import cn.addenda.businesseasy.cache.CacheHelper;
import cn.addenda.piparbac.constant.RedisKeyConst;
import cn.addenda.piparbac.mapper.UserMapper;
import cn.addenda.piparbac.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/10/8 17:17
 */
@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CacheHelper redisCacheHelper;

    @Override
    public boolean userIdExists(String userId) {
        Integer integer = userMapper.userIdExists(userId);
        return integer != null && integer != 0;
    }

    @Override
    public boolean userEmailExists(String userEmail) {
        Integer integer = userMapper.userEmailExists(userEmail);
        return integer != null && integer != 0;
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void deleteBySqc(Long sqc) {
        redisCacheHelper.acceptWithPerformanceFirst(
                RedisKeyConst.USER_SQC_KEY, sqc, aLong -> userMapper.deleteBySqc(sqc));
    }

    @Override
    public void setStatus(Long sqc, String status) {
        redisCacheHelper.acceptWithPerformanceFirst(RedisKeyConst.USER_SQC_KEY, sqc,
                aLong -> {
                    User user = new User();
                    user.setSqc(sqc);
                    user.setStatus(status);
                    userMapper.updateNonNullFieldsBySqc(user);
                });
    }

    @Override
    public boolean sqcExists(Long sqc) {
        Integer integer = userMapper.sqcExists(sqc);
        return integer != null && integer != 0;
    }

    @Override
    public User queryByUserId(String userId) {
        return redisCacheHelper.queryWithPerformanceFirst(RedisKeyConst.USER_USERID_KEY,
                userId, User.class, userMapper::queryByUserId, RedisKeyConst.CACHE_DEFAULT_TTL);
    }

    @Override
    public List<User> queryByNonNullFields(User user) {
        return userMapper.queryByNonNullFields(user);
    }

    @Override
    public User queryBySqc(Long sqc) {
        return redisCacheHelper.queryWithPerformanceFirst(RedisKeyConst.USER_SQC_KEY,
                sqc, User.class, this::doQueryBySqc, RedisKeyConst.CACHE_DEFAULT_TTL);
    }

    private User doQueryBySqc(Long sqc) {
        User user = new User();
        user.setSqc(sqc);
        List<User> users = userMapper.queryByNonNullFields(user);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public void update(User user) {
        redisCacheHelper.acceptWithPerformanceFirst(
                RedisKeyConst.USER_SQC_KEY, user.getSqc(), aLong -> userMapper.updateNonNullFieldsBySqc(user));
    }

}
