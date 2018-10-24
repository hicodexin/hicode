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
	
	
	public List<Auditions> getAuditionsAll(Integer start, Integer count);
	
	
}
