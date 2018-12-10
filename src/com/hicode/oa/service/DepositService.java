package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.Deposit;

/**
 * 押金接口
 * @author XinPeiXiang 2018-12-10
 *
 */
public interface DepositService {

	/**
	 * 通过id查询
	 * @param dep_id
	 * @return
	 */
	public Deposit getDepositByID(Integer dep_id);

	/**
	 * 分页查询押金表数据
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Deposit> getDepositInfo(Integer start, Integer count);
	
	/**
	 * 查询表内数据总条数
	 * @return
	 */
	public Integer getCustomerForCount();

	/**
	 * 添加
	 * @param deposit
	 * @return
	 */
	public Integer do_insertDeposit(Deposit deposit);

	/**
	 * 修改
	 * @param deposit
	 * @return
	 */
	public Integer do_updateDeposit(Deposit deposit);

	
}
