package com.hicode.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hicode.oa.service.TeacherService;
import com.hicode.oa.tool.Teacher;


/**
 * 讲师管理应用控制层
 * @author xinpeixiang
 * @date 2018-10-22
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("/to_login")
	public String login(){
		System.out.println("--------------------");
		return "/WEB-INF/VisitorsPage/Teacher.html";
	}
	
	@ResponseBody
	@RequestMapping(value ="/showTeacherByInfo")
	public String showTeacherByInfo(HttpServletRequest request){
		
		String start = request.getParameter("start");
		String count = request.getParameter("count");
		
		List<Teacher> teacher = teacherService.getTeacherAll(1, 3);
		
		for (Teacher t : teacher) {
			System.out.println(t.getT_id()+" : "+t.getT_name());
		}
		System.out.println("--------------------");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teacher", teacher);
		JSONObject obj = new JSONObject(map);
		
		return obj.toString();
	}
	

	@RequestMapping("/showTeacher")
	public String showTeacher_(){
		
		List<Teacher> teacher = teacherService.getTeacherAll(1, 3);
		
		for (Teacher t : teacher) {
			System.out.println(t.getT_id()+" : "+t.getT_name());
		}
		System.out.println("--------------------");
		return "/welcome.html";
	}
}
