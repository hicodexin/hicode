package com.hicode.oa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hicode.oa.tool.UserInfo;

/**
 * 排课详情
 * 
 * @author XinPeiXiang 2019-06-30
 *
 */
@Controller
@RequestMapping("/scheduling")
public class SchedulingController {
	
	@RequestMapping("/to_login")
	public String login(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非课程顾问、非会员用户、管理员用户、超级管理员
		/*if (
				obj.getUserType().getType_leibie() != 5 &&
				obj.getUserType().getType_leibie() != 3 && 
				obj.getUserType().getType_leibie() != 2 &&
				obj.getUserType().getType_leibie() != 6
			) {
			return "redirect:/Fighting.html";
		}
		*/
		return "/WEB-INF/TeacherPage/Scheduling.html";
	}
	@RequestMapping("/login_ShuJiaBan")
	public String login_ShuJiaBan(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		
		return "/WEB-INF/TeacherPage/ShuJiaBan.html";
	}
	
	@RequestMapping("/login_ShuJiaBanXia")
	public String login_ShuJiaBanXia(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		
		return "/WEB-INF/TeacherPage/ShuJiaBan_xia.html";
	}
	
	@RequestMapping("/login_JiXunYing")
	public String login_JiXunYing(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		
		return "/WEB-INF/TeacherPage/JiXunYing.html";
	}
	
	@RequestMapping("/login_ShuJiaBan_201908")
	public String login_ShuJiaBan_201908(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		
		return "/WEB-INF/TeacherPage/ShuJiaBan_201908.html";
	}
	
	@RequestMapping("/login_ShuJiaBan_201909_xia")
	public String login_ShuJiaBan_201909_xia(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		
		return "/WEB-INF/TeacherPage/ShuJiaBan_201909_xia.html";
	}
}
















