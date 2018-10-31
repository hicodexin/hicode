package com.hicode.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.AdviserService;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Auditions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 顾问控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/adviser")
public class AdviserController {

	@Autowired
	private AdviserService adviserService;
	
	@RequestMapping("/to_login")
	public String login(){
		System.out.println("----------to_login----------");
		return "/WEB-INF/VisitorsPage/Adviser.html";
	}
	
	@ResponseBody
	@RequestMapping(value="/showAdviserByInfo")
	public String showAdviserByInfo(HttpServletRequest request){
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
		List<Adviser> advs = adviserService.getAdviserAll(s, e);
		
		JSONArray objs = new JSONArray();
		
		for (Adviser adv1 : advs) {
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getAdv_id());
			obj.put("name", adv1.getAdv_name());
			obj.put("sex", adv1.getAdv_sex());
			obj.put("onthejob", adv1.getIf_Onthejob());
			obj.put("opentime", adv1.getTime_creatDate());
			obj.put("endtime", adv1.getTime_endDate());
			objs.add(obj);
		}
		
		JSONObject obj_arr = new JSONObject();
		obj_arr.put("list_advs", objs);
		
		return obj_arr.toString();
	}
	
	@RequestMapping("/showAdviser")
	public String showAdviser(){
		System.out.println("==========ok============");
		Adviser adv = adviserService.getAdviserByID("adv_1001");
		System.out.println(adv.getAdv_id()+" : "+adv.getAdv_name() );
		
		List<Adviser> advs = adviserService.getAdviserAll(0, 4);
		System.out.println("==========ok1============");
		for (Adviser adv1 : advs) {
			System.out.println(adv1.getAdv_id()+" : "+adv1.getAdv_name() );
		}
		
		return "/welcome.html";
	}
	
	
	
}
