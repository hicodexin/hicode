package com.hicode.oa.service;

import java.util.List;

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
	
}
