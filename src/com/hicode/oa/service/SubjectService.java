package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.Subject;

public interface SubjectService {

	public Subject getSubjectByID(String sub_id);
	
	public List<Subject> getSubjectAll(Integer start,Integer count);
	
	/**
	 * 获取所有条数
	 * @return
	 */
	public Integer getSubjectForCount();

	/**
	 * 添加课程信息
	 * @param subject
	 * @return
	 */
	public Integer do_insertSubject(Subject subject);
}
