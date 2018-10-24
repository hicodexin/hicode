package com.hicode.oa.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.TeacherDAO;
import com.hicode.oa.tool.Teacher;

@Service
public class ModelService {

	@Autowired
	private TeacherDAO teacherDAO;
	
	public Teacher getTeacherByID(String t_id) {
		if(teacherDAO != null){
			System.out.println("teacherDAO==="+teacherDAO);
			
//			return teacherDAO.getTeacherByID(t_id);
			return null;
		}else{
			System.out.println("teacherDAO");
			return null;
		}
		
	}
	
}
