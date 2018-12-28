package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.School;

/**
 * 学校信息
 * 
 * @author XinPeiXiang 2018-12-23
 *
 */
public interface SchoolService {
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public School getSchoolByID(Integer id);

	/**
	 * 查询学校信息
	 * @return
	 */
	public List<School> getSchoolByInfo();
	
	/**
	 * 添加学校信息
	 * @param school
	 * @return
	 */
	public Integer do_insertSchool(School school);
	
	/**
	 * 修改学校信息
	 * @param school
	 * @return
	 */
	public Integer do_updateSchool(School school);
	
	
	
}
