package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.ValidateCode;

/**
 * 验证码接口
 * @author xinpeixiang 2018-11-30
 *
 */
public interface ValidateCodeService {

	/**
	 * 添加验证码
	 * @param validateCode
	 * @return
	 */
	public Integer do_insertValidateCode(ValidateCode validateCode);
	
	public List<ValidateCode> getValidateCodeInfo(Integer start, Integer count);
}
