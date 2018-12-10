package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * 押金表
 * 
 * @author XinPeiXiang 2018-12-10
 *
 */
public class Deposit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 98676456712546712L;

	/**
	 * 押金id
	 */
	private Integer dep_id;

	/**
	 * 试听学员的id
	 */
	private Auditions auditions;

	/**
	 * 押金交付时间
	 */
	private Date pay_time;

	/**
	 * 押金数量
	 */
	private Float money;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 签单顾问
	 */
	private Adviser adviser;

	/**
	 * 邀约顾问
	 */
	private Adviser adviser2;

	/**
	 * 是否报名(0:未报名；1:已报名)
	 */
	private Integer ifSignup;

	/**
	 * 退费金额
	 */
	private Float refundMoney;

	/**
	 * 退费时间
	 */
	private Date refund_time;

	/**
	 * 备注
	 */
	private String remark;

	public Integer getDep_id() {
		return dep_id;
	}

	public void setDep_id(Integer dep_id) {
		this.dep_id = dep_id;
	}

	public Auditions getAuditions() {
		return auditions;
	}

	public void setAuditions(Auditions auditions) {
		this.auditions = auditions;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Integer getIfSignup() {
		return ifSignup;
	}

	public void setIfSignup(Integer ifSignup) {
		this.ifSignup = ifSignup;
	}

	public Float getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Float refundMoney) {
		this.refundMoney = refundMoney;
	}

	public Date getRefund_time() {
		return refund_time;
	}

	public void setRefund_time(Date refund_time) {
		this.refund_time = refund_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Deposit [dep_id=" + dep_id + ", auditions=" + auditions + ", pay_time=" + pay_time + ", money=" + money
				+ ", phone=" + phone + ", adviser=" + adviser + ", adviser2=" + adviser2 + ", ifSignup=" + ifSignup
				+ ", refundMoney=" + refundMoney + ", refund_time=" + refund_time + ", remark=" + remark + "]";
	}

}
