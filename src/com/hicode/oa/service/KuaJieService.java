package com.hicode.oa.service;

import java.util.List;

import com.hicode.oa.tool.KuaJie;

/**
 * 跨界合作接口
 * 
 * @author XinPeiXiang 2019-11-9
 *
 */
public interface KuaJieService {
	
	/**
	 * 通过ID获取
	 * @param kua_id
	 * @return
	 */
	public KuaJie getKuaJieByID(Integer kua_id);
	
	/**
	 * 分页查询跨界合作数据
	 * @param start
	 * @param count
	 * @return
	 */
	public List<KuaJie> getKuaJieInfo(Integer start, Integer count);
	
	/**
	 * 查询表内数据总条数
	 * @return
	 */
	public Integer getKuaJieForCount();
	
	/**
	 * 添加
	 * @param kuaJie
	 * @return
	 */
	public Integer do_insertKuaJie(KuaJie kuaJie);
	
	
	/**
	 * 修改( 修改：手机号、微信号、当前负责人、意向、备注)
	 * @param kuaJie
	 * @return
	 */
	public Integer do_updateKuaJieSomeColumn(KuaJie kuaJie);
	
	/**
	 * 修改
	 * @param kuaJie
	 * @return
	 */
	public Integer do_updateKuaJie_all(KuaJie kuaJie);
	
	
	
}
