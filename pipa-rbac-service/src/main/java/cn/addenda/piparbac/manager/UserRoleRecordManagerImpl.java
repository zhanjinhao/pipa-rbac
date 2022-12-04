package cn.addenda.piparbac.manager;

import cn.addenda.businesseasy.cache.CacheHelper;
import cn.addenda.piparbac.mapper.UserRoleRecordMapper;
import cn.addenda.piparbac.po.UserRole;
import cn.addenda.piparbac.po.UserRoleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author addenda
 * @datetime 2022/10/10 15:26
 */
@Component
public class UserRoleRecordManagerImpl implements UserRoleRecordManager {

    @Autowired
    private UserRoleRecordMapper userRoleRecordMapper;

    @Autowired
    private CacheHelper redisCacheHelper;

    @Override
    public UserRoleRecord queryUserRoleRecordByUserSqc(Long userSqc) {
        return userRoleRecordMapper.queryUserRoleRecordByUserSqc(userSqc);
    }

    @Override
    public void insert(UserRoleRecord userRoleRecord) {
        userRoleRecordMapper.insert(userRoleRecord);
    }

    @Override
    public void deleteByUserSqc(Long userSqc) {
        userRoleRecordMapper.deleteByUserSqc(userSqc);
    }

    @Override
    public UserRole queryLoginRole(Long userSqc) {
        return userRoleRecordMapper.queryLoginRole(userSqc);
    }

}
