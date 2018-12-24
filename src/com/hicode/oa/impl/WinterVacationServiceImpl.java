package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.WinterVacationDAO;
import com.hicode.oa.service.WinterVacationService;
import com.hicode.oa.tool.WinterVacation;

/**
 *  寒假班接口
 * @author XinPeiXiang 2018-12-22
 *
 */
@Service
public class WinterVacationServiceImpl implements WinterVacationService{

	@Autowired
	private WinterVacationDAO winterVacationDAO;
	
	
	@Override
	public Integer do_insertWinterVacation(WinterVacation winterVacation) {
		// TODO Auto-generated method stub
		return winterVacationDAO.do_insertWinterVacation(winterVacation);
	}

	@Override
	public Integer do_updateWinterVacation(WinterVacation winterVacation) {
		// TODO Auto-generated method stub
		return winterVacationDAO.do_updateWinterVacation(winterVacation);
	}

	@Override
	public List<WinterVacation> getWinterVacationByInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		return winterVacationDAO.getgetWinterVacationByInfo(start, count);
	}

	@Override
	public WinterVacation getWinterVacationByID(Integer id) {
		// TODO Auto-generated method stub
		return winterVacationDAO.getWinterVacationByID(id);
	}

	@Override
	public Integer getWinterVacationForCount() {
		// TODO Auto-generated method stub
		return winterVacationDAO.getWinterVacationForCount();
	}

}
