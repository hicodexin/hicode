package com.hicode.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.SubjectService;
import com.hicode.oa.tool.Adviser;
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
		//页码
		String page = request.getParameter("page");
		//开始数字
		Integer start = 0;
		//每页显示条数
		Integer num = 10;
		
		if(page != null){
			start = (Integer.valueOf(page)-1)*10;
		}
		
		Integer all_num = null;
		if(page.equals("1")){
			all_num = subjectService.getSubjectForCount();
			
			if(all_num != null){
				all_num = (all_num%10==0)?(all_num/10):(all_num/10+1);
			}
			
		}
		List<Subject> subs = subjectService.getSubjectAll(start, num);
		
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
		
		if(page.equals("1")){
			obj_arr.put("all_num", all_num);
		}
		return obj_arr.toString();
		
	}
	
	@ResponseBody
	@RequestMapping("/do_insertSubject")
	public String do_insertSubject(HttpServletRequest request){
		String name = request.getParameter("userName");
		String if_downline = request.getParameter("if_Onthejob");
		
		Subject subject = new Subject();
		subject.setSub_name(name);
		subject.setIf_downline(Integer.valueOf(if_downline));
		
		Integer count =subjectService.do_insertSubject(subject);
		JSONObject obj_arr = new JSONObject();
		if(count>0){
			obj_arr.put("list_advs", "ok");
		}
		
		return obj_arr.toString();
	}
	
	
	@ResponseBody
	@RequestMapping("/showSubject")
	public String showSubject(){
		
		List<Subject> advs = subjectService.getSubjectAll(0, 200);
		JSONArray jsay = new JSONArray();
		
		for (Subject adv1 : advs) {
			if(adv1.getIf_downline() == 1){
				continue;
			}
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getSub_id());
			obj.put("name", adv1.getSub_name() );
			
			jsay.add(obj);
		}
		
		return jsay.toString();
	}
	
	@ResponseBody
	@RequestMapping("/do_updateSubject")
	public String do_updateSubject(HttpServletRequest request){
		
		String subid = request.getParameter("id");
		String name = request.getParameter("userName");
		String if_downline = request.getParameter("if_Onthejob");
		
		Subject subject = new Subject();
		subject.setSub_id(subid);
		subject.setSub_name(name);
		subject.setIf_downline(Integer.valueOf(if_downline));
		
		Integer count =subjectService.do_updateSubject(subject);
		JSONObject obj_arr = new JSONObject();
		if(count>0){
			obj_arr.put("list_advs", "ok");
		}
		
		return obj_arr.toString();
	}
	

	
	
	
}
