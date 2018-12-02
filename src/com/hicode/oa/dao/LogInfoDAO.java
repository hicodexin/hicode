package com.hicode.oa.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.LogInfo;


@Repository
public class LogInfoDAO extends SysDAO{

	@Autowired
	public LogInfoDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}
	
	public Integer do_insertLogInfo(LogInfo logInfo){
		//添加登录时间
		logInfo.setLog_time(new Date());
		return insert(nameSpace+"do_insertLogInfo", logInfo);
	}
	
	public List<LogInfo> getLogInfoByTime(Date start,Date end){
		Map<String, Date> map = new HashMap<String, Date>();
		map.put("start", start);
		map.put("end", end);
		
		return selectList(nameSpace+"getLogInfoByTime", map);
	}
	
	public List<LogInfo> getLogInfoByUserID(String userId) {
		
		return selectList(nameSpace+"getLogInfoByUserID", userId);
		
	}
	
	public List<LogInfo> getLogInfoByIP(String IP){
		
		return selectList(nameSpace+"getLogInfoByIP", IP);
	}
	
	public List<LogInfo> getLogInfoByState(Integer success){
		return selectList(nameSpace+"getLogInfoByState", success);
	}

}
