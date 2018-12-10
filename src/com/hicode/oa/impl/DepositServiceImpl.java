package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.DepositDAO;
import com.hicode.oa.service.DepositService;
import com.hicode.oa.tool.Deposit;

@Service
public class DepositServiceImpl implements DepositService {

	@Autowired
	private DepositDAO depositDAO;

	@Override
	public Deposit getDepositByID(Integer dep_id) {
		// TODO Auto-generated method stub
		return depositDAO.getDepositByID(dep_id);
	}

	@Override
	public List<Deposit> getDepositInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		return depositDAO.getDepositInfo(start, count);
	}

	@Override
	public Integer getCustomerForCount() {
		// TODO Auto-generated method stub
		return depositDAO.getCustomerForCount();
	} 
	
	@Override
	public Integer do_insertDeposit(Deposit deposit) {
		// TODO Auto-generated method stub
		return depositDAO.do_insertDeposit(deposit);
	}

	@Override
	public Integer do_updateDeposit(Deposit deposit) {
		// TODO Auto-generated method stub
		return depositDAO.do_updateDeposit(deposit);
	}

	
}
