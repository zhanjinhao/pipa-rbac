package cn.addenda.piparbac.manager;

import cn.addenda.businesseasy.cache.CacheHelper;
import cn.addenda.piparbac.constant.RedisKeyConst;
import cn.addenda.piparbac.mapper.RoleMapper;
import cn.addenda.piparbac.po.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author addenda
 * @datetime 2022/10/9 15:41
 */
@Component
public class RoleManagerImpl implements RoleManager {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private CacheHelper redisCacheHelper;

    @Override
    public boolean roleCodeExists(String roleCode) {
        Integer integer = roleMapper.roleCodeExists(roleCode);
        return integer != null && integer > 0;
    }

    @Override
    public boolean sqcExists(Long sqc) {
        Integer integer = roleMapper.sqcExists(sqc);
        return integer != null && integer > 0;
    }

    @Override
    public void insert(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void deleteBySqc(Long sqc) {
        roleMapper.deleteBySqc(sqc);
    }

    @Override
    public void setStatus(Long sqc, String status) {
        Role role = new Role();
        role.setSqc(sqc);
        role.setStatus(status);
        roleMapper.updateNonNullFieldsBySqc(role);
    }

    @Override
    public Role queryByRoleCode(String roleCode) {
        return redisCacheHelper.queryWithPerformanceFirst(RedisKeyConst.ROLE_ROLECODE_KEY,
                roleCode, Role.class, roleMapper::queryByRoleCode, RedisKeyConst.CACHE_DEFAULT_TTL);
    }

    @Override
    public List<Role> queryByNonNullFields(Role role) {
        return roleMapper.queryByNonNullFields(role);
    }

    @Override
    public Role queryBySqc(Long sqc) {
        return redisCacheHelper.queryWithPerformanceFirst(RedisKeyConst.ROLE_SQC_KEY,
                sqc, Role.class, this::doQueryBySqc, RedisKeyConst.CACHE_DEFAULT_TTL);
    }

    private Role doQueryBySqc(Long sqc) {
        Role role = new Role();
        role.setSqc(sqc);
        List<Role> roles = roleMapper.queryByNonNullFields(role);
        if (roles.isEmpty()) {
            return null;
        }
        return roles.get(0);
    }

    @Override
    public void update(Role role) {
        roleMapper.updateNonNullFieldsBySqc(role);
    }

}
