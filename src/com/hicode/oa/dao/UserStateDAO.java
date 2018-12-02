package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.UserState;

@Repository
public class UserStateDAO extends SysDAO{

	@Autowired
	public UserStateDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public Integer do_insertUserState(UserState userState) {
		// TODO Auto-generated method stub
		return insert(nameSpace+"do_insertUserState", userState);
	}

	public List<UserState> getUserStateInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace+"getUserStateInfo", map);
		
	}

	public UserState getUserStateById(Integer state_id) {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getUserStateById", state_id);
	}

	public Integer do_updateUserState(UserState userState) {
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updateUserState", userState);
	}

}
