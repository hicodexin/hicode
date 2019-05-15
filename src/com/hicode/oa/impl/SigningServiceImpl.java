package com.hicode.oa.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.SigningDAO;
import com.hicode.oa.service.SigningService;
import com.hicode.oa.tool.Signing;

/**
 * 跟单详情
 * @author XinPeiXiang 2019-05-09
 *
 */
@Service
public class SigningServiceImpl implements SigningService{
	
	
	@Autowired
	private SigningDAO signingDAO;

	@Override
	public Signing getSigningByID(Integer sig_id) {
		// TODO Auto-generated method stub
		return signingDAO.getSigningByID(sig_id);
	}

	@Override
	public List<Signing> getSigningAll(Integer start, Integer count) {
		// TODO Auto-generated method stub
		return signingDAO.getSigningAll(start, count);
	}

	@Override
	public Integer getSigningBy_AuditionsID(Integer au_id) {
		// TODO Auto-generated method stub
		return signingDAO.getSigningBy_AuditionsID(au_id);
	}
	
	@Override
	public Integer getSigningForCount() {
		// TODO Auto-generated method stub
		return signingDAO.getSigningForCount();
	}

	@Override
	public Integer do_insertSigning(Signing signing) {
		// TODO Auto-generated method stub
		return signingDAO.do_insertSigning(signing);
	}

	@Override
	public Integer do_updateSigning(Signing signing) {
		// TODO Auto-generated method stub
		return signingDAO.do_updateSigning(signing);
	}

	@Override
	public Integer do_updateSigningHistory(Signing signing) {
		// TODO Auto-generated method stub
		return signingDAO.do_updateSigningHistory(signing);
	}

	@Override
	public List<Signing> getSigningBySomeOption(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return signingDAO.getSigningBySomeOption(map);
	}

	@Override
	public Integer getSigningForCountBySomeOption(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return signingDAO.getSigningForCountBySomeOption(map);
	}

	
}
