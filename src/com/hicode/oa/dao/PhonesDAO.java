package com.hicode.oa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.Phones;

@Repository
public class PhonesDAO extends SysDAO{

	@Autowired
	public PhonesDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public Phones getPhonesByID(Integer id) {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getPhonesByID",id);
	}

	public List<Phones> getPhonesByInfo() {
		// TODO Auto-generated method stub
		return selectList(nameSpace+"getPhonesByInfo");
	}

	public Integer do_insertPhones(Phones phones) {
		// TODO Auto-generated method stub
		return insert(nameSpace+"do_insertPhones",phones);
	}

	public Integer do_updatePhones(Phones phones) {
		// TODO Auto-generated method stub
		return update(nameSpace+"do_updatePhones",phones);
	}

	public List<Phones> getPhonesBySomeOption(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList(nameSpace+"getPhonesBySomeOption",map);
	}

	public Integer getPhonesForCountBySomeOption(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getPhonesForCountBySomeOption",map);
	}

	public Integer findThisPhone(String phone_num) {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"findThisPhone",phone_num);
	}

}
