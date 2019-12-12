package com.hicode.oa.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hicode.oa.service.TeacherService;
import com.hicode.oa.tool.Teacher;

public class Test_Teacher {
	@Autowired
	private TeacherService teacherService;
	
	@Test
	public void Teat_1001() {
		
		List<Teacher> teacher = teacherService.getTeacherAll(0, 10);
		
		for (Teacher t : teacher) {
			System.out.println(t.getT_id()+" : "+t.getT_name());
		}
		
	}
	
	public static void main(String[] args) {
		
		String a = "12";
		
		System.out.println(Integer.valueOf(a));
		
	}
	

}
