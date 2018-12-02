package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.UserStateDAO;
import com.hicode.oa.service.UserStateServcice;
import com.hicode.oa.tool.UserState;

@Service
public class UserStateServciceImpl implements UserStateServcice{

	@Autowired
	private UserStateDAO userStateDAO;

	@Override
	public Integer do_insertUserState(UserState userState) {
		// TODO Auto-generated method stub
		return userStateDAO.do_insertUserState( userState);
	}

	@Override
	public List<UserState> getUserStateInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		if(null == start){
			start = 0;
		}
		if(null == count){
			count = 20;
		}
		return userStateDAO.getUserStateInfo( start,  count) ;
	}

	@Override
	public UserState getUserStateById(Integer state_id) {
		// TODO Auto-generated method stub
		return userStateDAO.getUserStateById( state_id);
	}

	@Override
	public Integer do_updateUserState(UserState userState) {
		// TODO Auto-generated method stub
		return userStateDAO.do_updateUserState( userState);
	}
	
}
