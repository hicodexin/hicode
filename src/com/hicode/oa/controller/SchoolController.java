package com.hicode.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.SchoolService;
import com.hicode.oa.tool.School;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 电话量
 * 
 * @author XinPeiXiang 2018-11-26
 *
 */
@Controller
@RequestMapping("/school")
public class SchoolController {
	
	@Autowired
	private SchoolService schoolService;

	/**
	 * 获取学校的ID与name
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/showSchool", method = RequestMethod.POST)
	public String showSchool() {

		List<School> school = schoolService.getSchoolByInfo();
		JSONArray jsry = new JSONArray();
		for (School t : school) {
			JSONObject obj = new JSONObject();
			obj.put("id", t.getSch_id());
			obj.put("name", t.getSch_name());
			jsry.add(obj);
		}

		return jsry.toString();
	}
}
