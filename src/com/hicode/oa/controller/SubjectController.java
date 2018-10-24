package com.hicode.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hicode.oa.service.SubjectService;
import com.hicode.oa.tool.Subject;

@Controller
@RequestMapping(value = "/subject")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping(value = "/showAll")
	public String showAll(){
		System.out.println("==============ok==============");
		Subject sub = subjectService.getSubjectByID("sub_1001");
		System.out.println(sub.getSub_id()+" : "+ sub.getSub_name());
		System.out.println("==============ok==============");
		List<Subject> all = subjectService.getSubjectAll(null, null);
		for (Subject s : all) {
			System.out.println(s.getSub_id()+" : "+s.getSub_name());
		}
		System.out.println("==============================");
		return "/welcome.html";
	}

	
	
	
}
