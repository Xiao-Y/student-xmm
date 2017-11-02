package org.billow.api.system;

import org.billow.api.base.BaseService;
import org.billow.model.expand.RoleDto;

/**
 * 角色管理接口<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-02 09:41:50
 */
public interface RoleService extends BaseService<RoleDto> {

    /**
     * 删除用户角色和角色有关的权限
     *
     * @param role roleId
     */
    void deleteDel(RoleDto role);

    /**
     * 保存角色信息修改
     *
     * @param role    角色信息
     * @param menuIds 权限信息
     */
    void saveRoleAndRolePerssion(RoleDto role, String menuIds);
}