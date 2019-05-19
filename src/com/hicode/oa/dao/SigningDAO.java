package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.Signing;

/**
 * 跟单详情
 * @author XinPeiXiang 2019-05-09
 *
 */
@Repository
public class SigningDAO extends SysDAO{

	@Autowired
	public SigningDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public Signing getSigningByID(Integer sig_id) {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getSigningByID", sig_id);
	}

	public List<Signing> getSigningAll(Integer start, Integer count) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace+"getSigningAll", map);
	}

	public Integer getSigningBy_AuditionsID(Integer au_id) {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getSigningBy_AuditionsID",au_id);
	}

	public Integer getSigningForCount() {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getSigningForCount");
	}

	public Integer do_insertSigning(Signing signing) {
		// TODO Auto-generated method stub
		return insert(nameSpace+"do_insertSigning",signing);
	}

	public Integer do_updateSigning(Signing signing) {
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updateSigning", signing);
	}

	public Integer do_updateSigningHistory(Signing signing) {
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updateSigningHistory",signing);
	}

	public List<Signing> getSigningBySomeOption(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList(nameSpace+"getSigningBySomeOption", map);
	}

	public Integer getSigningForCountBySomeOption(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getSigningForCountBySomeOption", map);
	}

	public Integer do_updateAdviserForSigning(Signing signing) {
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updateAdviserForSigning",signing);
	}

	public String getSigningHistoryBy_ID(Integer sig_id) {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getSigningHistoryBy_ID", sig_id);
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
