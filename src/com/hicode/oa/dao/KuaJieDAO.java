package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.KuaJie;

@Repository
public class KuaJieDAO extends SysDAO{
	
	@Autowired
	public KuaJieDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public KuaJie getKuaJieByID(Integer kua_id) {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getKuaJieByID",kua_id);
	}

	public List<KuaJie> getKuaJieInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace+"getKuaJieInfo", map);
	}

	public Integer getKuaJieForCount() {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getKuaJieForCount");
	}

	public Integer do_insertKuaJie(KuaJie kuaJie) {
		// TODO Auto-generated method stub
		return insert(nameSpace+"do_insertKuaJie", kuaJie);
	}

	public Integer do_updateKuaJieSomeColumn(KuaJie kuaJie) {
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updateKuaJieSomeColumn", kuaJie);
	}
	public Integer do_updateKuaJie_all(KuaJie kuaJie){
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updateKuaJie_all", kuaJie);
	}

	public List<KuaJie> getKuaJieNameAndID() {
		// TODO Auto-generated method stub
		return selectList(nameSpace+"getKuaJieNameAndID");
	}
}
