package com.hicode.oa.service;

import java.util.List;
import java.util.Map;

import com.hicode.oa.tool.Auditions;

/**
 * 试听课详情
 * @author Administrator
 *
 */
public interface AuditionsService {

	
	public Auditions getAuditionsByID(Integer au_id);
	
	/**
	 * 获取试听课详情
	 * @param start 开始
	 * @param count 长度
	 * @return
	 */
	public List<Auditions> getAuditionsByInfo(Integer start, Integer count);
	
	/**
	 * 添加试听学员
	 * @param auditions
	 * @return
	 */
	public Integer do_insertAuditions(Auditions auditions);
	
	/**
	 * 获取数量
	 * @return
	 */
	public Integer getAuditionsForCount();
	
	/**
	 * 获取
	 * @return
	 */
	public List<Auditions> getAudNameAndID();

	public Integer do_updateAuditions(Auditions auditions);
	
	/**
	 * 条件查询
	 * @param map
	 * @return
	 */
	public List<Auditions> getAuditionsBySomeOption(Map<String, Object> map);
	
	/**
	 * 根据条件获取数量
	 * @param map
	 * @return
	 */
	public Integer getAuditionsForCountBySomeOption(Map<String, Object> map);
	
	
}
