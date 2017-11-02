package org.billow.model.expand;  
  
import org.billow.model.domain.RoleBase; 
  
/**
 * 
 * 角色管理model模型<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-02 09:41:50
 */
public class RoleDto extends RoleBase {
	
	public RoleDto() {
		super();
	}
	
	/**
	 * 主键构造器
	 * @param id 
	 */
	public RoleDto(Integer id ) {
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