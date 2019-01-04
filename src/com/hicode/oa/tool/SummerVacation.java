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
	private Auditions auditions;
	/**
	 * 是否报名(0:未报名；1:已报名)
	 */
	private Integer if_signup;
	/**
	 * 赠送的寒假班课程
	 */
	private Integer give_class;

	/**
	 * 所选科目
	 */
	private Subject subject;

	/**
	 * 授课老师
	 */
	private Teacher teacher;

	/**
	 * 开课时间
	 */
	private Date start_time;

	/**
	 * 共计打卡次数
	 */
	private Integer clock_num;

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

	public Auditions getAuditions() {
		return auditions;
	}

	public void setAuditions(Auditions auditions) {
		this.auditions = auditions;
	}

	public Integer getIf_signup() {
		return if_signup;
	}

	public void setIf_signup(Integer if_signup) {
		this.if_signup = if_signup;
	}

	public Integer getGive_class() {
		return give_class;
	}

	public void setGive_class(Integer give_class) {
		this.give_class = give_class;
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

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Integer getClock_num() {
		return clock_num;
	}

	public void setClock_num(Integer clock_num) {
		this.clock_num = clock_num;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "SummerVacation [sm_id=" + sm_id + ", auditions=" + auditions + ", if_signup=" + if_signup
				+ ", give_class=" + give_class + ", subject=" + subject + ", teacher=" + teacher + ", start_time="
				+ start_time + ", clock_num=" + clock_num + ", remarks=" + remarks + "]";
	}

	
	
}
