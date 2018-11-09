package com.hicode.oa.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hicode.oa.service.TeacherService;
import com.hicode.oa.tool.Teacher;

public class Test_1001 {
	@Autowired
	private TeacherService teacherService;
	
	@Test
	public void Teat_1001() {
		
		String id = "adv_1005";
		Integer int_id = Integer.valueOf(id.substring(4, id.length()));
		String ss = id.substring(0, 4)+(int_id+1);
		System.out.println(ss);
		
	}
	

}
