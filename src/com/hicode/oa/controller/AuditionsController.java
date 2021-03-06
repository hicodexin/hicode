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
import com.hicode.oa.tool.Teacher;
import com.hicode.oa.tool.UserInfo;

import net.sf.json.JSON;
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

	@RequestMapping("/to_login")
	public String login(HttpServletRequest request) {
		System.out.println("-------auditions------------");
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

		// 页码
		String page = request.getParameter("page");
		// 开始数字
		Integer start = 0;
		// 每页显示条数
		Integer num = 30;

		if (page != null) {
			start = (Integer.valueOf(page) - 1) * 30;
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
			obj.put("phone", adv1.getPhone());
			obj.put("tea_name", adv1.getTeacher().getT_name());
			obj.put("adv_name", adv1.getAdviser().getAdv_name());
			obj.put("adv_name2", adv1.getAdviser2().getAdv_name());
			obj.put("beizhu", adv1.getRemarks());

			objs.add(obj);
		}

		obj_arr.put("list_advs", objs);
		if (page.equals("1")) {

			all_num = auditionsService.getAuditionsForCountBySomeOption(map);

			if (all_num != null) {
				all_num = (all_num % 30 == 0) ? (all_num / 30) : (all_num / 30 + 1);
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
		// 游客没有添加权限
		if (obj.getUserType().getType_leibie() == 0) {
			obj_arr.put("list_advs", "ok1");
			return obj_arr.toString();
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
		String phone = request.getParameter("phone");
		String t_id = request.getParameter("update_selteas");
		String adv_id = request.getParameter("update_seladvs");
		String adv_id2 = request.getParameter("update_seladvs2");
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
		auditions.setRemarks(remarks);

		Integer count = auditionsService.do_insertAuditions(auditions);

		if (count > 0) {
			obj_arr.put("list_advs", "ok");
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
		// 游客与普通用户没有修改权限
		if (obj.getUserType().getType_leibie() == 0) {
			obj_arr.put("list_advs", "ok1");
			return obj_arr.toString();
		} else if (obj.getUserType().getType_leibie() == 1) {
			obj_arr.put("list_advs", "ok1");
			return obj_arr.toString();
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
		String phone = request.getParameter("phone");
		String t_id = request.getParameter("update_selteas");
		String adv_id = request.getParameter("update_seladvs");
		String adv_id2 = request.getParameter("update_seladvs2");
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
		auditions.setRemarks(remarks);

		Integer count = auditionsService.do_updateAuditions(auditions);
		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}
		return obj_arr.toString();
	}


}
