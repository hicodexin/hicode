package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.Teacher;
/**
 * 讲师表数据库操作
 * @author xinpeixiang
 * @date 2018-10-22
 */
@Repository
public class TeacherDAO extends SysDAO{
	
	@Autowired
	public TeacherDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

	public Teacher getTeacherByID(String t_id){
		return selectOne(nameSpace+"getTeacherByID", t_id);
	}
	
	public List<Teacher> getTeacherAll(Integer start,Integer count){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		
		return selectList(nameSpace+"getTeacherAll", map);
	}
	
	public Integer getTeacherForCount() {
		
		return selectOne(nameSpace+"getTeacherForCount");
	}

	public Integer do_insertTeacher(Teacher teacher){
		
		return insert(nameSpace+"do_insertTeacher", teacher);
	}
	
	public String getLastTeacherID(){
		return selectOne(nameSpace+"getLastTeacherID");
	}
	
	
}
