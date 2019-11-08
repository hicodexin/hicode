package com.hicode.oa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.AuditionsService;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Auditions;
import com.hicode.oa.tool.Loginfo_insert_update;
import com.hicode.oa.tool.School;
import com.hicode.oa.tool.Teacher;
import com.hicode.oa.tool.UserInfo;
import com.hicode.oa.service.Loginfo_insert_updateService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 试听课详情控制层
 * 
 * @author XinPeiXiang
 *
 */
@Controller
@RequestMapping("/auditions")
public class AuditionsController {

	@Autowired
	private AuditionsService auditionsService;
	@Autowired
	private Loginfo_insert_updateService loginfo_insert_updateService;

	@RequestMapping("/to_login")
	public String login(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非会员用户、管理员用户、超级管理员，课程顾问
		if (obj.getUserType().getType_leibie() != 3 && obj.getUserType().getType_leibie() != 2
				&& obj.getUserType().getType_leibie() != 5&& obj.getUserType().getType_leibie() != 6) {
			return "redirect:/Fighting.html";
		}
		return "/WEB-INF/VisitorsPage/Auditions.html";
	}

	@ResponseBody
	@RequestMapping(value = "/showAuditionsByInfo", method = RequestMethod.POST)
	public String showAuditionsByInfo(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();

		String stu_name = request.getParameter("stu_name");
		if (stu_name != null & stu_name != "") {
			map.put("stu_name", "%" + stu_name + "%");
		}
		String stu_class = request.getParameter("stu_class");
		if (stu_class != null & stu_class != "") {
			map.put("stu_class", stu_class);
		}
		
		String stu_school = request.getParameter("stu_school");
		if (stu_school != null & stu_school != "") {
			map.put("stu_school", stu_school);
		}
		
		String stu_phone = request.getParameter("stu_phone");
		if (stu_phone != null & stu_phone != "") {
			map.put("stu_phone", stu_phone);
		}
		String stu_teacher = request.getParameter("stu_teacher");
		if (stu_teacher != null & stu_teacher != "") {
			map.put("stu_teacher", stu_teacher);
		}
		String kai_time = request.getParameter("kai_time");
		if (kai_time != null & kai_time != "") {
			map.put("kai_time", kai_time);
		}
		String ting_time = request.getParameter("ting_time");
		if (ting_time != null & ting_time != "") {
			map.put("ting_time", ting_time);
		}
		String yao_gu = request.getParameter("yao_gu");
		if (yao_gu != null & yao_gu != "") {
			map.put("yao_gu", yao_gu);
		}
		String qian_gu = request.getParameter("qian_gu");
		if (qian_gu != null & qian_gu != "") {
			map.put("qian_gu", qian_gu);
		}
		
		//是否参加了试听课
		String if_join = request.getParameter("if_join");
		if (if_join != null & if_join != "") {
			map.put("if_join", if_join);
		}

		// 页码
		String page = request.getParameter("page");
		// 开始数字
		Integer start = 0;
		// 每页显示条数
		Integer num = 100;

		if (page != null) {
			start = (Integer.valueOf(page) - 1) * 100;
		}

		Integer all_num = null;

		map.put("start", start);
		map.put("count", num);

		JSONObject obj_arr = new JSONObject();

		List<Auditions> advs = null;

		advs = auditionsService.getAuditionsBySomeOption(map);
		
		JSONArray objs = new JSONArray();

		for (Auditions adv1 : advs) {
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getAu_id());
			obj.put("time", adv1.getSt_time());
			obj.put("name", adv1.getSt_name());
			obj.put("sex", adv1.getSt_sex());
			obj.put("classinfo", adv1.getSt_class());
			if(adv1.getSchool() != null){
				obj.put("school", adv1.getSchool().getSch_name());
			}else{
				obj.put("school", "0000");
			}
			obj.put("phone", adv1.getPhone());
			obj.put("tea_name", adv1.getTeacher().getT_name());
			obj.put("adv_name", adv1.getAdviser().getAdv_name());
			obj.put("adv_name2", adv1.getAdviser2().getAdv_name());
			obj.put("if_join", adv1.getIf_join());
			obj.put("beizhu", adv1.getRemarks());

			objs.add(obj);
		}

		obj_arr.put("list_advs", objs);
		
		if (page.equals("1")) {

			all_num = auditionsService.getAuditionsForCountBySomeOption(map);

			if (all_num != null) {
				all_num = (all_num % 100 == 0) ? (all_num / 100) : (all_num / 100 + 1);
			}
			obj_arr.put("all_num", all_num);
		}
		return obj_arr.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/do_insertAuditions", method = RequestMethod.POST)
	public String do_insertAuditions(HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 添加权限仅限于，普通用户，会员用户，管理员，超级管理员；
		if (
				obj.getUserType().getType_leibie() != 1 && 
				obj.getUserType().getType_leibie() != 2 && 
				obj.getUserType().getType_leibie() != 3 && 
				obj.getUserType().getType_leibie() != 6
			) {
			if(
					obj.getUser_name().equals("adv_1005") || 
					obj.getUser_name().equals("adv_1016") || 
					obj.getUser_name().equals("adv_1021")
				){
				
				System.out.println("=========徐永贺、王秋杰、孙崇崇账号进行了添加操作=========");
			}else{
				obj_arr.put("list_advs", "ok1");
				
				return obj_arr.toString();
			}
		}

		String st_time = request.getParameter("time_creatDate");
		String st_name = request.getParameter("userName");
		String st_sex = request.getParameter("t_sex");
		String st_class = request.getParameter("update_selclass");
		switch (st_class) {
		case "1":
			st_class = "学前班";
			break;
		case "2":
			st_class = "一年级";
			break;
		case "3":
			st_class = "二年级";
			break;
		case "4":
			st_class = "三年级";
			break;
		case "5":
			st_class = "四年级";
			break;
		case "6":
			st_class = "五年级";
			break;
		case "7":
			st_class = "六年级";
			break;
		case "8":
			st_class = "初一";
			break;
		case "9":
			st_class = "初二";
			break;
		case "10":
			st_class = "初三";
			break;
		case "11":
			st_class = "高一";
			break;
		case "12":
			st_class = "高二";
			break;
		case "13":
			st_class = "高三";
			break;
		}
		String sch_id = request.getParameter("update_school");
		String phone = request.getParameter("phone");
		String t_id = request.getParameter("update_selteas");
		String adv_id = request.getParameter("update_seladvs");
		String adv_id2 = request.getParameter("update_seladvs2");
		String if_join = request.getParameter("update_seljoin");
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
		
		School school = new School();
		school.setSch_id(Integer.valueOf(sch_id));
		
		auditions.setSchool(school);
		auditions.setPhone(phone);
		Teacher t = new Teacher();
		t.setT_id(t_id);
		auditions.setTeacher(t);
		Adviser adviser = new Adviser();
		adviser.setAdv_id(adv_id);
		Adviser adviser2 = new Adviser();
		adviser2.setAdv_id(adv_id2);

		auditions.setAdviser(adviser);
		auditions.setAdviser2(adviser2);
		auditions.setIf_join(Integer.valueOf(if_join));
		auditions.setRemarks(remarks);

		Integer count = auditionsService.do_insertAuditions(auditions);
		
		String ip = null;
		
		if (count > 0) {
			Loginfo_insert_update logInfo = new Loginfo_insert_update();
			logInfo.setUserName(obj.getUser_name());
			String xiangQing = obj.getUser_name()+"成功添加了试听课学员 :  "+st_name;
			logInfo.setXiangQing(xiangQing);
			try{
				ip = getIpAddr(request);
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

	@ResponseBody
	@RequestMapping(value = "/showAuditions", method = RequestMethod.POST)
	public String showAuditions() {
		List<Auditions> advs = auditionsService.getAudNameAndID();
		JSONArray jsay = new JSONArray();

		for (Auditions adv1 : advs) {
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getAu_id());
			obj.put("name", adv1.getSt_name());
			jsay.add(obj);
		}
		return jsay.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/do_updateAuditions", method = RequestMethod.POST)
	public String do_updateAuditions(HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 修改权限：普通用户，会员用户，管理员
		if (
				obj.getUserType().getType_leibie() != 1 && 
				obj.getUserType().getType_leibie() != 2 && 
				obj.getUserType().getType_leibie() != 3 && 
				obj.getUserType().getType_leibie() != 6
			) {
			
			if(
					obj.getUser_name().equals("adv_1005") || 
					obj.getUser_name().equals("adv_1016") || 
					obj.getUser_name().equals("adv_1021")
				){
				//课程顾问中徐永贺可以有试听课的添加和修改权限
				System.out.println("=========徐永贺、王秋杰、孙崇崇账号进行了修改操作=========");
			}else{
				obj_arr.put("list_advs", "ok1");
				return obj_arr.toString();
			}
		}

		String id = request.getParameter("id");
		String st_time = request.getParameter("time_creatDate");
		String st_name = request.getParameter("userName");
		String st_sex = request.getParameter("t_sex");
		String st_class = request.getParameter("update_selclass");
		switch (st_class) {
		case "1":
			st_class = "学前班";
			break;
		case "2":
			st_class = "一年级";
			break;
		case "3":
			st_class = "二年级";
			break;
		case "4":
			st_class = "三年级";
			break;
		case "5":
			st_class = "四年级";
			break;
		case "6":
			st_class = "五年级";
			break;
		case "7":
			st_class = "六年级";
			break;
		case "8":
			st_class = "初一";
			break;
		case "9":
			st_class = "初二";
			break;
		case "10":
			st_class = "初三";
			break;
		case "11":
			st_class = "高一";
			break;
		case "12":
			st_class = "高二";
			break;
		case "13":
			st_class = "高三";
			break;
		}
		String sch_id = request.getParameter("update_school");
		String phone = request.getParameter("phone");
		String t_id = request.getParameter("update_selteas");
		String adv_id = request.getParameter("update_seladvs");
		String adv_id2 = request.getParameter("update_seladvs2");
		String if_join = request.getParameter("update_seljoin");
		String remarks = request.getParameter("remarks");

		Auditions auditions = new Auditions();
		auditions.setAu_id(Integer.valueOf(id));

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

		School school = new School();
		school.setSch_id(Integer.valueOf(sch_id));
		
		auditions.setSchool(school);
		auditions.setPhone(phone);
		Teacher t = new Teacher();
		t.setT_id(t_id);
		auditions.setTeacher(t);
		Adviser adviser = new Adviser();
		adviser.setAdv_id(adv_id);
		Adviser adviser2 = new Adviser();
		adviser2.setAdv_id(adv_id2);

		auditions.setAdviser(adviser);
		auditions.setAdviser2(adviser2);
		if(if_join == null || if_join == ""){
			auditions.setIf_join(1);//已经试听
		}else{
			auditions.setIf_join(Integer.valueOf(if_join));
		}
		auditions.setRemarks(remarks);

		Integer count = auditionsService.do_updateAuditions(auditions);
		String ip = null;
		
		if (count > 0) {
			Loginfo_insert_update logInfo = new Loginfo_insert_update();
			logInfo.setUserName(obj.getUser_name());
			String xiangQing = obj.getUser_name()+" 成功修改了试听课学员 :  "+st_name;
			logInfo.setXiangQing(xiangQing);
			try{
				ip = getIpAddr(request);
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
	 * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
	 * 
	 * @return ip
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
