package com.hicode.oa.tool;

import java.io.Serializable;

/**
 * øŒ≥Ã¿‡
 * 
 * @author xinpeixiang
 * @date 2018-10-19
 */
public class Subject implements Serializable {

	private static final long serialVersionUID = 4546547534541L;

	private String sub_id;
	private String sub_name;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
