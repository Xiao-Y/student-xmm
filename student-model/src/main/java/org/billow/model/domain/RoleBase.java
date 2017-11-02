package org.billow.model.domain;  

import java.io.Serializable;
import java.util.Date;

import org.billow.model.base.BaseModel;
  
/**
 * 
 * 角色管理数据库模型<br>
 *
 * 对应的表名：t_role
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-02 09:41:49
 */
public class RoleBase extends BaseModel implements Serializable { 
	
	public RoleBase() {
		super();
	}
	
	/**
	 * 主键构造器
	 * @param id 
	 */
	public RoleBase(Integer id ) {
		super();
		this.id = id;
	}
	
	// 角色名称
    private String roleName;  
	// 更新时间
    private Date updateTime;
	// 创建时间
    private Date createTime;  
	// 说明
    private String remark;  
	// 
    private Integer id;  
      
	/**
	 * 角色名称
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-02 09:41:49
	 */
    public String getRoleName(){  
        return this.roleName;  
    } 
    
    /**
	 * 角色名称
	 * 
	 * @param roleName
	 * @author billow<br>
	 * @date: 2017-11-02 09:41:49
	 */
    public void setRoleName(String roleName){  
        this.roleName=roleName;  
    }  
     
	/**
	 * 更新时间
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-02 09:41:49
	 */
    public Date getUpdateTime(){  
        return this.updateTime;  
    } 
    
    /**
	 * 更新时间
	 * 
	 * @param updateTime
	 * @author billow<br>
	 * @date: 2017-11-02 09:41:49
	 */
    public void setUpdateTime(Date updateTime){  
        this.updateTime=updateTime;  
    }  
     
	/**
	 * 创建时间
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-02 09:41:49
	 */
    public Date getCreateTime(){  
        return this.createTime;  
    } 
    
    /**
	 * 创建时间
	 * 
	 * @param createTime
	 * @author billow<br>
	 * @date: 2017-11-02 09:41:49
	 */
    public void setCreateTime(Date createTime){  
        this.createTime=createTime;  
    }  
     
	/**
	 * 说明
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-02 09:41:49
	 */
    public String getRemark(){  
        return this.remark;  
    } 
    
    /**
	 * 说明
	 * 
	 * @param remark
	 * @author billow<br>
	 * @date: 2017-11-02 09:41:49
	 */
    public void setRemark(String remark){  
        this.remark=remark;  
    }  
     
	/**
	 * 
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-02 09:41:49
	 */
    public Integer getId(){  
        return this.id;  
    } 
    
    /**
	 * 
	 * 
	 * @param id
	 * @author billow<br>
	 * @date: 2017-11-02 09:41:49
	 */
    public void setId(Integer id){  
        this.id=id;  
    }  
     

	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return "PK[id = " + id + "]";
	}
}  