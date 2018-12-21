package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.CustomerDAO;
import com.hicode.oa.service.CustomerService;
import com.hicode.oa.tool.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	public Customer getCustomerByID(Integer er_id) {
		
		return customerDAO.getCustomerByID(er_id);
	}

	@Override
	public List<Customer> getCustomerAll(Integer start, Integer count) {
		// TODO Auto-generated method stub
		if(null == start){
			start = 0;
		}
		if(null == count){
			count = 20;
		}
		return customerDAO.getCustomerAll(start, count);
	}

	@Override
	public Integer getCustomerForCount() {
		// TODO Auto-generated method stub
		return customerDAO.getCustomerForCount();
	}

	@Override
	public Integer do_insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDAO.do_insertCustomer(customer);
	}

	@Override
	public Integer do_updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDAO.do_updateCustomer(customer);
	}

	@Override
	public Integer do_updateCustomerRemarks(Customer customer) {
		// TODO Auto-generated method stub
		return customerDAO.do_updateCustomerRemarks(customer);
	}
	

}
