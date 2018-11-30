package com.hicode.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hicode.oa.service.ValidateCodeService;

/**
 * 获取验证码
 * @author xinpeixiang 2018-11-30
 *
 */
@Controller
@RequestMapping("/validateCode")
public class ValidateCodeController {

	@Autowired
	private ValidateCodeService validateCodeService;
	
	
	public String do_insertValidateCode(){
		
		return "";
	}
	
	
	
	
	
}



















