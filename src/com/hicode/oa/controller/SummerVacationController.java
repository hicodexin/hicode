package com.hicode.oa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.Loginfo_insert_updateService;
import com.hicode.oa.service.SummerVacationService;
import com.hicode.oa.system.FindIP;
import com.hicode.oa.tool.Auditions;
import com.hicode.oa.tool.Loginfo_insert_update;
import com.hicode.oa.tool.School;
import com.hicode.oa.tool.Subject;
import com.hicode.oa.tool.SummerVacation;
import com.hicode.oa.tool.Teacher;
import com.hicode.oa.tool.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 暑假班
 * 
 * @author XinPeiXiang 2018-01-03
 *
 */
@Controller
@RequestMapping("/summerVacation")
public class SummerVacationController {

	@Autowired
	private SummerVacationService summerVacationService;
	
	@Autowired
	private Loginfo_insert_updateService loginfo_insert_updateService;

	@RequestMapping("/to_login")
	public String login(HttpServletRequest request) {
		return "/WEB-INF/ProvisionalPage/shujia.html";
	}

	@ResponseBody
	@RequestMapping(value = "/showSummerVacationByInfo", method = RequestMethod.POST)
	public String showSummerVacationByInfo(HttpServletRequest request) {
		// 页码
		String page = request.getParameter("page");

		// 开始数字
		Integer start = 0;
		// 每页显示条数
		Integer num = 20;

		if (page != null) {
			start = (Integer.valueOf(page) - 1) * 20;
		}

		Integer all_num = null;
		if (page.equals("1")) {
			all_num = summerVacationService.getSummerVacationForCount();

			if (all_num != null) {
				all_num = (all_num % 20 == 0) ? (all_num / 20) : (all_num / 20 + 1);
			}
		}
		List<SummerVacation> advs = summerVacationService.getSummerVacationByInfo(start, num);

		JSONArray objs = new JSONArray();

		for (SummerVacation adv1 : advs) {
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getSm_id());
			obj.put("stu_name", adv1.getAu_name());
			obj.put("school_name", adv1.getSchool().getSch_name());
			obj.put("banji", adv1.getClassinfo_name());
			obj.put("phone", adv1.getPhone());
			obj.put("start_time", adv1.getStart_time());
			obj.put("subject", adv1.getSubject().getSub_name());
			obj.put("teacher", adv1.getTeacher().getT_name());
			obj.put("if_signup", adv1.getIf_signup());
			obj.put("beizhu", adv1.getRemarks());
			objs.add(obj);
		}
		JSONObject obj_arr = new JSONObject();
		obj_arr.put("list_advs", objs);

		if (page.equals("1")) {
			obj_arr.put("all_num", all_num);
		}

		return obj_arr.toString();
	}

	/**
	 * 添加暑假班
	 * @param request
	 * @return
	 * @author XinPeiXiang 2020-07-09
	 */
	@ResponseBody
	@RequestMapping(value = "/do_insertSummerVacation", method = RequestMethod.POST)
	public String do_insertSummerVacation(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		
		JSONObject obj_arr = new JSONObject();
		
		// 添加权限仅限于：管理员；
		if ( obj.getUserType().getType_leibie() != 3 && obj.getUserType().getType_leibie() != 6 ) {
			obj_arr.put("list_advs", "ok1");
			return obj_arr.toString();
		}
		
		String au_name = request.getParameter("userName");
		String stu_school = request.getParameter("stu_school");
		String stu_class = request.getParameter("stu_class");
		String stu_phone = request.getParameter("stu_phone");
		String start_time = request.getParameter("start_time");
		String sub_id = request.getParameter("subject");
		String t_id = request.getParameter("the_teacher");
		String if_signup = request.getParameter("if_signup");
		String remarks = request.getParameter("remarks");

		SummerVacation summerVacation = new SummerVacation();
		
		School school =  new School();
		school.setSch_id(Integer.valueOf(stu_school));
		
		Subject subject = new Subject();
		subject.setSub_id(sub_id);

		Teacher teacher = new Teacher();
		teacher.setT_id(t_id);
		
		summerVacation.setAu_name(au_name);
		summerVacation.setSchool(school);
		summerVacation.setClassinfo_name(stu_class);
		summerVacation.setPhone(stu_phone);
		summerVacation.setSubject(subject);
		summerVacation.setTeacher(teacher);

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sf.parse(start_time);
			summerVacation.setStart_time(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(if_signup != "" && if_signup != null){
			summerVacation.setIf_signup(Integer.valueOf(if_signup));
		}
		
		summerVacation.setRemarks(remarks);
		
		Integer count = summerVacationService.do_insertSummerVacation(summerVacation);
		
		String ip = null;
		
		if (count > 0) {
			Loginfo_insert_update logInfo = new Loginfo_insert_update();
			logInfo.setUserName(obj.getUser_name());
			String xiangQing = obj.getUser_name()+"成功添加了暑假班学员 :  "+au_name;
			logInfo.setXiangQing(xiangQing);
			try{
				ip = FindIP.getIpAddr(request);
			}catch(Exception e){
				System.out.println("未能正常获取IP");
				ip = "未能正常获取IP";
			}finally{
				logInfo.setLog_ip(ip);
				loginfo_insert_updateService.do_insertLogInfo(logInfo);
				obj_arr.put("list_advs", "ok");
			}
			
		}

		return obj_arr.toString();
	}

	/**
	 * 修改暑假班学员
	 * @param request
	 * @return
	 * @author XinPeiXiang 2020-07-09
	 */
	@ResponseBody
	@RequestMapping(value = "/do_updateSummerVacation", method = RequestMethod.POST)
	public String do_updateSummerVacation(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		
		JSONObject obj_arr = new JSONObject();
		// 修改权限仅限于：管理员；
		if ( obj.getUserType().getType_leibie() != 3 && obj.getUserType().getType_leibie() != 6 ) {
			obj_arr.put("list_advs", "ok1");
			return obj_arr.toString();
		}
		
		String id = request.getParameter("id");

		String au_name = request.getParameter("userName");
		String stu_school = request.getParameter("stu_school");
		String stu_class = request.getParameter("stu_class");
		String stu_phone = request.getParameter("stu_phone");
		String start_time = request.getParameter("start_time");
		String sub_id = request.getParameter("subject");
		String t_id = request.getParameter("the_teacher");
		String if_signup = request.getParameter("if_signup");
		String remarks = request.getParameter("remarks");

		SummerVacation summerVacation = new SummerVacation();
		
		School school =  new School();
		school.setSch_id(Integer.valueOf(stu_school));
		
		Subject subject = new Subject();
		subject.setSub_id(sub_id);

		Teacher teacher = new Teacher();
		teacher.setT_id(t_id);
		
		summerVacation.setSm_id(Integer.valueOf(id));
		summerVacation.setAu_name(au_name);
		summerVacation.setSchool(school);
		summerVacation.setClassinfo_name(stu_class);
		summerVacation.setPhone(stu_phone);
		summerVacation.setSubject(subject);
		summerVacation.setTeacher(teacher);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sf.parse(start_time);
			summerVacation.setStart_time(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(if_signup != "" && if_signup != null){
			summerVacation.setIf_signup(Integer.valueOf(if_signup));
		}
		summerVacation.setRemarks(remarks);
		
//		System.out.println(summerVacation);
		
		Integer count = summerVacationService.do_updateSummerVacation(summerVacation);
		
		String ip = null;
		
		if (count > 0) {
			Loginfo_insert_update logInfo = new Loginfo_insert_update();
			logInfo.setUserName(obj.getUser_name());
			String xiangQing = obj.getUser_name()+"成功修改了暑假班学员 :  "+au_name;
			logInfo.setXiangQing(xiangQing);
			try{
				ip = FindIP.getIpAddr(request);
			}catch(Exception e){
				System.out.println("未能正常获取IP");
				ip = "未能正常获取IP";
			}finally{
				logInfo.setLog_ip(ip);
				loginfo_insert_updateService.do_insertLogInfo(logInfo);
				obj_arr.put("list_advs", "ok");
			}
		}
		return obj_arr.toString();
	}

}
