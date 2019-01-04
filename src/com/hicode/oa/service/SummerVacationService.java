package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.SummerVacation;

/**
 * 暑假班接口
 * @author XinPeiXiang 2019-01-03
 *
 */
public interface SummerVacationService {

	/**
	 * 添加暑假班学员
	 * @param SummerVacation
	 * @return
	 */
	public Integer do_insertSummerVacation(SummerVacation summerVacation);
	
	/**
	 * 修改暑假班学员信息
	 * @param SummerVacation
	 * @return
	 */
	public Integer do_updateSummerVacation(SummerVacation summerVacation);
	
	/**
	 * 分页查询出暑假班学员
	 * @param start
	 * @param count
	 * @return
	 */
	public List<SummerVacation> getSummerVacationByInfo(Integer start,Integer count);
	
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 */
	public SummerVacation getSummerVacationByID(Integer id);
	
	/**
	 * 查询出总行数
	 * @return
	 */
	public Integer getSummerVacationForCount();
	
	
}
