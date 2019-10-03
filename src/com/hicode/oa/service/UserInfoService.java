package com.hicode.oa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hicode.oa.tool.UserInfo;

/**
 * 登录用户接口
 * 
 * @author XinPeiXiang 2018-12-01
 *
 */
public interface UserInfoService {

	/**
	 * 添加用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer do_insertUserInfo(UserInfo userInfo);

	/**
	 * 通过id获取用户信息
	 * 
	 * @param user_id
	 * @return
	 */
	public UserInfo getUserInfoByID(String user_id);

	/**
	 * 获取用户信息
	 * 
	 * @param start
	 * @param count
	 * @return
	 */
	public List<UserInfo> getUserInfo(Integer start, Integer count);

	public List<UserInfo> getUserInfoByName(String name);

	/**
	 * 修改用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer do_updateUserInfo(UserInfo userInfo);
	
	/**
	 * 修改用户登录失败信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer do_updateUserInfoFalseNum(UserInfo userInfo);

}
