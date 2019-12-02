package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * 名单置换
 * 
 * @author XinPeiXiang
 * @date 2019-11-23
 * 
 */
public class KuaJie_Phone implements Serializable {

	private static final long serialVersionUID = 145342223452311L;
	/**
	 * 名单置换ID
	 */
	private Integer md_id;
	/**
	 * 兑换时间
	 */
	private Date md_time;
	/**
	 * 合作机构外键
	 */
	private Integer kua_id;
	/**
	 * 兑换数量
	 */
	private Integer md_num;
	/**
	 * 年龄区间
	 */
	private String md_age;
	/**
	 * 接通率
	 */
	private Float md_pass;
	/**
	 * 备注信息
	 */
	private String md_remarks;

	public Integer getMd_id() {
		return md_id;
	}

	public void setMd_id(Integer md_id) {
		this.md_id = md_id;
	}

	public Date getMd_time() {
		return md_time;
	}

	public void setMd_time(Date md_time) {
		this.md_time = md_time;
	}

	public Integer getKua_id() {
		return kua_id;
	}

	public void setKua_id(Integer kua_id) {
		this.kua_id = kua_id;
	}

	public Integer getMd_num() {
		return md_num;
	}

	public void setMd_num(Integer md_num) {
		this.md_num = md_num;
	}

	public String getMd_age() {
		return md_age;
	}

	public void setMd_age(String md_age) {
		this.md_age = md_age;
	}

	public Float getMd_pass() {
		return md_pass;
	}

	public void setMd_pass(Float md_pass) {
		this.md_pass = md_pass;
	}

	public String getMd_remarks() {
		return md_remarks;
	}

	public void setMd_remarks(String md_remarks) {
		this.md_remarks = md_remarks;
	}

	@Override
	public String toString() {
		return "KuaJie_Phone [md_id=" + md_id + ", md_time=" + md_time + ", kua_id=" + kua_id + ", md_num=" + md_num
				+ ", md_age=" + md_age + ", md_pass=" + md_pass + ", md_remarks=" + md_remarks + "]";
	}
	
	

}
