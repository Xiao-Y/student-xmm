package org.billow.model.domain;

import java.io.Serializable;

import org.billow.model.base.BaseModel;

public class UserBase extends BaseModel implements Serializable {

	private static final long serialVersionUID = 7380568085775453924L;

	private Integer userId;

	private String userName;

	private Integer age;

	private String password;

	private String phoneNumber;
	private String mail;
	private SystemLogBase systemLog;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * 微信账号唯一标识
	 */
	private String openID;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
	}

	public SystemLogBase getSystemLog() {
		return systemLog;
	}

	public void setSystemLog(SystemLogBase systemLog) {
		this.systemLog = systemLog;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return "PK[userId = " + userId + "]";
	}

}