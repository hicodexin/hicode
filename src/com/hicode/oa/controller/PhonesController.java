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

import com.hicode.oa.service.PhonesService;
import com.hicode.oa.service.SchoolService;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Auditions;
import com.hicode.oa.tool.Phones;
import com.hicode.oa.tool.School;
import com.hicode.oa.tool.Teacher;
import com.hicode.oa.tool.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 电话量
 * 
 * @author XinPeiXiang 2019-01-26
 *
 */
@Controller
@RequestMapping("/phones")
public class PhonesController {
	
	@Autowired
	private PhonesService phonesService;
	@Autowired
	private SchoolService schoolService;

	@RequestMapping("/to_login")
	public String login(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非TMK用户
		if (obj.getUserType().getType_leibie() != 11) {
			return "redirect:/Fighting.html";
		}
		return "/WEB-INF/TMKPage/TMK.html";
	}
	
	@ResponseBody
	@RequestMapping(value = "/showPhonesByInfo", method = RequestMethod.POST)
	public String showPhonesByInfo(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 *ph_name,ph_age,ph_sex,phone_num,ph_school,ph_tmk,
		  		ph_intention,if_arrival
		 * */
		String stu_name = request.getParameter("stu_name");
		if (stu_name != null & stu_name != "") {
			map.put("ph_name", "%" + stu_name + "%");
		}
		
		String kai_age = request.getParameter("kai_age");
		if (kai_age != null & kai_age != "") {
			map.put("kai_age", kai_age);
		}
		
		String ting_age = request.getParameter("ting_age");
		if (ting_age != null & ting_age != "") {
			map.put("ting_age", ting_age);
		}
		
		String ph_sex = request.getParameter("ph_sex");
		if (ph_sex != null & ph_sex != "") {
			map.put("ph_sex", ph_sex);
		}
		
		String phone_num = request.getParameter("phone_num");
		if (phone_num != null & phone_num != "") {
			map.put("phone_num", phone_num);
		}
		
		String ph_school = request.getParameter("ph_school");
		if (ph_school != null & ph_school != "") {
			map.put("ph_school", ph_school);
		}
		String ph_intention = request.getParameter("ph_intention");
		if (ph_intention != null & ph_intention != "") {
			map.put("ph_intention", ph_intention);
		}
		String if_arrival = request.getParameter("if_arrival");
		if (if_arrival != null & if_arrival != "") {
			map.put("if_arrival", if_arrival);
		}
		
		//tmk
		HttpSession session = request.getSession();
		UserInfo obj_user = (UserInfo) session.getAttribute("user");
		if (obj_user == null ||obj_user.getUserType().getType_leibie() != 11) {
			return null;
		}
		String ph_tmk = obj_user.getUser_id();
		map.put("ph_tmk", ph_tmk);
		
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

		List<Phones> advs = null;

		advs = phonesService.getPhonesBySomeOption(map);
		
		JSONArray objs = new JSONArray();

		for (Phones adv1 : advs) {
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getPh_id());
			obj.put("name", adv1.getPh_name());
			obj.put("age", adv1.getPh_age());
			obj.put("sex", adv1.getPh_sex());
			obj.put("phone", adv1.getPhone_num());
			obj.put("school", adv1.getPh_school());
			obj.put("ph_tmk", adv1.getPh_tmk());
			obj.put("yixiang", adv1.getPh_intention());
			obj.put("gaikuang", adv1.getPh_survey());
			obj.put("if_arrival", adv1.getIf_arrival());
			obj.put("beizhu", adv1.getPh_remarks());

			objs.add(obj);
		}

		obj_arr.put("list_advs", objs);
		
		if (page.equals("1")) {

			all_num = phonesService.getPhonesForCountBySomeOption(map);

			if (all_num != null) {
				all_num = (all_num % 100 == 0) ? (all_num / 100) : (all_num / 100 + 1);
			}
			obj_arr.put("all_num", all_num);
		}
		return obj_arr.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/do_insertPhones", method = RequestMethod.POST)
	public String do_insertPhones(HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 游客没有添加权限
		// 只有管理员和TMK具有添加权限
		if (obj.getUserType().getType_leibie() != 11 && obj.getUserType().getType_leibie() != 3) {
			obj_arr.put("list_advs", "ok1");
			return obj_arr.toString();
		}
		/*
		 * ph_id,ph_name,ph_age,ph_sex,phone_num,ph_school,ph_tmk,
  			ph_intention,ph_survey,if_arrival,ph_remarks
		 * */
		
		String ph_name = request.getParameter("userName");
		String ph_age = request.getParameter("userAge");
		String ph_sex = request.getParameter("t_sex");
		String phone_num = request.getParameter("phone");
		
		
		Integer num = phonesService.findThisPhone(phone_num);
//		判断该手机号是否存在
		if(num != null && num>0){
			obj_arr.put("list_advs", "no");
		}
		
		//学校
		String ph_school = request.getParameter("update_school");
		//直接在session中获取当前tmk
		String ph_tmk = obj.getUser_id();
		String ph_intention = request.getParameter("update_selyixiang");
		String gaikuang = request.getParameter("gaikuang");
		String if_arrival = request.getParameter("if_renewal");
		String ph_remarks = request.getParameter("remarks");

		Phones phones = new Phones();

		phones.setPh_name(ph_name);
		phones.setPh_age(Integer.valueOf(ph_age));
		phones.setPh_sex(Integer.valueOf(ph_sex));
		phones.setPhone_num(phone_num);
		
		phones.setPh_tmk(ph_tmk);
		phones.setPh_intention(Integer.valueOf(ph_intention));
		phones.setPh_survey(gaikuang);
		phones.setIf_arrival(Integer.valueOf(if_arrival));
		phones.setPh_remarks(ph_remarks);
		
		School school = schoolService.getSchoolByID(Integer.valueOf(ph_school));
		if(school != null){
			phones.setPh_school(school.getSch_name());
		}else{
			return obj_arr.toString();
		}

		Integer count = phonesService.do_insertPhones(phones);

		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}
		return obj_arr.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/do_updatePhones", method = RequestMethod.POST)
	public String do_updatePhones(HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 只有管理员和TMK具有修改权限
		if (obj.getUserType().getType_leibie() != 11 && obj.getUserType().getType_leibie() != 3) {
			obj_arr.put("list_advs", "ok1");
			return obj_arr.toString();
		}
		String ph_id = request.getParameter("id");
		String ph_name = request.getParameter("userName");
		String ph_age = request.getParameter("userAge");
		String ph_sex = request.getParameter("t_sex");
		String phone_num = request.getParameter("phone");
		if(ph_id != null){
			Phones p1 = phonesService.getPhonesByID(Integer.valueOf(ph_id));
			
			if(!(p1.getPhone_num() != null && p1.getPhone_num().equals(phone_num))){
				Integer num = phonesService.findThisPhone(phone_num);
				//判断该手机号是否存在
				if(num != null && num>0){
					obj_arr.put("list_advs", "no");
					return obj_arr.toString();
				}
			}
		}
		
		//学校
		String ph_school = request.getParameter("update_school");
		//直接在session中获取当前tmk
		String ph_tmk = obj.getUser_id();
		String ph_intention = request.getParameter("update_selyixiang");
		String gaikuang = request.getParameter("gaikuang");
		String if_arrival = request.getParameter("if_renewal");
		
		String ph_remarks = request.getParameter("remarks");

		Phones phones = new Phones();
		
		phones.setPh_id(Integer.valueOf(ph_id));
		phones.setPh_name(ph_name);
		phones.setPh_age(Integer.valueOf(ph_age));
		phones.setPh_sex(Integer.valueOf(ph_sex));
		phones.setPhone_num(phone_num);
		
		phones.setPh_tmk(ph_tmk);
		phones.setPh_intention(Integer.valueOf(ph_intention));
		phones.setPh_survey(gaikuang);
		phones.setIf_arrival(Integer.valueOf(if_arrival));
		phones.setPh_remarks(ph_remarks);
		
		School school = schoolService.getSchoolByID(Integer.valueOf(ph_school));
		if(school != null){
			phones.setPh_school(school.getSch_name());
		}else{
			return obj_arr.toString();
		}

		Integer count = phonesService.do_updatePhones(phones);

		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}
		return obj_arr.toString();
	}
	/**
	 * 查看当前手机号，是否已存在
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findThisPhone", method = RequestMethod.POST)
	public String findThisPhone(HttpServletRequest request) {
		
		String phone_num = request.getParameter("phone_num");

		JSONObject obj_arr = new JSONObject();

		Integer count = phonesService.findThisPhone(phone_num);
		if(count != null && count>0){
			obj_arr.put("call_back", "no");
		}else{
			obj_arr.put("call_back", "ok");
		}
		System.out.println(count+"++++++++++++++++++++"+obj_arr.toString());
		return obj_arr.toString();
	}

	
	
	
	
	
	
	
}
