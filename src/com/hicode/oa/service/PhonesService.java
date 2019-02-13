package com.hicode.oa.service;

import java.util.List;
import java.util.Map;

import com.hicode.oa.tool.Phones;

/**
 * 电话量
 * 
 * @author XinPeiXiang 2019-01-26
 *
 */
public interface PhonesService {

	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public Phones getPhonesByID(Integer id);
	
	/**
	 * 查询学校信息
	 * @return
	 */
	public List<Phones> getPhonesByInfo();
	
	/**
	 * 查看该手机号是否存在
	 * @param phone
	 * @return
	 */
	public Integer findThisPhone(String phone_num);
	
	/**
	 * 条件查询
	 * @param map
	 * @return
	 */
	public List<Phones> getPhonesBySomeOption(Map<String, Object> map);
	
	/**
	 * 根据条件获取数量
	 * @param map
	 * @return
	 */
	public Integer getPhonesForCountBySomeOption(Map<String, Object> map);
	
	
	
	/**
	 * 添加电话信息
	 * @param phones
	 * @return
	 */
	public Integer do_insertPhones(Phones phones);
	
	/**
	 * 修改电话信息
	 * @param phones
	 * @return
	 */
	public Integer do_updatePhones(Phones phones);
	
	
	
	
	
}
