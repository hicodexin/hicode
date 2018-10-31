package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.Auditions;

@Repository
public class AuditionsDAO extends SysDAO {

	@Autowired
	public AuditionsDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}
	
	public Auditions getAuditionsByID(Integer au_id){
		return selectOne(nameSpace+"getAuditionsByID", au_id);
	}
	
	public List<Auditions> getAuditionsAll(Integer start, Integer count) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace+"getAuditionsAll", map);
	}
	
	public Integer do_insertAuditions(Auditions auditions) {
		
		return insert(nameSpace+"do_insertAuditions", auditions);
		
	}

	
}
