package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.UserType;

@Repository
public class UserTypeDAO extends SysDAO {

	@Autowired
	public UserTypeDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public Integer do_insertUserType(UserType userType){
		
		return insert(nameSpace+"do_insertUserType", userType);
	}
	
	public List<UserType> getUserTypeInfo(Integer start,Integer count){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace+"getUserTypeInfo", map);
	}
	
	public UserType getUserTypeById(Integer type_id){
		
		return selectOne(nameSpace+"getUserTypeById", type_id);
	}
	
	public Integer do_updateUserType(UserType userType){
		return update(nameSpace+"do_updateUserType", userType);
	}
	
}
