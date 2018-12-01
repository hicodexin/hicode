package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.AuditionsDAO;
import com.hicode.oa.service.AuditionsService;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Auditions;

@Service
public class AuditionsServiceImpl implements AuditionsService{

	@Autowired
	private AuditionsDAO auditionsDAO;
	@Override
	public Auditions getAuditionsByID(Integer au_id) {
		// TODO Auto-generated method stub
		return auditionsDAO.getAuditionsByID(au_id);
	}

	@Override
	public List<Auditions> getAuditionsByInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		if(null == start){
			start = 0;
		}
		if(null == count){
			count = 20;
		}
		return auditionsDAO.getAuditionsAll(start, count);
	}

	@Override
	public Integer do_insertAuditions(Auditions auditions) {
		// TODO Auto-generated method stub
		return auditionsDAO.do_insertAuditions(auditions);
	}
	
	@Override
	public Integer getAuditionsForCount(){
		
		return auditionsDAO.getAuditionsForCount();
	}

	@Override
	public List<Auditions> getAudNameAndID() {
		// TODO Auto-generated method stub
		return auditionsDAO.getAudNameAndID();
	}

	@Override
	public Integer do_updateAuditions(Auditions auditions) {
		// TODO Auto-generated method stub
		return auditionsDAO.do_updateAuditions(auditions);
	}
}
