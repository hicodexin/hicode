package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.UserInfoDAO;
import com.hicode.oa.service.UserInfoService;
import com.hicode.oa.tool.UserInfo;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDAO userInfoDAO;

	@Override
	public Integer do_insertUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoDAO.do_insertUserInfo(userInfo);
	}

	@Override
	public UserInfo getUserInfoByID(String user_id) {
		// TODO Auto-generated method stub
		return userInfoDAO.getUserInfoByID(user_id);
	}

	@Override
	public List<UserInfo> getUserInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		return userInfoDAO.getUserInfo(start, count);
	}

	@Override
	public Integer do_updateUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoDAO.do_updateUserInfo(userInfo);
	}

	@Override
	public List<UserInfo> getUserInfoByName(String name) {
		// TODO Auto-generated method stub
		return userInfoDAO.getUserInfoByName(name);
	}

}
