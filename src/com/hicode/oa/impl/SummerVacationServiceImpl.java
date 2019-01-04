package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.service.SummerVacationService;
import com.hicode.oa.tool.SummerVacation;
import com.hicode.oa.dao.SummerVacationDAO;

/**
 *  暑假班接口
 * @author XinPeiXiang 2018-01-03
 *
 */
@Service
public class SummerVacationServiceImpl implements SummerVacationService{

	@Autowired
	private SummerVacationDAO summerVacationDAO;
	
	@Override
	public Integer do_insertSummerVacation(SummerVacation summerVacation) {
		// TODO Auto-generated method stub
		return summerVacationDAO.do_insertSummerVacation(summerVacation);
	}

	@Override
	public Integer do_updateSummerVacation(SummerVacation summerVacation) {
		// TODO Auto-generated method stub
		return summerVacationDAO.do_updateSummerVacation(summerVacation);
	}

	@Override
	public List<SummerVacation> getSummerVacationByInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		return summerVacationDAO.getSummerVacationByInfo(start, count);
	}

	@Override
	public SummerVacation getSummerVacationByID(Integer id) {
		// TODO Auto-generated method stub
		return summerVacationDAO.getSummerVacationByID(id);
	}

	@Override
	public Integer getSummerVacationForCount() {
		// TODO Auto-generated method stub
		return summerVacationDAO.getSummerVacationForCount();
	}

}
