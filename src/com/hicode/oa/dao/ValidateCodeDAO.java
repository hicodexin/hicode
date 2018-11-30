package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.ValidateCode;

/**
 * 验证码DAO
 * @author xinpeixiang 2018-11-30
 *
 */
@Repository
public class ValidateCodeDAO  extends SysDAO{
	
	@Autowired
	public ValidateCodeDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public Integer do_insertValidateCode(ValidateCode validateCode) {
		// TODO Auto-generated method stub
		return insert(nameSpace+"do_insertValidateCode", validateCode);
	}

	public List<ValidateCode> getValidateCodeInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace+"getValidateCodeInfo", map);
	}
	
	
	
	
	
	
	

}
