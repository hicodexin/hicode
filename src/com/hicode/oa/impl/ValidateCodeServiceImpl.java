package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.service.ValidateCodeService;
import com.hicode.oa.tool.ValidateCode;
import com.hicode.oa.dao.ValidateCodeDAO;
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService{
	
	@Autowired
	private ValidateCodeDAO validateCodeDAO;
	

	@Override
	public Integer do_insertValidateCode(ValidateCode validateCode) {
		// TODO Auto-generated method stub
		return validateCodeDAO.do_insertValidateCode(validateCode);
	}


	@Override
	public List<ValidateCode> getValidateCodeInfo(Integer start, Integer count) {
		// TODO Auto-generated method stub
		if(null == start){
			start = 0;
		}
		if(null == count){
			count = 20;
		}
		return validateCodeDAO.getValidateCodeInfo(start,count);
	}

}
