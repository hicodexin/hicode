package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.AdviserDAO;
import com.hicode.oa.service.AdviserService;
import com.hicode.oa.tool.Adviser;

@Service
public class AdviserServiceImpl implements AdviserService{
	
	@Autowired
	private AdviserDAO adviserDAO;
	
	@Override
	public Adviser getAdviserByID(String adv_id) {
		// TODO Auto-generated method stub
		return adviserDAO.getAdviserByID(adv_id);
	}

	@Override
	public List<Adviser> getAdviserAll(Integer start, Integer count) {
		// TODO Auto-generated method stub
		if(null == start){
			start = 0;
		}
		if(null == count){
			count = 20;
		}
		return adviserDAO.getAdviserAll(start,count);
	}

}
