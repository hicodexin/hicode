package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.UserTypeDAO;
import com.hicode.oa.service.UserTypeService;
import com.hicode.oa.tool.UserType;

@Service
public class UserTypeServiceImpl implements UserTypeService{

	@Autowired
	private UserTypeDAO userTypeDAO;

	@Override
	public Integer do_insertUserType(UserType userType) {
		// TODO Auto-generated method stub
		return userTypeDAO.do_insertUserType(userType);
	}

	@Override
	public List<UserType> getUserTypeInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		if(null == start){
			start = 0;
		}
		if(null == count){
			count = 20;
		}
		return userTypeDAO.getUserTypeInfo(start, count);
	}

	@Override
	public UserType getUserTypeById(Integer type_id) {
		// TODO Auto-generated method stub
		return userTypeDAO.getUserTypeById(type_id);
	}

	@Override
	public Integer do_updateUserType(UserType userType) {
		// TODO Auto-generated method stub
		return userTypeDAO.do_updateUserType(userType);
	}
}
