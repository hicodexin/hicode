package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.TeacherDAO;
import com.hicode.oa.service.TeacherService;
import com.hicode.oa.tool.Teacher;
/**
 * 讲师管理接口实现类
 * @author xinpeixiang
 * @date 2018-10-22
 */
@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherDAO teacherDAO;
	
	public Teacher getTeacherByID(String t_id) {
		return teacherDAO.getTeacherByID(t_id);
	}
	
	@Override
	public List<Teacher> getTeacherAll(Integer start, Integer count) {
		if(null == start){
			start = 0;
		}
		if(null == count){
			count = 20;
		}
		return teacherDAO.getTeacherAll(start, count);
	}

	@Override
	public boolean do_insert(Teacher t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_delete(Teacher t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_update(Teacher t) {
		// TODO Auto-generated method stub
		return false;
	}






	

	
}
