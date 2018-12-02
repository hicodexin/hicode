package com.hicode.oa.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.LogInfoDAO;
import com.hicode.oa.service.LogInfoService;
import com.hicode.oa.tool.LogInfo;

@Service
public class LogInfoServiceImpl implements LogInfoService{

	@Autowired
	private LogInfoDAO logInfoDAO;
	
	@Override
	public Integer do_insertLogInfo(LogInfo logInfo) {
		// TODO Auto-generated method stub
		return logInfoDAO.do_insertLogInfo(logInfo);
	}

	@Override
	public List<LogInfo> getLogInfoByTime(Date start, Date end) {
		// TODO Auto-generated method stub
		return logInfoDAO.getLogInfoByTime(start, end);
	}

	@Override
	public List<LogInfo> getLogInfoByUserID(String userId) {
		// TODO Auto-generated method stub
		return logInfoDAO.getLogInfoByUserID(userId);
	}

	@Override
	public List<LogInfo> getLogInfoByIP(String IP) {
		// TODO Auto-generated method stub
		return logInfoDAO.getLogInfoByIP(IP);
	}

	@Override
	public List<LogInfo> getLogInfoByState(Integer success) {
		// TODO Auto-generated method stub
		return logInfoDAO.getLogInfoByState(success);
	}

}
