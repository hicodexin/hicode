package com.hicode.oa.superVIP.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.PhonesService;
import com.hicode.oa.service.SchoolService;
import com.hicode.oa.service.UserInfoService;
import com.hicode.oa.tool.Phones;
import com.hicode.oa.tool.School;
import com.hicode.oa.tool.SymmetricEncoder;
import com.hicode.oa.tool.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 超级管理员
 * 
 * @author XinPeiXiang 2019-04-30
 *
 */
@Controller
@RequestMapping("/svipPhones")
public class SuperVIP_PhonesController {
	
	@Autowired
	private PhonesService phonesService;
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private SchoolService schoolService;

	@RequestMapping("/to_login")
	public String login(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非超级管理员
		if (obj.getUserType().getType_leibie() != 6) {
			return "redirect:/Fighting.html";
		}
		return "/WEB-INF/SuperVIP/super_index.html";
	}
	
	@ResponseBody
	@RequestMapping(value = "/postUrl" , method = RequestMethod.POST)
	public String postUrl(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非超级管理员
		if (obj.getUserType().getType_leibie() != 6) {
			return "redirect:/Fighting.html";
		}
		
		JSONObject obj_arr = new JSONObject();
		
		JSONArray jsar = new JSONArray();
		
		String uid = null;
		for(int i=5;i<=14;i++){
			JSONObject jsob = new JSONObject();
			if(i<10){
				uid = "us_100"+i;
			}else{
				uid = "us_10"+i;
			}
			UserInfo userInfo = userInfoService.getUserInfoByID(uid);
			if(userInfo == null ){
				continue;
			}
			
			String remark =	userInfo.getRemarks();
			if(remark==null || remark == ""){
				remark = userInfo.getUser_name();
			}
//			System.out.println("用户id：" + uid);
			String date = new SimpleDateFormat("yyyy+MM+dd").format(new Date());
			String key = UUID.randomUUID().toString() + "@" + uid + "@" + date + "@"+SymmetricEncoder.getSymbol();
//			System.out.println(key);
			String encodedKey = SymmetricEncoder.invokeEncryptEncode(key);
//			System.out.println("加密后的秘钥：" + encodedKey);
			String url01 = "/hicode/svipPhones/openSomeTMKList.spc?name=";
			jsob.put("urls", url01+encodedKey);
			jsob.put("beizhu", remark);
			jsar.add(jsob);
		}
		obj_arr.put("giveUrl", jsar);
		return obj_arr.toString();
	}
	
	@RequestMapping("/openSomeTMKList")
	public String openSomeTMKList(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非超级管理员
		if (obj.getUserType().getType_leibie() != 6) {
			return "redirect:/Fighting.html";
		}
		
		String tmk_name = request.getParameter("name");
		if (tmk_name != null & tmk_name != "") {
			
			String encodedKey = tmk_name;
			String key1 = null;
			try{
				key1 = SymmetricEncoder.invokeDecryptEncode(encodedKey);
			}catch(Exception e){
				return "redirect:/Fighting.html";
			}
			
			if(key1 == null){
				return "redirect:/Fighting.html";
			}
//			System.out.println("解密后的秘钥：" + key1);
//			System.out.println("用户id为：" + key1.split("@")[1]);
			
			tmk_name = key1.split("@")[1];
			
			session.setAttribute("tmk_name", tmk_name);
		}
		return "/WEB-INF/SuperVIP/VIP_TMK.html";
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
			Integer ph_school_num =Integer.valueOf(ph_school);
			School sc =schoolService.getSchoolByID(ph_school_num);
			if(null != sc){
				map.put("ph_school", sc.getSch_name());
			}
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
		if (obj_user == null ||obj_user.getUserType().getType_leibie() != 6) {
			return null;
		}
		
		Object tmk_name = session.getAttribute("tmk_name");
		
		if (tmk_name != null & tmk_name != "") {
			UserInfo user_tmk = userInfoService.getUserInfoByID(tmk_name.toString());
			if(null != user_tmk){
				map.put("ph_tmk", tmk_name);
			}else{
				System.out.println("TMK id is null....");
				return null;
			}
		}
		// 页码
		String page = request.getParameter("page");
		// 开始数字
		Integer start = 0;
		// 每页显示条数
		Integer num = 300;

		if (page != null) {
			start = (Integer.valueOf(page) - 1) * 300;
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
				all_num = (all_num % 300 == 0) ? (all_num / 300) : (all_num / 300 + 1);
			}
			obj_arr.put("all_num", all_num);
		}
		return obj_arr.toString();
	}

	
	
	
	
	
	
	
}
