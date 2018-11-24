package com.hicode.oa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.TeacherService;
import com.hicode.oa.tool.Teacher;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 讲师管理应用控制层
 * 
 * @author xinpeixiang
 * @date 2018-10-22
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@RequestMapping("/to_login")
	public String login() {
		System.out.println("--------------------");
		return "/WEB-INF/VisitorsPage/Teacher.html";
	}

	/**
	 * 分页展示讲师信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/showTeacherByInfo")
	public String showTeacherByInfo(HttpServletRequest request) {

		// 页码
		String page = request.getParameter("page");
		// 开始数字
		Integer start = 0;
		// 每页显示条数
		Integer num = 10;

		if (page != null) {
			start = (Integer.valueOf(page) - 1) * 10;
		}

		Integer all_num = null;
		if (page.equals("1")) {
			all_num = teacherService.getTeacherForCount();

			if (all_num != null) {
				all_num = (all_num % 10 == 0) ? (all_num / 10) : (all_num / 10 + 1);
			}
		}

		List<Teacher> teacher = teacherService.getTeacherAll(start, num);
		JSONArray objs = new JSONArray();

		for (Teacher tea : teacher) {
			JSONObject obj = new JSONObject();
			obj.put("t_id", tea.getT_id());
			obj.put("t_name", tea.getT_name());
			obj.put("t_sex", tea.getT_sex());
			obj.put("if_Onthejob", tea.getIf_Onthejob());
			obj.put("time_creatDate", tea.getTime_creatDate());
			obj.put("time_endDate", tea.getTime_endDate());
			obj.put("title", tea.getTitle());
			obj.put("title_updatetime", tea.getTitle_updatetime());

			objs.add(obj);
		}

		JSONObject obj_arr = new JSONObject();
		obj_arr.put("list_tea", objs);

		if (page.equals("1")) {
			obj_arr.put("all_num", all_num);
		}
		return obj_arr.toString();

	}

	/**
	 * 添加讲师
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/do_insertTeacher")
	public String do_insertTeacher(HttpServletRequest request) {
		String t_name = request.getParameter("userName");
		String t_sex = request.getParameter("sex");
		String if_Onthejob = request.getParameter("if_Onthejob");
		String time_creatDate = request.getParameter("time_creatDate");
		String time_endDate = request.getParameter("time_endDate");
		String title = request.getParameter("title");
		String title_updatetime = request.getParameter("title_updatetime");

		Teacher teacher = new Teacher();
		teacher.setT_name(t_name);
		teacher.setT_sex(Integer.valueOf(t_sex));
		teacher.setIf_Onthejob(Integer.valueOf(if_Onthejob));
		teacher.setTitle(title);

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sf.parse(time_creatDate);
			teacher.setTime_creatDate(d);
			if (time_endDate != null && time_endDate != "") {
				d = sf.parse(time_endDate);
				teacher.setTime_endDate(d);
			}
			if (title_updatetime != null && title_updatetime != "") {
				d = sf.parse(title_updatetime);
				teacher.setTitle_updatetime(d);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Integer count = teacherService.do_insertTeacher(teacher);
		JSONObject obj_arr = new JSONObject();
		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}

		return obj_arr.toString();
	}

	/**
	 * 获取讲师的ID与name
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/showTeacher")
	public String showTeacher_() {

		List<Teacher> teacher = teacherService.getTeaNameAndID();
		JSONArray jsry = new JSONArray();
		for (Teacher t : teacher) {
			// 确保该讲师在职
			if (t.getTime_endDate() != null) {
				continue;
			}
			net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
			obj.put("id", t.getT_id());
			obj.put("name", t.getT_name());
			jsry.add(obj);
		}

		return jsry.toString();
	}
	
	/**
	 * 修改讲师
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/do_updateTeacher")
	public String do_updateTeacher(HttpServletRequest request){
		String id = request.getParameter("id");
		String t_name = request.getParameter("userName");
		String t_sex = request.getParameter("sex");
		String if_Onthejob = request.getParameter("if_Onthejob");
		String time_creatDate = request.getParameter("time_creatDate");
		String time_endDate = request.getParameter("time_endDate");
		String title = request.getParameter("title");
		String title_updatetime = request.getParameter("title_updatetime");

		Teacher teacher = new Teacher();
		teacher.setT_id(id);
		teacher.setT_name(t_name);
		teacher.setT_sex(Integer.valueOf(t_sex));
		teacher.setIf_Onthejob(Integer.valueOf(if_Onthejob));
		teacher.setTitle(title);

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sf.parse(time_creatDate);
			teacher.setTime_creatDate(d);
			if (time_endDate != null && time_endDate != "") {
				d = sf.parse(time_endDate);
				teacher.setTime_endDate(d);
			}
			if (title_updatetime != null && title_updatetime != "") {
				d = sf.parse(title_updatetime);
				teacher.setTitle_updatetime(d);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Integer count = teacherService.do_updateTeacher(teacher);
		JSONObject obj_arr = new JSONObject();
		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}

		return obj_arr.toString();
		
	}
	
}
