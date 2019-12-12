package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.KuaJieDAO;
import com.hicode.oa.service.KuaJieService;
import com.hicode.oa.tool.KuaJie;

@Service
public class KuaJieServiceImpl implements KuaJieService{
	
	@Autowired
	private KuaJieDAO kuajieDAO;
	
	@Override
	public KuaJie getKuaJieByID(Integer kua_id) {
		// TODO Auto-generated method stub
		return kuajieDAO.getKuaJieByID(kua_id);
	}

	@Override
	public List<KuaJie> getKuaJieInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		if(null == start){
			start = 0;
		}
		if(null == count){
			count = 20;
		}
		return kuajieDAO.getKuaJieInfo(start, count);
	}

	@Override
	public Integer getKuaJieForCount() {
		// TODO Auto-generated method stub
		return kuajieDAO.getKuaJieForCount();
	}

	@Override
	public Integer do_insertKuaJie(KuaJie kuaJie) {
		// TODO Auto-generated method stub
		return kuajieDAO.do_insertKuaJie(kuaJie);
	}

	@Override
	public Integer do_updateKuaJieSomeColumn(KuaJie kuaJie) {
		// TODO Auto-generated method stub
		return kuajieDAO.do_updateKuaJieSomeColumn(kuaJie);
	}

	@Override
	public Integer do_updateKuaJie_all(KuaJie kuaJie) {
		// TODO Auto-generated method stub
		return kuajieDAO.do_updateKuaJie_all(kuaJie);
	}

	@Override
	public List<KuaJie> getKuaJieNameAndID() {
		// TODO Auto-generated method stub
		return kuajieDAO.getKuaJieNameAndID();
	}

}
