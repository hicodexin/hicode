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

import com.hicode.oa.service.KuaJie_PhoneService;
import com.hicode.oa.service.Loginfo_insert_updateService;
import com.hicode.oa.system.FindIP;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Auditions;
import com.hicode.oa.tool.KuaJie;
import com.hicode.oa.tool.KuaJie_Phone;
import com.hicode.oa.tool.Loginfo_insert_update;
import com.hicode.oa.tool.School;
import com.hicode.oa.tool.Teacher;
import com.hicode.oa.tool.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 异业合作兑换名单控制层
 * 
 * @author XinPeiXiang 2019-12-1
 *
 */
@Controller
@RequestMapping("/kuajie_Phone")
public class KuaJie_PhoneController {
	
	@Autowired
	private KuaJie_PhoneService kuaJie_PhoneService;
	@Autowired
	private Loginfo_insert_updateService loginfo_insert_updateService;
	
	@RequestMapping("/to_login")
	public String login(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 异业合作、管理员用户、超级管理员
		if (obj.getUserType().getType_leibie() != 7 && 
				obj.getUserType().getType_leibie() != 3 && 
				obj.getUserType().getType_leibie() != 6) {
			//允许永贺有查看、添加的权限
			if(obj.getUser_name().equals("adv_1005")){
				System.out.println("=========许永贺 账号进行了查看操作=========");
			}else{
				return "redirect:/Fighting.html";
			}
		}
		return "/WEB-INF/VisitorsPage/KuaJie_Phone.html";
	}
	
	/**
	 * 分页展示异业名单兑换信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/showKuaJie_PhoneByInfo", method = RequestMethod.POST)
	public String showKuaJie_PhoneByInfo(HttpServletRequest request) {
		
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
		Integer num = 20;

		if (page != null) {
			start = (Integer.valueOf(page) - 1) * 20;
		}

		Integer all_num = null;
		if (page.equals("1")) {
			all_num = kuaJie_PhoneService.getKuaJie_PhoneForCount();

			if (all_num != null) {
				all_num = (all_num % 20 == 0) ? (all_num / 20) : (all_num / 20 + 1);
			}
		}
		
		
		map.put("start", start);
		map.put("count", num);
		
		List<KuaJie_Phone> advs = kuaJie_PhoneService.getKuaJie_PhoneInfo(start, num);

		JSONArray objs = new JSONArray();

		for (KuaJie_Phone adv1 : advs) {
			JSONObject obj = new JSONObject();

			obj.put("id", adv1.getMd_id());
			obj.put("shijian", adv1.getMd_time());
			obj.put("name", adv1.getKuaJie().getKua_name());//合作机构名称
			obj.put("shuliang", adv1.getMd_num());//兑换数量
			obj.put("age", adv1.getMd_age());//年龄区间
			obj.put("the_pass", adv1.getMd_pass());//接通率
			obj.put("adv_name", adv1.getAdv_name());//负责顾问
			obj.put("shenhe", adv1.getIf_check());//是否经过了审核
			obj.put("beizhu", adv1.getMd_remarks());
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
	 * 添加异业名单兑换信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/do_insertKuaJie_Phone", method = RequestMethod.POST)
	public String do_insertKuaJie_Phone(HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 添加权限：异业合作、管理员用户、超级管理员
		if (
				obj.getUserType().getType_leibie() != 7 && 
				obj.getUserType().getType_leibie() != 3 && 
				obj.getUserType().getType_leibie() != 6
			) {
			if(obj.getUser_name().equals("adv_1005")){
				System.out.println("=========许永贺 账号进行了添加操作=========");
			}else{
				obj_arr.put("list_advs", "ok1");
				return obj_arr.toString();
			}
		}

		String st_time = request.getParameter("time_creatDate");
		String st_name = request.getParameter("userName");
		KuaJie kuaJie = new KuaJie();
		kuaJie.setKua_id(Integer.valueOf(st_name));
		
		String find_num = request.getParameter("find_num");
		String for_success = request.getParameter("for_success");
		if(for_success == "" || for_success == null){
			for_success = "0";
		}
		Integer phoneNum = null;
		Float for_pass = null;
		try{
			phoneNum = Integer.valueOf(find_num);
			for_pass = Float.valueOf(for_success);
		}catch(NumberFormatException e){
			obj_arr.put("list_advs", "no_num");
			return obj_arr.toString();
		}
		
		String st_class = request.getParameter("age");
		String advName = request.getParameter("advName");
		String remarks = request.getParameter("remarks");

		KuaJie_Phone kuaJie_Phone = new KuaJie_Phone();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sf.parse(st_time);
			kuaJie_Phone.setMd_time(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kuaJie_Phone.setKuaJie(kuaJie);
		kuaJie_Phone.setMd_age(st_class);
		kuaJie_Phone.setMd_num(phoneNum);
		kuaJie_Phone.setMd_pass(for_pass);
		kuaJie_Phone.setAdv_name(advName);
		kuaJie_Phone.setMd_remarks(remarks);
		
		kuaJie_Phone.setIf_check(0);//未审核
		
		Integer count = kuaJie_PhoneService.do_insertKuaJie_Phone(kuaJie_Phone);
		
		String ip = null;
		
		if (count > 0) {
			Loginfo_insert_update logInfo = new Loginfo_insert_update();
			logInfo.setUserName(obj.getUser_name());
			String xiangQing = obj.getUser_name()+"成功添加了异业兑换名单 :  "+st_name;
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
	@ResponseBody
	@RequestMapping(value = "/do_updateKuaJie_Phone", method = RequestMethod.POST)
	public String do_updateKuaJie_Phone(HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 修改权限：普通用户，会员用户，管理员
		if (
				obj.getUserType().getType_leibie() != 7 && 
				obj.getUserType().getType_leibie() != 3 && 
				obj.getUserType().getType_leibie() != 6
			) {
			if(obj.getUser_name().equals("adv_1005")){
				System.out.println("=========许永贺 账号进行了修改操作=========");
			}else{
				obj_arr.put("list_advs", "ok1");
				return obj_arr.toString();
			}
		}

		String id = request.getParameter("id");
		String st_name = request.getParameter("shangjia");
		String find_num = request.getParameter("find_num");
		String for_success = request.getParameter("for_success");
		if(for_success == "" || for_success == null){
			for_success = "0";
		}
		Integer phoneNum = null;
		Float for_pass = null;
		try{
			phoneNum = Integer.valueOf(find_num);
			for_pass = Float.valueOf(for_success);
		}catch(NumberFormatException e){
			obj_arr.put("list_advs", "no_num");
			return obj_arr.toString();
		}
		
		String st_class = request.getParameter("age");
		String advName = request.getParameter("advName");
		String remarks = request.getParameter("remarks");

		KuaJie_Phone kuaJie_Phone = new KuaJie_Phone();
		kuaJie_Phone.setMd_id(Integer.valueOf(id));
		kuaJie_Phone.setMd_age(st_class);
		kuaJie_Phone.setMd_num(phoneNum);
		kuaJie_Phone.setMd_pass(for_pass);
		kuaJie_Phone.setAdv_name(advName);
		kuaJie_Phone.setMd_remarks(remarks);

		Integer count = kuaJie_PhoneService.do_updateKuaJie_PhoneSomeColumn(kuaJie_Phone);
		String ip = null;
		
		if (count > 0) {
			Loginfo_insert_update logInfo = new Loginfo_insert_update();
			logInfo.setUserName(obj.getUser_name());
			String xiangQing = obj.getUser_name()+" 成功修改了异业兑换名单中 :  "+st_name;
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
