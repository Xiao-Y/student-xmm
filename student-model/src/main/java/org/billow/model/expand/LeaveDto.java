package org.billow.model.expand;

import org.billow.model.domain.LeaveBase;

/**
 * 请假实体对象
 * 
 * @author XiaoY
 * @date: 2017年5月28日 下午3:49:01
 */
public class LeaveDto extends LeaveBase {

	/**
	 * 表单类型-普通
	 */
	public static final String TYPE_ORDINARY = "ordinary";
	/**
	 * 表单类型-外置
	 */
	public static final String TYPE_FORMKEY = "formkey";

	private static final long serialVersionUID = 3221605134094266678L;
	private UserDto userDto;
	private String type;
	private String flag;

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}