package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.WinterVacation;

/**
 * 寒假班接口
 * @author XinPeiXiang 2018-12-22
 *
 */
public interface WinterVacationService {

	/**
	 * 添加寒假班学员
	 * @param winterVacation
	 * @return
	 */
	public Integer do_insertWinterVacation(WinterVacation winterVacation);
	
	/**
	 * 修改寒假班学员信息
	 * @param winterVacation
	 * @return
	 */
	public Integer do_updateWinterVacation(WinterVacation winterVacation);
	
	/**
	 * 分页查询出寒假班学员
	 * @param start
	 * @param count
	 * @return
	 */
	public List<WinterVacation> getWinterVacationByInfo(Integer start,Integer count);
	
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 */
	public WinterVacation getWinterVacationByID(Integer id);
	
	/**
	 * 查询出总行数
	 * @return
	 */
	public Integer getWinterVacationForCount();
	
	
	
	
	
	
	
	
}
