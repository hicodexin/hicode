package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.UserType;
/**
 * 用户类型接口
 * @author XinPeiXiang 2018-12-01
 *
 */
public interface UserTypeService {

	/**
	 * 添加用户类型
	 * @param userType
	 * @return
	 */
	public Integer do_insertUserType(UserType userType);
	
	/**
	 * 获取相应的用户类型
	 * @param start
	 * @param count
	 * @return
	 */
	public List<UserType> getUserTypeInfo(Integer start,Integer count);
	
	/**
	 * 通过ID获取相应的用户类型
	 * @param type_id
	 * @return
	 */
	public UserType getUserTypeById(Integer type_id);
	
	/**
	 * 修改用户类型
	 * @param userType
	 * @return
	 */
	public Integer do_updateUserType(UserType userType);
}
