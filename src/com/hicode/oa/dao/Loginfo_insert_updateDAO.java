package com.hicode.oa.dao;

import java.util.Date;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.Loginfo_insert_update;

@Repository
public class Loginfo_insert_updateDAO extends SysDAO{

	@Autowired
	public Loginfo_insert_updateDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public Integer do_insertLogInfo(Loginfo_insert_update logInfo) {
		// TODO Auto-generated method stub
		logInfo.setCreatTime(new Date());
		return insert(nameSpace+"do_insertLogInfo", logInfo);
	}

}
