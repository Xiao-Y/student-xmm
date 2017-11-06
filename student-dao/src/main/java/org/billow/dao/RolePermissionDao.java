package org.billow.dao;

import org.billow.dao.base.BaseDao;
import org.billow.model.expand.RolePermissionDto;

/**
 * 角色权限接口<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-02 14:05:54
 */
public interface RolePermissionDao extends BaseDao<RolePermissionDto> {


    /**
     * 通过角色id删除角色权限
     *
     * @param roleId
     */
    void deleteRolePermissionByRoleId(Integer roleId);

    /**
     * 通过角色id和菜单id删除权限信息
     *
     * @param dto
     */
    void deleteByRoleIdAndMenuId(RolePermissionDto dto);
}