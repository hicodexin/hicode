package com.hicode.oa.tool;

import java.io.Serializable;

/**
 * 跟单详情
 * 
 * @author XinPeiXiang 2019-05-09
 *
 */
public class Signing implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 156200076827L;
	/**
	 * id
	 */
	private Integer sig_id;
	/**
	 * 试听课学生信息
	 */
	private Auditions auditions;
	/**
	 * 面资当天的具体情况
	 */
	private String situation;
	/**
	 * 用户分类（1.两周内可签单；2.一个月内可签单；3.需要长期跟踪；4.无意向）
	 */
	private Integer category;
	/**
	 * 第一次追踪记录
	 */
	private String tracking_one;
	/**
	 * 第二次追踪记录
	 */
	private String tracking_two;
	/**
	 * 第三次追踪记录
	 */
	private String tracking_three;
	/**
	 * 是否报名（0:未报名；1:已报名；2:死单）
	 */
	private Integer if_signup;
	/**
	 * 第一接单人
	 */
	private Adviser adviser;
	
	/**
	 * 当前接单人
	 */
	private Adviser adviser_now;
	
	/**
	 * 最终接单人
	 */
	private Adviser adv_success_id;
	
	/**
	 * 交接历史
	 */
	private String history;
	
	public Integer getSig_id() {
		return sig_id;
	}

	public void setSig_id(Integer sig_id) {
		this.sig_id = sig_id;
	}

	public Auditions getAuditions() {
		return auditions;
	}

	public void setAuditions(Auditions auditions) {
		this.auditions = auditions;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getTracking_one() {
		return tracking_one;
	}

	public void setTracking_one(String tracking_one) {
		this.tracking_one = tracking_one;
	}

	public String getTracking_two() {
		return tracking_two;
	}

	public void setTracking_two(String tracking_two) {
		this.tracking_two = tracking_two;
	}

	public String getTracking_three() {
		return tracking_three;
	}

	public void setTracking_three(String tracking_three) {
		this.tracking_three = tracking_three;
	}

	public Integer getIf_signup() {
		return if_signup;
	}

	public void setIf_signup(Integer if_signup) {
		this.if_signup = if_signup;
	}

	public Adviser getAdviser() {
		return adviser;
	}

	public void setAdviser(Adviser adviser) {
		this.adviser = adviser;
	}
	
	public Adviser getAdviser_now() {
		return adviser_now;
	}

	public void setAdviser_now(Adviser adviser_now) {
		this.adviser_now = adviser_now;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}
	
	public Adviser getAdv_success_id() {
		return adv_success_id;
	}

	public void setAdv_success_id(Adviser adv_success_id) {
		this.adv_success_id = adv_success_id;
	}

	@Override
	public String toString() {
		return "Signing [sig_id=" + sig_id + ", auditions=" + auditions + ", situation=" + situation + ", category="
				+ category + ", tracking_one=" + tracking_one + ", tracking_two=" + tracking_two + ", tracking_three="
				+ tracking_three + ", if_signup=" + if_signup + ", adviser=" + adviser + ", adviser_now=" + adviser_now
				+ ", adv_success_id=" + adv_success_id + ", history=" + history + "]";
	}


	

	


	
	
	
	

}
