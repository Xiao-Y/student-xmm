package org.billow.service.system;

import javax.annotation.Resource;

import org.billow.api.system.RoleService;
import org.billow.dao.RoleDao;
import org.billow.dao.RolePermissionDao;
import org.billow.dao.UserRoleDao;
import org.billow.model.expand.RoleDto;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.RolePermissionDto;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色管理实现类<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-02 09:41:50
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDto> implements RoleService {

    @Resource
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Resource
    @Override
    public void setBaseDao(BaseDao<RoleDto> baseDao) {
        super.baseDao = this.roleDao;
    }

    @Override
    public void deleteDel(RoleDto role) {
        //删除用户角色
        userRoleDao.deleteUserRoleByRoleId(role.getId());
        //删除角色权限
        rolePermissionDao.deleteRolePermissionByRoleId(role.getId());
        //删除角色信息
        roleDao.deleteByPrimaryKey(role);
    }

    @Override
    public void saveRoleAndRolePerssion(RoleDto role, String menuIds) {
        //保存角色信息
        if (role.getId() == null) {
            roleDao.insert(role);
        } else {
            roleDao.updateByPrimaryKeySelective(role);
        }
        //保存权限信息
        rolePermissionDao.deleteRolePermissionByRoleId(role.getId());
        if (ToolsUtils.isNotEmpty(menuIds)) {
            String[] menuId = menuIds.split(",");
            for (String id : menuId) {
                RolePermissionDto dto = new RolePermissionDto();
                dto.setMenuId(new Integer(id));
                dto.setRoleId(role.getId());
                rolePermissionDao.insert(dto);
            }
        }
    }
}