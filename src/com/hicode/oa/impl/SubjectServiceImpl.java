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

	@Override
	public Integer getSubjectForCount() {
		// TODO Auto-generated method stub
		return subjectDAO.getSubjectForCount();
	}

	@Override
	public Integer do_insertSubject(Subject subject) {
		String id = subjectDAO.getLastSubjectID();
		Integer int_id = Integer.valueOf(id.substring(4, id.length()));
		subject.setSub_id(id.substring(0, 4)+(int_id+1));
		return subjectDAO.do_insertSubject(subject);
	}

}
