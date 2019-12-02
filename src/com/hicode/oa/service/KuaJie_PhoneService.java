package com.hicode.oa.service;

import java.util.List;

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
	 * 修改( 修改：手机号、微信号、当前负责人、意向、备注)
	 * @param kuaJie_Phone
	 * @return
	 */
//	public Integer do_updateKuaJie_PhoneSomeColumn(KuaJie_Phone kuaJie_Phone);
	
	/**
	 * 修改
	 * @param kuaJie_Phone
	 * @return
	 */
	public Integer do_updateKuaJie_Phone_all(KuaJie_Phone kuaJie_Phone);
	
	
}
