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

import net.sf.json.JSONArray;


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
	
	/**
	 * 分页展示讲师信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/showTeacherByInfo")
	public String showTeacherByInfo(HttpServletRequest request){
		
		String start = request.getParameter("start");
		String count = request.getParameter("count");
		int s = 0;
		int e = 10;
		if(start != null){
			s = Integer.valueOf(start);
		}
		if(count != null){
			e = Integer.valueOf(count);
		}
		
		List<Teacher> teacher = teacherService.getTeacherAll(s, e);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teacher", teacher);
		JSONObject obj = new JSONObject(map);
		
		return obj.toString();
	}
	
	/**
	 * 获取讲师的ID与name
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/showTeacher")
	public String showTeacher_(){
		
		List<Teacher> teacher = teacherService.getTeacherAll(0, 20);
		JSONArray jsry = new JSONArray();
		for (Teacher t : teacher) {
			//确保该讲师在职
			if(t.getTime_endDate() != null){
				continue;
			}
			net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
			obj.put("tid", t.getT_id());
			obj.put("name", t.getT_name());
			jsry.add(obj);
		}
		
		return jsry.toString();
	}
}
