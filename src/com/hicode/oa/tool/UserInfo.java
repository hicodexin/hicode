package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录用户信息
 * 
 * @author XinPeiXiang 
 *
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 10122241546451L;
	/**
	 * 用户id
	 */
	private String user_id;
	/**
	 * 用户名
	 */
	private String user_name;
	/**
	 * 密码
	 */
	private String user_pwd;
	/**
	 * 用户状态(0：未锁定；1：已禁用)
	 */
	private UserState userState;
	/**
	 * 用户类型(0:游客; 1:普通用户; 2:会员用户; 3:管理员;)
	 */
	private UserType userType;
	/**
	 * 用户创建时间
	 */
	private Date create_date;
	
	/**
	 * 备注信息 
	 * */
	private String remarks;
	
	private Integer false_num;
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public UserState getUserState() {
		return userState;
	}

	public void setUserState(UserState userState) {
		this.userState = userState;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public Integer getFalse_num() {
		return false_num;
	}

	public void setFalse_num(Integer false_num) {
		this.false_num = false_num;
	}

	@Override
	public String toString() {
		return "UserInfo [user_id=" + user_id + ", user_name=" + user_name + ", user_pwd=" + user_pwd + ", userState="
				+ userState + ", userType=" + userType + ", create_date=" + create_date + ", remarks=" + remarks
				+ ", false_num=" + false_num + "]";
	}

	

}
