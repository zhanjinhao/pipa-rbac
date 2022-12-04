package cn.addenda.piparbac.rpc;

import cn.addenda.piparbac.dto.DUser;

/**
 * @author addenda
 * @datetime 2022/10/15 10:20
 */
public interface UserRpc {

    DUser queryByUserId(String userId);

}
