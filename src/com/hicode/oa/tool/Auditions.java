package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 试听课记录
 * 
 * @author XinPeiXiang
 * @date 2018-10-19
 */
public class Auditions implements Serializable {

	private static final long serialVersionUID = 545474735451L;

	private Integer au_id;
	/**
	 * 试听时间
	 */
	private Date st_time;
	/**
	 * 学生姓名
	 */
	private String st_name;
	/**
	 * 学生性别（1：男；0：女）
	 */
	private Integer st_sex;
	/**
	 * 年级
	 */
	private String st_class;
	
	/**
	 * 学校
	 */
	private School school;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 试听课授课老师
	 */
	private Teacher teacher;
	/**
	 * 邀约上门顾问id
	 */
	private Adviser adviser;
	/**
	 * 签单跟进顾问id
	 */
	private Adviser adviser2;
	/**
	 * 是否参与试听课（参与1，未参与0）
	 */
	private Integer if_join;
	/**
	 * 备注
	 */
	private String remarks;

	public Integer getAu_id() {
		return au_id;
	}

	public void setAu_id(Integer au_id) {
		this.au_id = au_id;
	}

	public Date getSt_time() {
		return st_time;
	}

	public void setSt_time(Date st_time) {
		this.st_time = st_time;
	}

	public String getSt_name() {
		return st_name;
	}

	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}

	public Integer getSt_sex() {
		return st_sex;
	}

	public void setSt_sex(Integer st_sex) {
		this.st_sex = st_sex;
	}

	public String getSt_class() {
		return st_class;
	}

	public void setSt_class(String st_class) {
		this.st_class = st_class;
	}
	
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Adviser getAdviser() {
		return adviser;
	}

	public void setAdviser(Adviser adviser) {
		this.adviser = adviser;
	}

	public Adviser getAdviser2() {
		return adviser2;
	}

	public void setAdviser2(Adviser adviser2) {
		this.adviser2 = adviser2;
	}

	public Integer getIf_join() {
		return if_join;
	}

	public void setIf_join(Integer if_join) {
		this.if_join = if_join;
	}

	@Override
	public String toString() {
		return "Auditions [au_id=" + au_id + ", st_time=" + st_time + ", st_name=" + st_name + ", st_sex=" + st_sex
				+ ", st_class=" + st_class + ", school=" + school + ", phone=" + phone + ", teacher=" + teacher
				+ ", adviser=" + adviser + ", adviser2=" + adviser2 + ", if_join=" + if_join + ", remarks=" + remarks
				+ "]";
	}
	
	

}
