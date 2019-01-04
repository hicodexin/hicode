package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.SummerVacation;

/**
 *  暑假班接口
 * @author XinPeiXiang 2018-01-03
 *
 */
@Repository
public class SummerVacationDAO extends SysDAO{

	@Autowired
	public SummerVacationDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public Integer do_insertSummerVacation(SummerVacation summerVacation) {
		// TODO Auto-generated method stub
		return insert(nameSpace+"do_insertSummerVacation", summerVacation);
	}

	public Integer do_updateSummerVacation(SummerVacation summerVacation) {
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updateSummerVacation", summerVacation);
	}

	public List<SummerVacation> getSummerVacationByInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace+"getSummerVacationByInfo", map);
	}

	public SummerVacation getSummerVacationByID(Integer id) {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getSummerVacationByID", id);
	}

	public Integer getSummerVacationForCount() {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getSummerVacationForCount");
	}
}
