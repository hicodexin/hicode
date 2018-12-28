package com.hicode.oa.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.School;

@Repository
public class SchoolDAO extends SysDAO{

	@Autowired
	public SchoolDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public School getSchoolByID(Integer id){
		return selectOne(nameSpace+"getSchoolByID",id);
	}
	
	public List<School> getSchoolByInfo() {
		// TODO Auto-generated method stub
		return selectList(nameSpace+"getSchoolByInfo");
	}

	public Integer do_insertSchool(School school) {
		// TODO Auto-generated method stub
		return insert(nameSpace+"do_insertSchool",school);
	}

	public Integer do_updateSchool(School school) {
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updateSchool",school);
	}

}
