package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.Subject;

public interface SubjectService {

	public Subject getSubjectByID(String sub_id);
	
	public List<Subject> getSubjectAll(Integer start,Integer count);
}
