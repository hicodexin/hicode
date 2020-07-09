package com.hicode.oa.service;

import java.util.List;
import java.util.Map;

import com.hicode.oa.tool.KuaJie_Phone;

/**
 * 名单置换
 * 
 * @author XinPeiXiang
 * @date 2019-11-23
 * 
 */
public interface KuaJie_PhoneService {

	/**
	 * 通过ID获取
	 * @param md_id
	 * @return
	 */
	public KuaJie_Phone getKuaJie_PhoneByID(Integer md_id);
	
	/**
	 * 分页查询名单置换数据
	 * @param start
	 * @param count
	 * @return
	 */
	public List<KuaJie_Phone> getKuaJie_PhoneInfo(Integer start, Integer count);
	
	
	/**
	 * 条件查询
	 * @param map
	 * @return
	 */
	public List<KuaJie_Phone> getKuaJie_PhoneBySomeOption(Map<String, Object> map);
	
	/**
	 * 获取条件查询的条数
	 * @param map
	 * @return
	 */
	public Integer getKuaJie_PhoneForCountBySomeOption(Map<String, Object> map);
	
	/**
	 * 查询表内数据总条数
	 * @return
	 */
	public Integer getKuaJie_PhoneForCount();
	
	/**
	 * 添加
	 * @param kuaJie_Phone
	 * @return
	 */
	public Integer do_insertKuaJie_Phone(KuaJie_Phone kuaJie_Phone);
	
	
	/**
	 * 修改( 修改：交换数量、年龄段、接通率、洽谈顾问、备注)
	 * @param kuaJie_Phone
	 * @return
	 */
	public Integer do_updateKuaJie_PhoneSomeColumn(KuaJie_Phone kuaJie_Phone);
	
	/**
	 * 修改
	 * @param kuaJie_Phone
	 * @return
	 */
	public Integer do_updateKuaJie_Phone_all(KuaJie_Phone kuaJie_Phone);

	
	
	
}
