package com.hicode.oa.service;

import java.util.List;
import java.util.Map;

import com.hicode.oa.tool.Customer;

/**
 * 签单学员 
 * @author XinPeiXiang 
 *
 */
public interface CustomerService {
	
	
	public Customer getCustomerByID(Integer er_id);
	
	public List<Customer> getCustomerAll(Integer start, Integer count);

	/**
	 * 查询表内数据总条数
	 * @return
	 * @author XinPeiXiang 2018-11-10
	 */
	public Integer getCustomerForCount();
	
	/**
	 * 添加签单学员 
	 * @param customer
	 * @return
	 * @date 2018-11-11
	 */
	public Integer do_insertCustomer(Customer customer);

	public Integer do_updateCustomer(Customer customer);
	
	/**
	 * 修改备注信息
	 * @param customer
	 * @return
	 * @date 2018-12-21
	 */
	public Integer do_updateCustomerRemarks(Customer customer);

	/**
	 * 根据条件查询
	 * @param map
	 * @return
	 */
	public List<Customer> getCustomerBySomeOption(Map<String, Object> map);

	/**
	 * 根据条件》》》获取条数
	 * @param map
	 * @return
	 */
	public Integer getCustomerForCountBySomeOption(Map<String, Object> map);
	
	
	
}
