package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.Customer;

/**
 * 报名
 * @author xinpeixiang 
 *
 */
public interface CustomerService {
	
	
	public Customer getCustomerByID(Integer er_id);
	
	public List<Customer> getCustomerAll(Integer start, Integer count);

	/**
	 * 查询表内数据总条数
	 * @return
	 * @author xinpeixiang 2018-11-10
	 */
	public Integer getCustomerForCount();
}
