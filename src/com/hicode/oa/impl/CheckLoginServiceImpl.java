package com.hicode.oa.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.CheckLoginDAO;
import com.hicode.oa.service.CheckLoginService;
import com.hicode.oa.tool.CheckLogin;
/**
 * 登录IP检查
 * @author XinPeiXiang 2019-09-30
 *
 */
@Service
public class CheckLoginServiceImpl implements CheckLoginService{

	@Autowired
	private CheckLoginDAO checkLoginDAO;

	@Override
	public Integer do_insertCheckLogin(CheckLogin checkLogin) {
		// TODO Auto-generated method stub
		return checkLoginDAO.do_insertCheckLogin(checkLogin);
	}

	@Override
	public Integer do_updateCheckLogin(CheckLogin checkLogin) {
		// TODO Auto-generated method stub
		return checkLoginDAO.do_updateCheckLogin(checkLogin);
	}

	@Override
	public CheckLogin getCheckLoginByIP(String ip) {
		// TODO Auto-generated method stub
		return checkLoginDAO.getCheckLoginByIP(ip);
	}
	
	

}
