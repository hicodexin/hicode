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

	@Override
	public List<Adviser> getAdvNameAndID() {
		// TODO Auto-generated method stub
		return adviserDAO.getAdvNameAndID();
	}

	@Override
	public Integer getAdvisersForCount() {
		// TODO Auto-generated method stub
		return adviserDAO.getAdvisersForCount();
	}

	@Override
	public Integer do_insertAdvisers(Adviser adviser) {
		String id = adviserDAO.getLastAdvisersID();
		Integer int_id = Integer.valueOf(id.substring(4, id.length()));
		adviser.setAdv_id(id.substring(0, 4)+(int_id+1));
		
		
		
		return adviserDAO.do_insertAdvisers(adviser);
	}

	@Override
	public Integer do_updateAdvisers(Adviser adviser) {
		// TODO Auto-generated method stub
		return adviserDAO.do_updateAdvisers(adviser);
	}

}
