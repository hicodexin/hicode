package com.hicode.oa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.LogInfoService;
import com.hicode.oa.service.UserInfoService;
import com.hicode.oa.tool.LogInfo;
import com.hicode.oa.tool.UserInfo;
import com.hicode.oa.tool.UserState;

import net.sf.json.JSONArray;
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

	@Autowired
	private LogInfoService logInfoService;

	/**
	 * 
	 */
	@RequestMapping("/to_login")
	public String login(HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非会员用户、管理员用户、超级管理员
		if (obj.getUserType().getType_leibie() != 3
				&& obj.getUserType().getType_leibie() != 6) {
			return "redirect:/Fighting.html";
		}

		return "/WEB-INF/ManagerPage/UserOnline.html";
	}

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
		LogInfo info = new LogInfo();

		try {
			if (uf_list == null || uf_list.size() == 0) {
				obj.put("call_back", "该用户并未注册。。。。");
				info.setLog_success(0);
				info.setLog_remarks("该用户并未注册");
				return obj.toString();
			}
			if (!uf_list.get(0).getUser_pwd().equals(ufpass)) {
				obj.put("call_back", "用户名密码不匹配。。。。");
				info.setLog_success(0);
				info.setLog_remarks("用户名密码不匹配");
				return obj.toString();
			}
			if (uf_list.get(0).getUserState().getState_id() == 2) {
				obj.put("call_back", "该账户已被锁定。。。。");
				info.setLog_success(0);
				info.setLog_remarks("该账户已被锁定");
				return obj.toString();
			}
			if (uf_list.get(0).getUserState().getState_id() == 3) {
				obj.put("call_back", "该账户已被废弃。。。。");
				info.setLog_success(0);
				info.setLog_remarks("该账户已被废弃");
				return obj.toString();
			}

			HttpSession session = request.getSession();

			ServletContext application = session.getServletContext();
			// 在全局变量中检索登录用户信息
			Map<String, HttpSession> userOnline = (Map<String, HttpSession>) application.getAttribute("userOnline");
			// 如果未检索到,为其新创建,防止空指针异常
			if (userOnline == null) {
				userOnline = new HashMap<String, HttpSession>();
				application.setAttribute("userOnline", userOnline);
			}

			if (userOnline.containsKey(ufname)) {
				obj.put("call_back", "该账户已在线,不支持重复登录。。。。");
				info.setLog_success(0);
				info.setLog_remarks("该账户已在线");
				return obj.toString();
			}
			// 设置进入session作用域,进而放入application作用域
			session.setAttribute("user", uf_list.get(0));
			userOnline.put(ufname, session);
			info.setLog_success(1);
			info.setLog_remarks("登陆成功");
			obj.put("call_back", "ok");
		} finally {
			String ip = getIpAddr(request);
			info.setLog_ip(ip);
			info.setLog_time(new Date());

			if (uf_list != null && uf_list.size() > 0) {
				info.setUserInfo(uf_list.get(0));
			} else {
				UserInfo us = new UserInfo();
				us.setUser_id("u_1000");
				info.setUserInfo(us);
			}

			logInfoService.do_insertLogInfo(info);
		}

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
		if (userInfo != null) {

			obj.put("name", userInfo.getUser_name());
			obj.put("type", userInfo.getUserType().getType_remarks());
			return obj.toString();
		}
		obj.put("ok", "okk");
		return obj.toString();
	}

	/**
	 * 用户登录退出
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logOut", method = RequestMethod.POST)
	public String logOut(HttpServletRequest request) {

		String userName = request.getParameter("userName");

		HttpSession session = request.getSession();

		ServletContext application = session.getServletContext();

		Map<String, HttpSession> userOnline = (Map<String, HttpSession>) application.getAttribute("userOnline");

		JSONObject obj = new JSONObject();

		if (userOnline != null && userOnline.size() > 0) {

			Set<String> sets = userOnline.keySet();

			for (String s : sets) {
				if (userName != null) {
					UserInfo uf = (UserInfo) userOnline.get(s).getAttribute("user");
					if (uf != null && uf.getUser_name().equals(userName)) {
						userOnline.get(s).removeAttribute("user");
						userOnline.remove(s);
						obj.put("ok", "已经令其下线。。。");
						return obj.toString();
					}
				} else {
					if (userOnline.get(s) == session) {
						userOnline.get(s).removeAttribute("user");
						userOnline.remove(s);
						obj.put("ok", "okk");
						return obj.toString();
					}
				}

			}
		}

		return obj.toString();
	}

	/**
	 * 展示正在上线的
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/showUserOnline", method = RequestMethod.POST)
	public String showUserOnline(HttpServletRequest request) {
		HttpSession session = request.getSession();

		ServletContext application = session.getServletContext();

		Map<String, HttpSession> userOnline = (Map<String, HttpSession>) application.getAttribute("userOnline");

		JSONArray arry = new JSONArray();

		if (userOnline != null && userOnline.size() > 0) {

			Set<String> sets = userOnline.keySet();

			for (String s : sets) {
				JSONObject obj = new JSONObject();

				obj.put("name", s);
				UserInfo info = null;
				try{
					info = (UserInfo) userOnline.get(s).getAttribute("user");
				}catch(IllegalStateException e){
					System.out.println("==================Session 失效==================");
					continue;
				}

				String type = "游客";

				if(info != null){
					switch (info.getUserType().getType_leibie()) {
					case 0:
						type = "游客";
						break;
					case 1:
						type = "普通用户";
						break;
					case 2:
						type = "会员用户";
						break;
					case 3:
						type = "管理员";
						break;
					case 6:
						type = "超级管理员";
						break;
					}
					
				}else{
					System.out.println("==================没有获得用户信息==================");
				}

				obj.put("type", type);

				if (type.equals("管理员") || type.equals("超级管理员")) {
					obj.put("mger", "yes");
				}

				obj.put("userID", info.getUser_id());

				List<LogInfo> logs = logInfoService.getLogInfoByUserID(info.getUser_id());
				if (logs != null && logs.size() > 0) {
					LogInfo log = logs.get(logs.size() - 1);
					obj.put("ip", log.getLog_ip());
					obj.put("time", log.getLog_time());
				}

				arry.add(obj);
			}
		}
		JSONObject obj_arr = new JSONObject();
		obj_arr.put("list_advs", arry);
		return obj_arr.toString();
	}

	/**
	 * 锁定用户账号
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/closeUserInfoState", method = RequestMethod.POST)
	public String closeUserInfoState(HttpServletRequest request) {

		String userID = request.getParameter("id");
		UserInfo user = UserInfoService.getUserInfoByID(userID);

		JSONObject obj = new JSONObject();
		if (user == null) {
			obj.put("ok", "数据有误,联系管理员。。。");
			return obj.toString();
		}

		UserState us = new UserState();
		us.setState_id(2);

		user.setUserState(us);

		Integer count = UserInfoService.do_updateUserInfo(user);

		if (count > 0) {
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();

			Map<String, HttpSession> userOnline = (Map<String, HttpSession>) application.getAttribute("userOnline");

			if (userOnline != null && userOnline.size() > 0) {

				Set<String> sets = userOnline.keySet();
				for (String s : sets) {
					UserInfo uf = (UserInfo) userOnline.get(s).getAttribute("user");
					if (uf.getUser_id().equals(userID)) {
						userOnline.get(s).removeAttribute("user");
						userOnline.remove(s);
						obj.put("ok", "改账号已被锁定。。。");
						return obj.toString();
					}
				}
			}
		}

		obj.put("ok", "修改失败,联系管理员。。。");
		return obj.toString();
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
