package com.hicode.oa.service;

import com.hicode.oa.tool.CheckLogin;

/**
 * 登录IP检查
 * @author XinPeiXiang 2019-09-30
 *
 */
public interface CheckLoginService {

public Integer do_insertCheckLogin(CheckLogin checkLogin);
	
	public Integer do_updateCheckLogin(CheckLogin checkLogin);
	
	public CheckLogin getCheckLoginByIP(String ip);
}
