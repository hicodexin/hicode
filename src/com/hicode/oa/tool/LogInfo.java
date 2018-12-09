package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 * 
 * @author XinPeiXiang 2018-12-08
 *
 */
public class LogInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1157412481215645L;
	/**
	 * 日志id
	 */
	private Integer log_id;
	/**
	 * 登录用户
	 */
	private UserInfo userInfo;
	/**
	 * 登录时间
	 */
	private Date log_time;
	/**
	 * 登录IP
	 */
	private String log_ip;
	/**
	 * 是否登录成功（0:未成功；1:成功）
	 */
	private Integer log_success;

	/**
	 * 登录信息备注
	 */
	private String log_remarks;

	public Integer getLog_id() {
		return log_id;
	}

	public void setLog_id(Integer log_id) {
		this.log_id = log_id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Date getLog_time() {
		return log_time;
	}

	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}

	public String getLog_ip() {
		return log_ip;
	}

	public void setLog_ip(String log_ip) {
		this.log_ip = log_ip;
	}

	public Integer getLog_success() {
		return log_success;
	}

	public void setLog_success(Integer log_success) {
		this.log_success = log_success;
	}

	public String getLog_remarks() {
		return log_remarks;
	}

	public void setLog_remarks(String log_remarks) {
		this.log_remarks = log_remarks;
	}

	@Override
	public String toString() {
		return "LogInfo [log_id=" + log_id + ", userInfo=" + userInfo + ", log_time=" + log_time + ", log_ip=" + log_ip
				+ ", log_success=" + log_success + "]";
	}

}
