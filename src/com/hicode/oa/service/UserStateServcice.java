package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.UserState;

/**
 * 用户禁用状态接口
 * @author XinPeiXiang 2018-12-01
 *
 */
public interface UserStateServcice {

	/**
	 * 添加用户状态
	 * @param userState
	 * @return
	 */
	public Integer do_insertUserState(UserState userState);
	/**
	 * 查询用户状态信息
	 * @param start
	 * @param count
	 * @return
	 */
	public List<UserState> getUserStateInfo(Integer start,Integer count);
	/**
	 * 根据Id查询用户状态信息
	 * @param state_id
	 * @return
	 */
	public UserState getUserStateById(Integer state_id);
	/**
	 * 修改用户状态
	 * @param userState
	 * @return
	 */
	public Integer do_updateUserState(UserState userState);
	
	
}
