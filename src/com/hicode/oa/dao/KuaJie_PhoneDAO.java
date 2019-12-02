package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.KuaJie_Phone;

/**
 * 名单置换
 * 
 * @author XinPeiXiang
 * @date 2019-11-24
 * 
 */
@Repository
public class KuaJie_PhoneDAO extends SysDAO {

	@Autowired
	public KuaJie_PhoneDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public KuaJie_Phone getKuaJie_PhoneByID(Integer md_id) {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getKuaJie_PhoneByID",md_id);
	}

	public List<KuaJie_Phone> getKuaJie_PhoneInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace+"getKuaJie_PhoneInfo", map);
	}

	public Integer getKuaJie_PhoneForCount() {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getKuaJie_PhoneForCount");
	}

	public Integer do_insertKuaJie_Phone(KuaJie_Phone kuaJie_Phone) {
		// TODO Auto-generated method stub
		return insert(nameSpace+"do_insertKuaJie_Phone", kuaJie_Phone);
	}

	public Integer do_updateKuaJie_Phone_all(KuaJie_Phone kuaJie_Phone) {
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updateKuaJie_Phone_all", kuaJie_Phone);
	}
	
	
	

}






