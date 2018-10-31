package com.hicode.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.SubjectService;
import com.hicode.oa.tool.Subject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/subject")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping("/to_login")
	public String login(){
		System.out.println("-------subject-------------");
		return "/WEB-INF/VisitorsPage/Subject.html";
	}
	
	@ResponseBody
	@RequestMapping(value="/showSubjectByInfo")
	public String showSubjectByInfo(HttpServletRequest request){
		String start = request.getParameter("start");
		String count = request.getParameter("count");
		int s = 0;
		int e = 10;
		if(start != null){
			s = Integer.valueOf(start);
		}
		if(count != null){
			e = Integer.valueOf(count);
		}
		List<Subject> subs = subjectService.getSubjectAll(s, e);
		
		JSONArray objs = new JSONArray();
		
		for (Subject adv1 : subs) {
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getSub_id());
			obj.put("name", adv1.getSub_name());
			obj.put("xiaxian", adv1.getIf_downline());
			objs.add(obj);
		}
		
		JSONObject obj_arr = new JSONObject();
		obj_arr.put("list_advs", objs);
		
		return obj_arr.toString();
	}
	
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
