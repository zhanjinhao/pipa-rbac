package cn.addenda.piparbac.service;

import cn.addenda.piparbac.po.Role;
import cn.addenda.se.result.ServiceResult;
import com.github.pagehelper.PageInfo;

/**
 * @author addenda
 * @datetime 2022/2/7 17:16
 */
public interface RoleService {

    ServiceResult<Long> insert(Role role);

    ServiceResult<Boolean> deleteBySqc(Long sqc);

    ServiceResult<Boolean> setStatus(Long sqc, String status);

    ServiceResult<Role> queryByRoleCode(String roleCode);

    ServiceResult<PageInfo<Role>> pageQuery(Integer pageNum, Integer pageSize, Role role);

    ServiceResult<Role> queryBySqc(Long sqc);

    ServiceResult<Boolean> update(Role role);

}
