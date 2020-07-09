package com.hicode.oa.service;

import java.util.List;
import java.util.Map;

import com.hicode.oa.tool.Adviser;

/**
 * 顾问接口
 * @author xinpeixiang
 *
 */
public interface AdviserService {
	
	public Adviser getAdviserByID(String adv_id);
	
	/**
	 * 通过姓名，获取顾问信息
	 * @param adv_name
	 * @return
	 */
	public List<Adviser> getAdviserByName(String adv_name);
	
	public List<Adviser> getAdviserAll(Integer start,Integer count);
	
	/**
	 * 获取所有顾问的ID与name
	 * @return
	 */
	public List<Adviser> getAdvNameAndID();
	
	/**
	 * 获取数量
	 * @return
	 */
	public Integer getAdvisersForCount();
	
	/**
	 * 添加市场人员
	 * @param adviser
	 * @return
	 * @author xinpeixiang 2018-11-9
	 */
	public Integer do_insertAdvisers(Adviser adviser);

	/**
	 * 修改市场人员
	 * @param adviser
	 * @return
	 * @author xinpeixiang 2018-11-26
	 */
	public Integer do_updateAdvisers(Adviser adviser);
	
}
