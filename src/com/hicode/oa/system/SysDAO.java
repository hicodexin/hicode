package com.hicode.oa.system;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO 的基类
 * @author xinpeixiang
 * @date 2018-10-22
 */
@Repository
public class SysDAO extends SqlSessionTemplate{
	
	protected  String nameSpace = this.getClass().getName()+".";
	
	@Autowired
	public SysDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}



	


}
