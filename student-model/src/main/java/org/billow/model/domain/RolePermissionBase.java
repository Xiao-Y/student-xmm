package org.billow.model.domain;  

import java.io.Serializable;
import org.billow.model.base.BaseModel; 
  
/**
 * 
 * 角色权限数据库模型<br>
 *
 * 对应的表名：t_role_permission
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-02 14:05:53
 */
public class RolePermissionBase extends BaseModel implements Serializable { 
	
	public RolePermissionBase() {
		super();
	}
	
	/**
	 * 主键构造器
	 * @param id 主键
	 */
	public RolePermissionBase(Integer id ) {
		super();
		this.id = id;
	}
	
	// 角色ID
    private Integer roleId;  
	// 主键
    private Integer id;  
	// 菜单ID
    private Integer menuId;  
      
	/**
	 * 角色ID
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-02 14:05:53
	 */
    public Integer getRoleId(){  
        return this.roleId;  
    } 
    
    /**
	 * 角色ID
	 * 
	 * @param roleId
	 * @author billow<br>
	 * @date: 2017-11-02 14:05:53
	 */
    public void setRoleId(Integer roleId){  
        this.roleId=roleId;  
    }  
     
	/**
	 * 主键
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-02 14:05:53
	 */
    public Integer getId(){  
        return this.id;  
    } 
    
    /**
	 * 主键
	 * 
	 * @param id
	 * @author billow<br>
	 * @date: 2017-11-02 14:05:53
	 */
    public void setId(Integer id){  
        this.id=id;  
    }  
     
	/**
	 * 菜单ID
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-02 14:05:53
	 */
    public Integer getMenuId(){  
        return this.menuId;  
    } 
    
    /**
	 * 菜单ID
	 * 
	 * @param menuId
	 * @author billow<br>
	 * @date: 2017-11-02 14:05:53
	 */
    public void setMenuId(Integer menuId){  
        this.menuId=menuId;  
    }  
     

	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return "PK[id = " + id + "]";
	}
}  