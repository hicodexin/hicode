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

import com.hicode.oa.service.KuaJieService;
import com.hicode.oa.service.Loginfo_insert_updateService;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.KuaJie;
import com.hicode.oa.tool.Loginfo_insert_update;
import com.hicode.oa.tool.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 跨界合作控制层
 * 
 * @author XinPeiXiang 2019-11-9
 *
 */
@Controller
@RequestMapping("/kuajie")
public class KuaJieController {
	
	@Autowired
	private KuaJieService kuaJieService;
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
			return "redirect:/Fighting.html";
		}
		return "/WEB-INF/VisitorsPage/KuaJie.html";
	}

	/**
	 * 分页展示异业合作信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/showKuaJieByInfo", method = RequestMethod.POST)
	public String showAdviserByInfo(HttpServletRequest request) {
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
			all_num = kuaJieService.getKuaJieForCount();

			if (all_num != null) {
				all_num = (all_num % 20 == 0) ? (all_num / 20) : (all_num / 20 + 1);
			}

		}
		List<KuaJie> advs = kuaJieService.getKuaJieInfo(start, num);

		JSONArray objs = new JSONArray();

		for (KuaJie adv1 : advs) {
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getKua_id());
			obj.put("kuatime", adv1.getKua_time());
			obj.put("name", adv1.getKua_name());
			obj.put("address", adv1.getKua_address());
			obj.put("phone", adv1.getKua_phone());
			obj.put("weixin", adv1.getKua_weixin());
			obj.put("shichang", adv1.getKua_adv());
			obj.put("shichang_now", adv1.getKua_adv_now());
			obj.put("yixiang", adv1.getKua_yixiang());
			obj.put("beizhu", adv1.getKua_remarks());
			objs.add(obj);
		}

		JSONObject obj_arr = new JSONObject();
		obj_arr.put("list_advs", objs);

		if (page.equals("1")) {
			obj_arr.put("all_num", all_num);
		}
		return obj_arr.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/do_insertKuaJie", method = RequestMethod.POST)
	public String do_insertKuaJie(HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 添加权限仅限于，异业合作，管理员，超级管理员；
		if (
				obj.getUserType().getType_leibie() != 7 && 
				obj.getUserType().getType_leibie() != 3 && 
				obj.getUserType().getType_leibie() != 6
			) {
			obj_arr.put("list_advs", "ok1");
			
			return obj_arr.toString();
		}
		
		String st_name = request.getParameter("userName");
		String st_time = request.getParameter("time_creatDate");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String weixin = request.getParameter("weixin");
		String adv_id = request.getParameter("adv_name");
		String update_selyi = request.getParameter("update_selyi");
		String remarks = request.getParameter("remarks");

		KuaJie kuaJie = new KuaJie();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sf.parse(st_time);
			kuaJie.setKua_time(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		kuaJie.setKua_name(st_name);
		kuaJie.setKua_address(address);
		kuaJie.setKua_phone(phone);
		kuaJie.setKua_weixin(weixin);
		kuaJie.setKua_adv(adv_id);
		kuaJie.setKua_adv_now(adv_id);
		kuaJie.setKua_yixiang(Integer.valueOf(update_selyi));
		kuaJie.setKua_remarks(remarks);
		
		Integer count = kuaJieService.do_insertKuaJie(kuaJie);
		
		String ip = null;
		
		if (count > 0) {
			Loginfo_insert_update logInfo = new Loginfo_insert_update();
			logInfo.setUserName(obj.getUser_name());
			String xiangQing = obj.getUser_name()+"成功添加了异业合作单位 :  "+st_name+" : "+address+" 店";
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
	@RequestMapping(value = "/do_updateKuaJie", method = RequestMethod.POST)
	public String do_updateKuaJie(HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 添加权限仅限于，异业合作，管理员，超级管理员；
		if (
				obj.getUserType().getType_leibie() != 7 && 
				obj.getUserType().getType_leibie() != 3 && 
				obj.getUserType().getType_leibie() != 6
			) {
			obj_arr.put("list_advs", "ok1");
			
			return obj_arr.toString();
		}
		
		String id = request.getParameter("id");
		String st_name = request.getParameter("userName");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String weixin = request.getParameter("weixin");
		String adv_id = request.getParameter("adv_name");
		String update_selyi = request.getParameter("update_selyi");
		String remarks = request.getParameter("remarks");

		KuaJie kuaJie = new KuaJie();
		if(null != id && "" != id){
			kuaJie.setKua_id(Integer.valueOf(id));
		}
		kuaJie.setKua_phone(phone);
		kuaJie.setKua_weixin(weixin);
		kuaJie.setKua_adv_now(adv_id);
		kuaJie.setKua_yixiang(Integer.valueOf(update_selyi));
		kuaJie.setKua_remarks(remarks);
		
		Integer count = kuaJieService.do_updateKuaJieSomeColumn(kuaJie);
		
		String ip = null;
		
		if (count > 0) {
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String d = sf.format(new Date());
			
			Loginfo_insert_update logInfo = new Loginfo_insert_update();
			logInfo.setUserName(obj.getUser_name());
			String xiangQing = d+" : "+ obj.getUser_name()+"成功修改了异业合作单位 :  "+st_name+" : "+address+" 店";
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
	 * 展示异业合作方
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/showKuaJie", method = RequestMethod.POST)
	public String showAdviser() {

		List<KuaJie> advs = kuaJieService.getKuaJieNameAndID();
		JSONArray jsay = new JSONArray();

		for (KuaJie adv1 : advs) {
			
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getKua_id());
			obj.put("name", adv1.getKua_name());

			jsay.add(obj);
		}

		return jsay.toString();
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






