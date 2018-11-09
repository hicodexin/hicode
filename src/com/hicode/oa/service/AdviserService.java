package com.hicode.oa.service;

import java.util.List;
import java.util.Map;

import com.hicode.oa.tool.Adviser;

/**
 * 顾问接口
 * @author Administrator
 *
 */
public interface AdviserService {
	
	public Adviser getAdviserByID(String adv_id);
	
	public List<Adviser> getAdviserAll(Integer start,Integer count);
	
	/**
	 * 获取所有顾问的ID与name
	 * @return
	 */
	public List<Adviser> getAdvNameAndID();
	
}
