package com.hicode.oa.service;

import java.util.Date;
import java.util.List;

import com.hicode.oa.tool.LogInfo;

/**
 * 登录日志接口
 * @author XinPeiXiang 2018-12-2
 *
 */
public interface LogInfoService {

	/**
	 * 添加日志
	 * @param logInfo
	 * @return
	 */
	public Integer do_insertLogInfo(LogInfo logInfo);

	/**
	 * 根据时间查询 日志记录
	 * @param start
	 * @param end
	 * @return
	 */
	public List<LogInfo> getLogInfoByTime(Date start, Date end);

	/**
	 * 根据用户ID查询日志记录
	 * @param userId
	 * @return
	 */
	public List<LogInfo> getLogInfoByUserID(String userId);

	/**
	 * 根据IP查询日志记录
	 * @param IP
	 * @return
	 */
	public List<LogInfo> getLogInfoByIP(String IP);

	/**
	 * 根据登录状态查询日志记录(是否登录成功)
	 * @param success
	 * @return
	 */
	public List<LogInfo> getLogInfoByState(Integer success);
}
