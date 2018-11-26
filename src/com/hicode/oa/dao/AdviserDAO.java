package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.Adviser;

/**
 * 顾问表
 * @author Administrator
 *
 */
@Repository
public class AdviserDAO extends SysDAO{

	@Autowired
	public AdviserDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}
	
	public Adviser getAdviserByID(String adv_id){
		return selectOne(nameSpace+"getAdviserByID", adv_id);
	}
	
	public List<Adviser> getAdviserAll(Integer start,Integer count){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace+"getAdviserAll", map);
	}
	
	public List<Adviser> getAdvNameAndID() {
		// TODO Auto-generated method stub
		return selectList(nameSpace+"getAdvNameAndID");
	}

	public Integer getAdvisersForCount(){
		return selectOne(nameSpace+"getAdvisersForCount");
	}
	
	public Integer do_insertAdvisers(Adviser adviser){
		
		return insert(nameSpace+"do_insertAdvisers", adviser);
	}
	/**
	 * 获取最后一个市场人员的ID 
	 * @return
	 */
	public String getLastAdvisersID(){
		return selectOne(nameSpace+"getLastAdvisersID");
	}

	public Integer do_updateAdvisers(Adviser adviser) {
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updateAdvisers", adviser);
	}
	
	
	
	
	
	
	
	
	
}
