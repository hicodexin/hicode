package com.hicode.oa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	/**
	 * 分页展示市场人员信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/showAdviserByInfo")
	public String showAdviserByInfo(HttpServletRequest request){
		
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
			all_num = adviserService.getAdvisersForCount();
			
			if(all_num != null){
				all_num = (all_num%10==0)?(all_num/10):(all_num/10+1);
			}
			
		}
		List<Adviser> advs = adviserService.getAdviserAll(start, num);
		
		JSONArray objs = new JSONArray();
		
		for (Adviser adv1 : advs) {
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getAdv_id());
			obj.put("name", adv1.getAdv_name());
			obj.put("sex", adv1.getAdv_sex());
			obj.put("title", adv1.getTitle());
			obj.put("onthejob", adv1.getIf_Onthejob());
			obj.put("opentime", adv1.getTime_creatDate());
			obj.put("endtime", adv1.getTime_endDate());
			obj.put("title_updatetime", adv1.getTitle_updatetime());
			objs.add(obj);
		}
		
		JSONObject obj_arr = new JSONObject();
		obj_arr.put("list_advs", objs);
		
		if(page.equals("1")){
			obj_arr.put("all_num", all_num);
		}
		return obj_arr.toString();
	}
	
	/**
	 * 添加市场人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/do_insertAdviser")
	public String do_insertAdviser(HttpServletRequest request){
		String adv_name = request.getParameter("userName");
		String adv_sex = request.getParameter("sex");
		String title = request.getParameter("title");
		switch (title) {
			case "1":title ="课程顾问"; break;
			case "2":title ="地推专员"; break;
			case "3":title ="TMK专员"; break;
			case "4":title ="TMK主管"; break;
			case "5":title ="顾问主管"; break;
			case "6":title ="地推主管"; break;
			case "7":title ="市场总监"; break;
		}
		String if_Onthejob = request.getParameter("if_Onthejob");
		String time_creatDate = request.getParameter("time_creatDate");
		String time_endDate = request.getParameter("time_endDate");
		String title_updatetime = request.getParameter("title_updatetime");
		
		Adviser adviser = new Adviser();
		adviser.setAdv_name(adv_name);
		adviser.setAdv_sex(Integer.valueOf(adv_sex));
		adviser.setTitle(title);
		adviser.setIf_Onthejob(Integer.valueOf(if_Onthejob));
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sf.parse(time_creatDate);
			adviser.setTime_creatDate(d);
			if(time_endDate != null && time_endDate != ""){
				d = sf.parse(time_endDate);
				adviser.setTime_endDate(d);
			}
			if(title_updatetime != null && title_updatetime != ""){
				d = sf.parse(title_updatetime);
				adviser.setTitle_updatetime(d);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer count =adviserService.do_insertAdvisers(adviser);
		JSONObject obj_arr = new JSONObject();
		if(count>0){
			obj_arr.put("list_advs", "ok");
		}
		
		return obj_arr.toString();
	}
	
	/**
	 * 展示顾问
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/showAdviser")
	public String showAdviser(){
		
		List<Adviser> advs = adviserService.getAdvNameAndID();
		JSONArray jsay = new JSONArray();
		
		for (Adviser adv1 : advs) {
			if(adv1.getTime_endDate() != null){
				continue;
			}
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getAdv_id());
			obj.put("name", adv1.getAdv_name() );
			
			jsay.add(obj);
		}
		
		return jsay.toString();
	}
	
	/**
	 * 修改市场人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/do_updateAdviser")
	public String do_updateAdviser(HttpServletRequest request){
		String id = request.getParameter("id");
		String adv_name = request.getParameter("userName");
		String adv_sex = request.getParameter("sex");
		String title = request.getParameter("title");
		switch (title) {
			case "1":title ="课程顾问"; break;
			case "2":title ="地推专员"; break;
			case "3":title ="TMK专员"; break;
			case "4":title ="TMK主管"; break;
			case "5":title ="顾问主管"; break;
			case "6":title ="地推主管"; break;
			case "7":title ="市场总监"; break;
		}
		
		String if_Onthejob = request.getParameter("if_Onthejob");
		String time_creatDate = request.getParameter("time_creatDate");
		String time_endDate = request.getParameter("time_endDate");
		String title_updatetime = request.getParameter("title_updatetime");
		
		Adviser adviser = new Adviser();
		adviser.setAdv_id(id);
		adviser.setAdv_name(adv_name);
		adviser.setAdv_sex(Integer.valueOf(adv_sex));
		adviser.setTitle(title);
		adviser.setIf_Onthejob(Integer.valueOf(if_Onthejob));
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sf.parse(time_creatDate);
			adviser.setTime_creatDate(d);
			if(time_endDate != null && time_endDate != ""){
				d = sf.parse(time_endDate);
				adviser.setTime_endDate(d);
			}
			if(title_updatetime != null && title_updatetime != ""){
				d = sf.parse(title_updatetime);
				adviser.setTitle_updatetime(d);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer count =adviserService.do_updateAdvisers(adviser);
		JSONObject obj_arr = new JSONObject();
		if(count>0){
			obj_arr.put("list_advs", "ok");
		}
		
		return obj_arr.toString();
	}
	
	
	
}
