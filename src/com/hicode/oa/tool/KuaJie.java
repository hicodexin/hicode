package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * 跨界合作
 * 
 * @author XinPeiXiang 2019-11-9
 *
 */
public class KuaJie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9867645673346712L;

	/**
	 * 跨界合作ID
	 */
	private Integer kua_id;
	/**
	 * 首次合作时间
	 */
	private Date kua_time;
	/**
	 * 合作机构名称
	 */
	private String kua_name;
	/**
	 * 合作方地址
	 */
	private String kua_address;
	/**
	 * 合作方联系方式（手机号）
	 */
	private String kua_phone;
	/**
	 * 合作方微信
	 */
	private String kua_weixin;
	/**
	 * 我公司负责人(首次)
	 */
	private String kua_adv;
	
	/**
	 * 我公司负责人(当前)
	 */
	private String kua_adv_now;
	/**
	 * 合作方意向默认为零（最高5颗星）
	 */
	private Integer kua_yixiang;
	/**
	 * 备注信息
	 */
	private String kua_remarks;

	public Integer getKua_id() {
		return kua_id;
	}

	public void setKua_id(Integer kua_id) {
		this.kua_id = kua_id;
	}

	public Date getKua_time() {
		return kua_time;
	}

	public void setKua_time(Date kua_time) {
		this.kua_time = kua_time;
	}

	public String getKua_name() {
		return kua_name;
	}

	public void setKua_name(String kua_name) {
		this.kua_name = kua_name;
	}

	public String getKua_address() {
		return kua_address;
	}

	public void setKua_address(String kua_address) {
		this.kua_address = kua_address;
	}

	public String getKua_phone() {
		return kua_phone;
	}

	public void setKua_phone(String kua_phone) {
		this.kua_phone = kua_phone;
	}

	public String getKua_weixin() {
		return kua_weixin;
	}

	public void setKua_weixin(String kua_weixin) {
		this.kua_weixin = kua_weixin;
	}

	public String getKua_adv() {
		return kua_adv;
	}

	public void setKua_adv(String kua_adv) {
		this.kua_adv = kua_adv;
	}

	public Integer getKua_yixiang() {
		return kua_yixiang;
	}

	public void setKua_yixiang(Integer kua_yixiang) {
		this.kua_yixiang = kua_yixiang;
	}

	public String getKua_remarks() {
		return kua_remarks;
	}

	public void setKua_remarks(String kua_remarks) {
		this.kua_remarks = kua_remarks;
	}

	public String getKua_adv_now() {
		return kua_adv_now;
	}

	public void setKua_adv_now(String kua_adv_now) {
		this.kua_adv_now = kua_adv_now;
	}

	@Override
	public String toString() {
		return "KuaJie [kua_id=" + kua_id + ", kua_time=" + kua_time + ", kua_name=" + kua_name + ", kua_address="
				+ kua_address + ", kua_phone=" + kua_phone + ", kua_weixin=" + kua_weixin + ", kua_adv=" + kua_adv
				+ ", kua_adv_now=" + kua_adv_now + ", kua_yixiang=" + kua_yixiang + ", kua_remarks=" + kua_remarks
				+ "]";
	}


	

}
