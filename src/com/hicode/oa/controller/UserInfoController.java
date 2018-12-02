package com.hicode.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.UserInfoService;
import com.hicode.oa.tool.UserInfo;

import net.sf.json.JSONObject;

/**
 * 登录用户控制层
 * 
 * @author XinPeiXiang 2018-12-2
 *
 */
@Controller
@RequestMapping("/UserInfo")
public class UserInfoController {

	@Autowired
	private UserInfoService UserInfoService;

	/**
	 * 判断用户名和密码
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserAndPass", method = RequestMethod.POST)
	public String getUserAndPass(HttpServletRequest request) {

		String ufname = request.getParameter("ufname");
		String ufpass = request.getParameter("ufpass");

		List<UserInfo> uf_list = UserInfoService.getUserInfoByName(ufname);

		JSONObject obj = new JSONObject();

		if (uf_list == null || uf_list.size() == 0) {
			obj.put("call_back", "该用户并未注册。。。。");

			return obj.toString();
		}
		if (!uf_list.get(0).getUser_pwd().equals(ufpass)) {
			obj.put("call_back", "用户名密码不匹配。。。。");

			return obj.toString();
		}
		if (uf_list.get(0).getUserState().getState_id() == 2) {
			obj.put("call_back", "该账户已被锁定。。。。");

			return obj.toString();
		}
		if (uf_list.get(0).getUserState().getState_id() == 3) {
			obj.put("call_back", "该账户已被废弃。。。。");

			return obj.toString();
		}

		HttpSession session = request.getSession();
		/*
		 * ServletContext context = session.getServletContext();
		 * context.getAttribute("users"); if(){
		 * 
		 * }
		 * 
		 */

		session.setAttribute("user", uf_list.get(0));

		obj.put("call_back", "ok");

		return obj.toString();
	}
	
	/**
	 * 判断用户名和密码
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserName", method = RequestMethod.POST)
	public String getUserName(HttpServletRequest request) {

		JSONObject obj = new JSONObject();
	
		HttpSession session = request.getSession();
	
		UserInfo userInfo = (UserInfo) session.getAttribute("user");
		if(userInfo != null){
			
			obj.put("name", userInfo.getUser_name());
			obj.put("type", userInfo.getUserType().getType_remarks());
			return obj.toString();
		}
		obj.put("ok", "okk");
		return obj.toString();
	}
	
	
	
	

}
