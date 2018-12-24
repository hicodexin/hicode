package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * 寒假班
 * 
 * @author XinPeiXiang 2018-12-22
 *
 */
public class WinterVacation implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 89784314524564531L;

	/**
	 * id
	 */
	private Integer wv_id;
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

	public Integer getWv_id() {
		return wv_id;
	}

	public void setWv_id(Integer wv_id) {
		this.wv_id = wv_id;
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
		return "WinterVacation [wv_id=" + wv_id + ", auditions=" + auditions + ", if_signup=" + if_signup
				+ ", give_class=" + give_class + ", start_time=" + start_time + ", clock_num=" + clock_num
				+ ", remarks=" + remarks + "]";
	}

}
