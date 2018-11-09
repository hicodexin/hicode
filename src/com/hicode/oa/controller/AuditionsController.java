package com.hicode.oa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.AuditionsService;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Auditions;
import com.hicode.oa.tool.Teacher;

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
		System.out.println("-------auditions------------");
		return "/WEB-INF/VisitorsPage/Auditions.html";
	}
	
	@ResponseBody
	@RequestMapping(value="/showAuditionsByInfo")
	public String showAuditionsByInfo(HttpServletRequest request){
		
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
			all_num = auditionsService.getAuditionsForCount();
			
			if(all_num != null){
				all_num = (all_num%10==0)?(all_num/10):(all_num/10+1);
			}
			
		}
		
		List<Auditions> advs = auditionsService.getAuditionsByInfo(start, num);
		
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
	@RequestMapping(value="/do_insertAuditions")
	public String do_insertAuditions(HttpServletRequest request){
		String st_time = request.getParameter("time_creatDate");
		String st_name = request.getParameter("userName");
		String st_sex = request.getParameter("t_sex");
		String st_class = request.getParameter("update_selclass");
		String t_id = request.getParameter("update_selteas");
		String adv_id = request.getParameter("update_seladvs");
		String remarks = request.getParameter("remarks");
		
		Auditions auditions = new Auditions();
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d;
		try {
			d = sf.parse(st_time);
			auditions.setSt_time(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		auditions.setSt_name(st_name);
		auditions.setSt_sex(Integer.valueOf(st_sex));
		auditions.setSt_class(st_class);
		Teacher t = new Teacher();
		t.setT_id(t_id);
		auditions.setTeacher(t);
		Adviser adviser = new Adviser();
		adviser.setAdv_id(adv_id);
		auditions.setAdviser(adviser);
		auditions.setRemarks(remarks);
		
		Integer count = auditionsService.do_insertAuditions(auditions);
		JSONObject obj_arr = new JSONObject();
		if(count>0){
			obj_arr.put("list_advs", "ok");
		}
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
