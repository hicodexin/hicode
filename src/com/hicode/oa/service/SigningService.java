package com.hicode.oa.service;

import java.util.List;
import java.util.Map;

import com.hicode.oa.tool.Signing;

/**
 * 跟单详情
 * @author XinPeiXiang 2019-05-09
 *
 */
public interface SigningService {
	
	/**
	 * 通过ID获取该学员的跟单情况
	 * @param sig_id 跟单ID
	 * @return
	 */
	public Signing getSigningByID(Integer sig_id);
	
	public List<Signing> getSigningAll(Integer start, Integer count);
	
	/**
	 * 通过Id 获取该学员的跟进历史记录 
	 * @param sig_id
	 * @return
	 */
	public String getSigningHistoryBy_ID(Integer sig_id);

	/**
	 * 通过试听学员的id查看是否存在该学生信息
	 * @param au_id
	 * @return
	 */
	public Integer getSigningBy_AuditionsID(Integer au_id);
	
	/**
	 * 查询表内数据总条数
	 * @return
	 */
	public Integer getSigningForCount();
	
	/**
	 * 添加新的学员跟进信息 
	 * @param signing
	 * @return
	 */
	public Integer do_insertSigning(Signing signing);

	public Integer do_updateSigning(Signing signing);
	
	/**
	 * 修改学生的跟进顾问
	 * @param adv_id
	 * @return
	 */
	public Integer do_updateAdviserForSigning(Signing signing);
	
	/**
	 *  修改交接记录 
	 * @param signing
	 * @return
	 */
	public Integer do_updateSigningHistory(Signing signing);

	/**
	 * 根据条件查询
	 * @param map
	 * @return
	 */
	public List<Signing> getSigningBySomeOption(Map<String, Object> map);

	/**
	 * 根据条件》》》获取条数
	 * @param map
	 * @return
	 */
	public Integer getSigningForCountBySomeOption(Map<String, Object> map);
	
	
	
}
