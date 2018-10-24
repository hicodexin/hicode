package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.AuditionsDAO;
import com.hicode.oa.service.AuditionsService;
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
	public List<Auditions> getAuditionsAll(Integer start, Integer count) {
		// TODO Auto-generated method stub
		return auditionsDAO.getAuditionsAll(start, count);
	}

}
