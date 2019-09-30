package com.hicode.oa.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.CheckLogin;


@Repository
public class CheckLoginDAO extends SysDAO{

	@Autowired
	public CheckLoginDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}
	
	public Integer do_insertCheckLogin(CheckLogin checkLogin){
		
		return insert(nameSpace+"do_insertCheckLogin",checkLogin);
	}
	
	public Integer do_updateCheckLogin(CheckLogin checkLogin) {
		return update(nameSpace+"do_updateCheckLogin", checkLogin);
	}
	
	public CheckLogin getCheckLoginByIP(String ip) {
		return selectOne(nameSpace+"getCheckLoginByIP", ip);
	}

}
