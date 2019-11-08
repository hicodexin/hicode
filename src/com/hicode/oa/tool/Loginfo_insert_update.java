package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * 添加和修改日志
 * 
 * @author XinPeiXiang 2019-11-08
 *
 */
public class Loginfo_insert_update implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12154353535435L;
	
	/**
	 * ID
	 */
	private Integer loginup_id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 修改和添加时间
	 */
	private Date creatTime;
	/**
	 * 当前IP
	 */
	private String log_ip;
	/**
	 * 操作详情
	 */
	private String xiangQing;
	
	public Integer getLoginup_id() {
		return loginup_id;
	}
	public void setLoginup_id(Integer loginup_id) {
		this.loginup_id = loginup_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public String getLog_ip() {
		return log_ip;
	}
	public void setLog_ip(String log_ip) {
		this.log_ip = log_ip;
	}
	public String getXiangQing() {
		return xiangQing;
	}
	public void setXiangQing(String xiangQing) {
		this.xiangQing = xiangQing;
	}
	@Override
	public String toString() {
		return "Loginfo_insert_update [loginup_id=" + loginup_id + ", userName=" + userName + ", creatTime=" + creatTime
				+ ", log_ip=" + log_ip + ", xiangQing=" + xiangQing + "]";
	}
	
	
	
	
	
	
	
	
	
	
	

}
