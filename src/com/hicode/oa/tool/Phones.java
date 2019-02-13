package com.hicode.oa.tool;

import java.io.Serializable;

/**
 * 电话量
 * 
 * @author XinPeiXiang 2019-01-26
 *
 */
public class Phones implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 15623876827L;
	/**
	 * id
	 */
	private Integer ph_id;
	/**
	 * 学生姓名
	 */
	private String ph_name;
	/**
	 * 年龄
	 */
	private Integer ph_age;
	/**
	 * 性别
	 */
	private Integer ph_sex;
	/**
	 * 手机号
	 */
	private String phone_num;
	/**
	 * 学校
	 */
	private String ph_school;
	/**
	 * TMK(对应登录用户的id)
	 */
	private String ph_tmk;
	/**
	 * 意向（0:待确定；1:错号/空号；2:近期上门；3:可邀约上门；4:意向一般；5:无意向；）
	 */
	private Integer ph_intention;
	/**
	 * 概况( 孩子的基本信息 )
	 */
	private String ph_survey;
	/**
	 * 是否上门(0:未上门; 1:上门)
	 */
	private Integer if_arrival;
	/**
	 * 跟踪情况
	 */
	private String ph_remarks;

	public Integer getPh_id() {
		return ph_id;
	}

	public void setPh_id(Integer ph_id) {
		this.ph_id = ph_id;
	}

	public String getPh_name() {
		return ph_name;
	}

	public void setPh_name(String ph_name) {
		this.ph_name = ph_name;
	}

	public Integer getPh_age() {
		return ph_age;
	}

	public void setPh_age(Integer ph_age) {
		this.ph_age = ph_age;
	}

	public Integer getPh_sex() {
		return ph_sex;
	}

	public void setPh_sex(Integer ph_sex) {
		this.ph_sex = ph_sex;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getPh_school() {
		return ph_school;
	}

	public void setPh_school(String ph_school) {
		this.ph_school = ph_school;
	}

	public String getPh_tmk() {
		return ph_tmk;
	}

	public void setPh_tmk(String ph_tmk) {
		this.ph_tmk = ph_tmk;
	}

	public Integer getPh_intention() {
		return ph_intention;
	}

	public void setPh_intention(Integer ph_intention) {
		this.ph_intention = ph_intention;
	}

	public String getPh_survey() {
		return ph_survey;
	}

	public void setPh_survey(String ph_survey) {
		this.ph_survey = ph_survey;
	}

	public Integer getIf_arrival() {
		return if_arrival;
	}

	public void setIf_arrival(Integer if_arrival) {
		this.if_arrival = if_arrival;
	}

	public String getPh_remarks() {
		return ph_remarks;
	}

	public void setPh_remarks(String ph_remarks) {
		this.ph_remarks = ph_remarks;
	}

	@Override
	public String toString() {
		return "Phones [ph_id=" + ph_id + ", ph_name=" + ph_name + ", ph_age=" + ph_age + ", ph_sex=" + ph_sex
				+ ", phone_num=" + phone_num + ", ph_school=" + ph_school + ", ph_tmk=" + ph_tmk + ", ph_intention="
				+ ph_intention + ", ph_survey=" + ph_survey + ", if_arrival=" + if_arrival + ", ph_remarks="
				+ ph_remarks + "]";
	}
	
	
	
	

}
