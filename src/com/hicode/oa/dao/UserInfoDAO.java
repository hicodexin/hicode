package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.UserInfo;

/**
 * 登录用户
 * @author Administrator
 *
 */
@Repository
public class UserInfoDAO extends SysDAO {

	@Autowired
	public UserInfoDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}
	
	public Integer do_insertUserInfo(UserInfo userInfo){
		
		return insert(nameSpace+"do_insertUserInfo", userInfo);
	}
	
	public UserInfo getUserInfoByID(String user_id){
		
		return selectOne(nameSpace+"getUserInfoByID", user_id);
	}
	
	public List<UserInfo> getUserInfo(Integer start,Integer count){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace+"getUserInfo", map);
	}
	
	public Integer do_updateUserInfo(UserInfo userInfo){
		return update(nameSpace+"do_updateUserInfo", userInfo);
	}
}
