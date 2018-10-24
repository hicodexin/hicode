package com.hicode.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hicode.oa.model.ModelService;

@Controller
@RequestMapping(value = "/sys")
public class PersonController {
	
	@Autowired
	private ModelService ModelService;

	@RequestMapping(value = "/login")
	public String logAction(){
		
		
		System.out.println("log....................ok"+ModelService);
		
//		System.out.println("log....ok"+ModelService.getTeacherByID("t_1003"));
		return "/welcome.html";
		
	}
}
