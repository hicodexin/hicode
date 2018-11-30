package com.hicode.oa.tool;

import java.io.Serializable;

/**
 * 验证码表
 * 
 * @author xinpeixiang 2018-11-30
 *
 */
public class ValidateCode implements Serializable{

	

	private static final long serialVersionUID = 14534312862311L;

	/**
	 * id
	 */
	private Integer code_id;
	
	/**
	 * 验证图片的名称
	 */
	private String code_img;

	public Integer getCode_id() {
		return code_id;
	}

	public void setCode_id(Integer code_id) {
		this.code_id = code_id;
	}

	public String getCode_img() {
		return code_img;
	}

	public void setCode_img(String code_img) {
		this.code_img = code_img;
	}

	@Override
	public String toString() {
		return "ValidateCode [code_id=" + code_id + ", code_img=" + code_img + "]";
	}

	
}
