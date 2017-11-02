package org.billow.model.expand;  
  
import org.billow.model.domain.RolePermissionBase; 
  
/**
 * 
 * 角色权限model模型<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-02 14:05:54
 */
public class RolePermissionDto extends RolePermissionBase {
	
	public RolePermissionDto() {
		super();
	}
	
	/**
	 * 主键构造器
	 * @param id 主键
	 */
	public RolePermissionDto(Integer id ) {
		super(id );
	}
	
	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}  