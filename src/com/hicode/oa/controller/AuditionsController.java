package com.hicode.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.AuditionsService;
import com.hicode.oa.tool.Auditions;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 试听课详情控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/auditions")
public class AuditionsController {

	@Autowired
	private AuditionsService auditionsService;
	
	@RequestMapping("/to_login")
	public String login(){
		System.out.println("--------------------");
		return "/WEB-INF/VisitorsPage/Auditions.html";
	}
	
	@ResponseBody
	@RequestMapping(value="/showAuditionsByInfo")
	public String showAuditionsByInfo(HttpServletRequest request){
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
		List<Auditions> advs = auditionsService.getAuditionsByInfo(s, e);
		
		JSONArray objs = new JSONArray();
		
		for (Auditions adv1 : advs) {
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getAu_id());
			obj.put("time", adv1.getSt_time());
			obj.put("name", adv1.getSt_name());
			obj.put("sex", adv1.getSt_sex());
			obj.put("classinfo", adv1.getSt_class());
			obj.put("tea_name", adv1.getTeacher().getT_name());
			obj.put("adv_name", adv1.getAdviser().getAdv_name());
			obj.put("beizhu", adv1.getRemarks());
			
			System.out.println(adv1.getAu_id()+" : "+adv1.getSt_name()
								+" : "+adv1.getTeacher().getT_name()
								+" : "+adv1.getAdviser().getAdv_name() );
			
			
			objs.add(obj);
			
		}
		
		System.out.println(objs.size());
		JSONObject obj_arr = new JSONObject();
		obj_arr.put("list_advs", objs);
		return obj_arr.toString();
	}
	
	@RequestMapping(value="/showAuditions")
	public String showAuditions(){
		System.out.println("==========ok============");
		Auditions adv = auditionsService.getAuditionsByID(new Integer(2));
		System.out.println(adv.getAu_id()+" : "+adv.getSt_name() );
		System.out.println(adv.getTeacher().getT_name()+" : "+adv.getAdviser().getAdv_name() );
		List<Auditions> advs = auditionsService.getAuditionsByInfo(0, 30);
		System.out.println("==========ok1============");
		for (Auditions adv1 : advs) {
			System.out.println(adv1.getAu_id()+" : "+adv1.getSt_name());
		}
		
		return "/welcome.html";
	}
	
	
	
}
