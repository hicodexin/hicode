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
	 * 试听课信息
	 */
	private Auditions auditions;
	/**
	 * 所报课程
	 */
	private Subject subject;
	/**
	 * 所报课时(1：一个季度； 2：两个季度； 3：一个年度； 4：两个年度)
	 */
	private Integer period;
	
	private Adviser adviser;
	
	/**
	 * 是否为续费用户(1:是 ；  0:否)
	 */
	private Integer if_renewal;
	/**
	 * 授课老师ID
	 */
	private Teacher teacher;
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
	/**
	 * 是否退费（0:否；1:是）
	 */
	private Integer if_refund;
	/**
	 * 备注
	 */
	private String remarks;
	public Integer getEr_id() {
		return er_id;
	}
	public void setEr_id(Integer er_id) {
		this.er_id = er_id;
	}
	public Auditions getAuditions() {
		return auditions;
	}
	public void setAuditions(Auditions auditions) {
		this.auditions = auditions;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
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
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
	public Integer getIf_refund() {
		return if_refund;
	}
	public void setIf_refund(Integer if_refund) {
		this.if_refund = if_refund;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Adviser getAdviser() {
		return adviser;
	}
	public void setAdviser(Adviser adviser) {
		this.adviser = adviser;
	}
	@Override
	public String toString() {
		return "Customer [er_id=" + er_id + ", auditions=" + auditions + ", subject=" + subject + ", period=" + period
				+ ", adviser=" + adviser + ", if_renewal=" + if_renewal + ", teacher=" + teacher + ", first_time="
				+ first_time + ", if_done=" + if_done + ", phone=" + phone + ", if_refund=" + if_refund + ", remarks="
				+ remarks + "]";
	}

	
	
}
