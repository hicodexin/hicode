package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Teacher;

/**
 * 讲师管理接口
 * @author xinpeixiang
 * @date 2018-10-22
 */
public interface TeacherService extends DAO<Teacher>{

	/**
	 * 通过ID获取教师对象
	 * @param t_id 讲师ID
	 * @return
	 */
	public Teacher getTeacherByID(String t_id);
	
	/**
	 * 分页获取所有老师
	 * @param start 开始
	 * @param count 个数
	 * @return 
	 */
	public List<Teacher> getTeacherAll(Integer start,Integer count);

	/**
	 * 查询讲师列表的条数
	 * @return
	 */
	public Integer getTeacherForCount();
	
	/**
	 * 添加讲师
	 * @param teacher
	 * @return
	 * @author xinpeixiang 2018-11-10
	 */
	public Integer do_insertTeacher(Teacher teacher);
	
	/**
	 * 获取讲师的姓名，ID，离职时间
	 * @return
	 */
	public List<Teacher> getTeaNameAndID();
	/**
	 * 修改讲师信息
	 * @param teacher
	 * @return
	 */
	public Integer do_updateTeacher(Teacher teacher);
	
	
	
	
}
