package com.hicode.oa.tool;

import java.io.Serializable;

/**
 * 课程科目
 * 
 * @author xinpeixiang
 * @date 2018-10-19
 */
public class Subject implements Serializable {

	private static final long serialVersionUID = 4546547534541L;

	private String sub_id;
	private String sub_name;
	/**
	 * 是否下线(0:未下线；1:已下线)
	 */
	private Integer if_downline;

	public String getSub_id() {
		return sub_id;
	}

	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public Integer getIf_downline() {
		return if_downline;
	}

	public void setIf_downline(Integer if_downline) {
		this.if_downline = if_downline;
	}


}
