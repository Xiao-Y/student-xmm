package org.billow.service.system;   

import javax.annotation.Resource;

import org.billow.api.system.RolePermissionService;
import org.billow.dao.RolePermissionDao;
import org.billow.model.expand.RolePermissionDto;
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 * 角色权限实现类<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-02 14:05:54
 */
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionDto> implements RolePermissionService { 

	@Resource
	private RolePermissionDao rolePermissionDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<RolePermissionDto> baseDao) {
		super.baseDao = this.rolePermissionDao;
	}
}    