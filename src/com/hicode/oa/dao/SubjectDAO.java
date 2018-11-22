package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.Subject;

@Repository
public class SubjectDAO  extends SysDAO{
	
	@Autowired
	public SubjectDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}
	
	public Subject getSubjectByID(String sub_id){
		System.out.println(nameSpace+"+++++++++++++");
		return this.selectOne(nameSpace+"getSubjectByID", sub_id);
	}
	
	public List<Subject> getSubjectAll(Integer start,Integer count){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return this.selectList(nameSpace+"getSubjectAll", map);
	}
	
	public Integer getSubjectForCount() {
		return selectOne(nameSpace+"getSubjectForCount");
	}

	public Integer do_insertSubject(Subject subject) {
		// TODO Auto-generated method stub
		return insert(nameSpace+"do_insertSubject", subject);
	}
	
	public String getLastSubjectID() {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getLastSubjectID");
	}

	public Integer do_updateSubject(Subject subject) {
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updateSubject", subject);
	}
	

}
