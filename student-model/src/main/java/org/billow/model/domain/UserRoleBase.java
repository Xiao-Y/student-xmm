package org.billow.model.domain;

import java.io.Serializable;

public class UserRoleBase implements Serializable {

	private static final long serialVersionUID = 8801701851518975268L;

	private Integer userId;

	private Integer roleId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return "PK[userId = " + userId + ", roleId = " + roleId + "]";
	}
}