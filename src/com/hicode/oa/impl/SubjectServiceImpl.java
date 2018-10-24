package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.SubjectDAO;
import com.hicode.oa.service.SubjectService;
import com.hicode.oa.tool.Subject;

@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectDAO subjectDAO;
	
	@Override
	public Subject getSubjectByID(String sub_id) {
		return subjectDAO.getSubjectByID(sub_id);
	}

	@Override
	public List<Subject> getSubjectAll(Integer start, Integer count) {
		// TODO Auto-generated method stub
		if(null == start){
			start = 0;
		}
		if(null == count){
			count = 20;
		}
		return subjectDAO.getSubjectAll(start, count);
	}

}
