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

import com.hicode.oa.service.SummerVacationService;
import com.hicode.oa.tool.Auditions;
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
			obj.put("name", adv1.getAuditions().getSt_name());
			obj.put("subject", adv1.getSubject().getSub_name());
			obj.put("teacher", adv1.getTeacher().getT_name());
			obj.put("start_time", adv1.getStart_time());
			obj.put("clock_num", adv1.getClock_num());
			obj.put("if_signup", adv1.getIf_signup());
			obj.put("giveClass", adv1.getGive_class());
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
		
		String aud_id = request.getParameter("userName");
		String sub_id = request.getParameter("subject");
		String t_id = request.getParameter("the_teacher");
		
		String start_time = request.getParameter("start_time");
		String clock_num = request.getParameter("clock_num");
		String if_signup = request.getParameter("if_signup");
		
		String giveClass = request.getParameter("giveClass");
		String remarks = request.getParameter("remarks");

		SummerVacation summerVacation = new SummerVacation();

		Auditions auditions = new Auditions();
		auditions.setAu_id(Integer.valueOf(aud_id));

		Subject subject = new Subject();
		subject.setSub_id(sub_id);

		Teacher teacher = new Teacher();
		teacher.setT_id(t_id);
		
		summerVacation.setAuditions(auditions);
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
		
		if(clock_num != null && clock_num != ""){
			summerVacation.setClock_num(Integer.valueOf(clock_num));
		}
		
		summerVacation.setIf_signup(Integer.valueOf(if_signup));
		
		if(giveClass != null && giveClass != ""){
			summerVacation.setGive_class(Integer.valueOf(giveClass));
		}
		
		summerVacation.setRemarks(remarks);
		
		Integer count = summerVacationService.do_insertSummerVacation(summerVacation);
		
		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}

		return obj_arr.toString();
	}

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

		String aud_id = request.getParameter("userName");
		String sub_id = request.getParameter("subject");
		String t_id = request.getParameter("the_teacher");
		
		String start_time = request.getParameter("start_time");
		String clock_num = request.getParameter("clock_num");
		String if_signup = request.getParameter("if_signup");
		
		String giveClass = request.getParameter("giveClass");
		String remarks = request.getParameter("remarks");

		SummerVacation summerVacation = new SummerVacation();

		Auditions auditions = new Auditions();
		auditions.setAu_id(Integer.valueOf(aud_id));

		Subject subject = new Subject();
		subject.setSub_id(sub_id);

		Teacher teacher = new Teacher();
		teacher.setT_id(t_id);
		
		summerVacation.setSm_id(Integer.valueOf(id));
		summerVacation.setAuditions(auditions);
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

		if(clock_num != null && clock_num != ""){
			summerVacation.setClock_num(Integer.valueOf(clock_num));
		}
		
		summerVacation.setIf_signup(Integer.valueOf(if_signup));
		
		if(giveClass != null && giveClass != ""){
			summerVacation.setGive_class(Integer.valueOf(giveClass));
		}
		
		summerVacation.setRemarks(remarks);
		
		Integer count = summerVacationService.do_updateSummerVacation(summerVacation);
		
		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}

		return obj_arr.toString();
	}

}
