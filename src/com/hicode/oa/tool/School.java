package com.hicode.oa.tool;

import java.io.Serializable;

/**
 * 学校信息
 * 
 * @author XinPeiXiang 2018-12-23
 *
 */
public class School implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 144543345412312L;

	/**
	 * 学校id
	 */
	private Integer sch_id;

	/**
	 * 学校名称
	 */
	private String sch_name;
	/**
	 * 备注
	 */
	private String remarks;

	public Integer getSch_id() {
		return sch_id;
	}

	public void setSch_id(Integer sch_id) {
		this.sch_id = sch_id;
	}

	public String getSch_name() {
		return sch_name;
	}

	public void setSch_name(String sch_name) {
		this.sch_name = sch_name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "School [sch_id=" + sch_id + ", sch_name=" + sch_name + ", remarks=" + remarks + "]";
	}

}
