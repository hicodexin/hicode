package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * 检查登录IP
 * 
 * @author XinPeiXiang
 * @date 2019-09-30
 */
public class CheckLogin implements Serializable{

	

	private static final long serialVersionUID = 145342223452311L;
	
	/**
	 * ID
	 */
	private Integer check_id;
	/**
	 * 当前登录的IP
	 */
	private String check_ip;
	/**
	 * IP状态（1：未锁定；2：已禁用；3：已被废弃）
	 */
	private Integer check_stateid;
	/**
	 * 连续登录失败的次数（等于三次，直接锁定该ip）
	 */
	private Integer false_num;
	/**
	 * 备注信息
	 */
	private String remarks;
	public Integer getCheck_id() {
		return check_id;
	}
	public void setCheck_id(Integer check_id) {
		this.check_id = check_id;
	}
	public String getCheck_ip() {
		return check_ip;
	}
	public void setCheck_ip(String check_ip) {
		this.check_ip = check_ip;
	}
	public Integer getCheck_stateid() {
		return check_stateid;
	}
	public void setCheck_stateid(Integer check_stateid) {
		this.check_stateid = check_stateid;
	}
	public Integer getFalse_num() {
		return false_num;
	}
	public void setFalse_num(Integer false_num) {
		this.false_num = false_num;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "CheckLogin [check_id=" + check_id + ", check_ip=" + check_ip + ", check_stateid=" + check_stateid
				+ ", false_num=" + false_num + ", remarks=" + remarks + "]";
	}
	
	
	
}
