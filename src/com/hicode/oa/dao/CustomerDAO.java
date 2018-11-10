package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.Customer;

/**
 * 签约客户信息
 * @author Administrator
 *
 */
@Repository
public class CustomerDAO extends SysDAO{

	@Autowired
	public CustomerDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}
	public Customer getCustomerByID(Integer er_id){
		return selectOne(nameSpace+"getCustomerByID", er_id);
	}
	
	public List<Customer> getCustomerAll(Integer start, Integer count) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace+"getCustomerAll", map);
	
	}
	public Integer getCustomerForCount() {
		// TODO Auto-generated method stub
		return selectOne(nameSpace+"getCustomerForCount");
	}

}
