package com.hicode.oa.tool;

import java.io.Serializable;

/**
 * 用户类型(0:游客; 1:普通用户; 2:管理员;)
 * 
 * @author Administrator
 *
 */
public class UserType implements Serializable {

	private static final long serialVersionUID = 10134541546661L;

	/**
	 * 类型ID
	 */
	private Integer type_id;

	/**
	 * 用户类型(0:游客; 1:普通用户; 2:管理员;)
	 */
	private Integer type_leibie;

	private String type_remarks;
	
	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Integer getType_leibie() {
		return type_leibie;
	}

	public void setType_leibie(Integer type_leibie) {
		this.type_leibie = type_leibie;
	}

	public String getType_remarks() {
		return type_remarks;
	}

	public void setType_remarks(String type_remarks) {
		this.type_remarks = type_remarks;
	}

	@Override
	public String toString() {
		return "UserType [type_id=" + type_id + ", type_leibie=" + type_leibie + "]";
	}
	
	

}
