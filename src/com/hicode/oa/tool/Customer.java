package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * 签约客户详细信息
 * 
 * @author xinpeixiang
 * @date 2018-10-19
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 14678946416411L;

	private Integer er_id;
	/**
	 * 试听课ID
	 */
	private Integer au_id;
	/**
	 * 所报课程ID
	 */
	private String sub_id;
	/**
	 * 所报课时(1：季度； 2：年度； 3：两年)
	 */
	private Integer period;
	/**
	 * 是否为续费用户(1:是 ；  0:否)
	 */
	private Integer if_renewal;
	/**
	 * 授课老师ID
	 */
	private String t_id;
	/**
	 * 首次上课时间
	 */
	private Date first_time;
	/**
	 * 当前学期是否已经结束(1:是；0:否)
	 */
	private Integer if_done;
	/**
	 * 联系电话
	 */
	private String phone;

	public Integer getEr_id() {
		return er_id;
	}

	public void setEr_id(Integer er_id) {
		this.er_id = er_id;
	}

	public Integer getAu_id() {
		return au_id;
	}

	public void setAu_id(Integer au_id) {
		this.au_id = au_id;
	}

	public String getSub_id() {
		return sub_id;
	}

	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getIf_renewal() {
		return if_renewal;
	}

	public void setIf_renewal(Integer if_renewal) {
		this.if_renewal = if_renewal;
	}

	public String getT_id() {
		return t_id;
	}

	public void setT_id(String t_id) {
		this.t_id = t_id;
	}

	public Date getFirst_time() {
		return first_time;
	}

	public void setFirst_time(Date first_time) {
		this.first_time = first_time;
	}

	public Integer getIf_done() {
		return if_done;
	}

	public void setIf_done(Integer if_done) {
		this.if_done = if_done;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
