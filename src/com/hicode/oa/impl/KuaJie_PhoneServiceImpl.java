package com.hicode.oa.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.service.KuaJie_PhoneService;
import com.hicode.oa.tool.KuaJie_Phone;
import com.hicode.oa.dao.KuaJie_PhoneDAO;
/**
 * 名单置换
 * 
 * @author XinPeiXiang
 * @date 2019-11-24
 * 
 */
@Service
public class KuaJie_PhoneServiceImpl implements KuaJie_PhoneService{

	@Autowired
	private KuaJie_PhoneDAO kuaJie_PhoneDAO;
	
	@Override
	public KuaJie_Phone getKuaJie_PhoneByID(Integer md_id) {
		// TODO Auto-generated method stub
		return kuaJie_PhoneDAO.getKuaJie_PhoneByID(md_id);
	}

	@Override
	public List<KuaJie_Phone> getKuaJie_PhoneInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		if(null == start){
			start = 0;
		}
		if(null == count){
			count = 20;
		}
		return kuaJie_PhoneDAO.getKuaJie_PhoneInfo(start, count);
	}

	@Override
	public Integer getKuaJie_PhoneForCount() {
		// TODO Auto-generated method stub
		return kuaJie_PhoneDAO.getKuaJie_PhoneForCount();
	}

	@Override
	public Integer do_insertKuaJie_Phone(KuaJie_Phone kuaJie_Phone) {
		// TODO Auto-generated method stub
		return kuaJie_PhoneDAO.do_insertKuaJie_Phone(kuaJie_Phone);
	}

	@Override
	public Integer do_updateKuaJie_Phone_all(KuaJie_Phone kuaJie_Phone) {
		// TODO Auto-generated method stub
		return kuaJie_PhoneDAO.do_updateKuaJie_Phone_all(kuaJie_Phone);
	}

	@Override
	public Integer do_updateKuaJie_PhoneSomeColumn(KuaJie_Phone kuaJie_Phone) {
		// TODO Auto-generated method stub
		return kuaJie_PhoneDAO.do_updateKuaJie_PhoneSomeColumn(kuaJie_Phone);
	}

	@Override
	public List<KuaJie_Phone> getKuaJie_PhoneBySomeOption(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return kuaJie_PhoneDAO.getKuaJie_PhoneBySomeOption(map);
	}

	@Override
	public Integer getKuaJie_PhoneForCountBySomeOption(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return kuaJie_PhoneDAO.getKuaJie_PhoneForCountBySomeOption(map);
	}

}
