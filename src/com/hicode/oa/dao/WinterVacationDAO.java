package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.WinterVacation;

/**
 *	寒假班接口
 *
 * @author XinPeiXiang 2018-12-22
 */
@Repository
public class WinterVacationDAO extends SysDAO{

	@Autowired
	public WinterVacationDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public Integer do_insertWinterVacation(WinterVacation winterVacation) {
		// TODO Auto-generated method stub
		return insert(nameSpace+"do_insertWinterVacation", winterVacation);
	}

	public Integer do_updateWinterVacation(WinterVacation winterVacation) {
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updateWinterVacation", winterVacation);
	}

	public List<WinterVacation> getgetWinterVacationByInfo(Integer start,Integer count){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace+"getgetWinterVacationByInfo", map);
	}

	public WinterVacation getWinterVacationByID(Integer id) {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getWinterVacationByID", id);
	}

	public Integer getWinterVacationForCount() {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getWinterVacationForCount");
	}

}
