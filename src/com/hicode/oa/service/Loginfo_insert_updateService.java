package com.hicode.oa.service;

import com.hicode.oa.tool.Loginfo_insert_update;

/**
 *	添加和修改日志
 * 
 * @author XinPeiXiang 2019-11-08
 * 
 */
public interface Loginfo_insert_updateService {
	
	/**
	 * 添加日志
	 * @param Loginfo_insert_update
	 * @return
	 */
	public Integer do_insertLogInfo(Loginfo_insert_update logInfo);

}
