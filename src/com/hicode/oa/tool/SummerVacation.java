package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * 暑假班
 * 
 * @author XinPeiXiang 2019-1-3
 *
 */
public class SummerVacation implements Serializable{

	/**
	 * 序列化版本
	 */
	private static final long serialVersionUID = 97829398236487L;
	
	/**
	 * id
	 */
	private Integer sm_id;
	/**
	 * 姓名
	 */
	private String au_name;
	
	/**
	 * 学校
	 */
	private School school;
	
	/**
	 * 年级
	 */
	private String classinfo_name;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 开课时间
	 */
	private Date start_time;
	
	/**
	 * 所选科目
	 */
	private Subject subject;

	/**
	 * 授课老师
	 */
	private Teacher teacher;
	
	
	/**
	 * 是否报名(0:未报名；1:已报名)
	 */
	private Integer if_signup;

	/**
	 * 备注
	 */
	private String remarks;
	

	public Integer getSm_id() {
		return sm_id;
	}

	public void setSm_id(Integer sm_id) {
		this.sm_id = sm_id;
	}

	public String getAu_name() {
		return au_name;
	}

	public void setAu_name(String au_name) {
		this.au_name = au_name;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getClassinfo_name() {
		return classinfo_name;
	}

	public void setClassinfo_name(String classinfo_name) {
		this.classinfo_name = classinfo_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Integer getIf_signup() {
		return if_signup;
	}

	public void setIf_signup(Integer if_signup) {
		this.if_signup = if_signup;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "SummerVacation [sm_id=" + sm_id + ", au_name=" + au_name + ", school=" + school + ", classinfo_name="
				+ classinfo_name + ", phone=" + phone + ", start_time=" + start_time + ", subject=" + subject
				+ ", teacher=" + teacher + ", if_signup=" + if_signup + ", remarks=" + remarks + "]";
	}

	
	


	
	
}
