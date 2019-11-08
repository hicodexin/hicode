package com.hicode.oa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.Loginfo_insert_updateDAO;
import com.hicode.oa.service.Loginfo_insert_updateService;
import com.hicode.oa.tool.Loginfo_insert_update;

@Service
public class Loginfo_insert_updateServiceImpl  implements Loginfo_insert_updateService{

	@Autowired
	private Loginfo_insert_updateDAO loginfo_insert_updateDAO;
	
	@Override
	public Integer do_insertLogInfo(Loginfo_insert_update logInfo) {
		// TODO Auto-generated method stub
		return loginfo_insert_updateDAO.do_insertLogInfo(logInfo);
	}

}
