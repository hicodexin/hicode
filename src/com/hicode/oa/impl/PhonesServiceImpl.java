package com.hicode.oa.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.PhonesDAO;
import com.hicode.oa.service.PhonesService;
import com.hicode.oa.tool.Phones;

@Service
public class PhonesServiceImpl implements PhonesService{

	@Autowired
	private PhonesDAO phonesDAO;
	
	@Override
	public Phones getPhonesByID(Integer id) {
		// TODO Auto-generated method stub
		return phonesDAO.getPhonesByID(id);
	}

	@Override
	public List<Phones> getPhonesByInfo() {
		// TODO Auto-generated method stub
		return phonesDAO.getPhonesByInfo();
	}

	@Override
	public Integer do_insertPhones(Phones phones) {
		// TODO Auto-generated method stub
		return phonesDAO.do_insertPhones(phones);
	}

	@Override
	public Integer do_updatePhones(Phones phones) {
		// TODO Auto-generated method stub
		return phonesDAO.do_updatePhones(phones);
	}

	@Override
	public List<Phones> getPhonesBySomeOption(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return phonesDAO.getPhonesBySomeOption(map);
	}

	@Override
	public Integer getPhonesForCountBySomeOption(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return phonesDAO.getPhonesForCountBySomeOption(map);
	}

	@Override
	public Integer findThisPhone(String phone_num) {
		// TODO Auto-generated method stub
		return phonesDAO.findThisPhone(phone_num);
	}

}
