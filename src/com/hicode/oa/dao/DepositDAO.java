package com.hicode.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hicode.oa.system.SysDAO;
import com.hicode.oa.tool.Deposit;

/**
 * 押金表
 * 
 * @author XinPeiXiang 2018-12-10
 *
 */
@Repository
public class DepositDAO extends SysDAO {

	@Autowired
	public DepositDAO(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}

	public Deposit getDepositByID(Integer dep_id) {

		return selectOne(nameSpace + "getDepositByID", dep_id);
	}

	public List<Deposit> getDepositInfo(Integer start, Integer count) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("count", count);
		return selectList(nameSpace + "getDepositInfo", map);
	}

	public Integer do_insertDeposit(Deposit deposit) {
		return insert(nameSpace + "do_insertDeposit", deposit);
	}

	public Integer do_updateDeposit(Deposit deposit) {
		return update(nameSpace + "do_updateDeposit", deposit);
	}

	public Integer getCustomerForCount() {
		// TODO Auto-generated method stub
		return selectOne(nameSpace + "getCustomerForCount");
	}

}
