package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.Customer;

public interface CustomerService {
	
	
	public Customer getCustomerByID(Integer er_id);
	
	public List<Customer> getCustomerAll(Integer start, Integer count);
}
