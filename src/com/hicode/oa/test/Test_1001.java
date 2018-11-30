package com.hicode.oa.test;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hicode.oa.dao.ValidateCodeDAO;
import com.hicode.oa.service.TeacherService;
import com.hicode.oa.service.ValidateCodeService;
import com.hicode.oa.tool.Teacher;
import com.hicode.oa.tool.ValidateCode;

public class Test_1001 {
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private ValidateCodeService validateCodeService;
	
	@Test
	public void Teat_1003() {
		
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory ss = (SqlSessionFactory) app.getBean("sqlSessionFactory");
		ValidateCodeDAO dao = new ValidateCodeDAO(ss);
		List<ValidateCode> all = dao.getValidateCodeInfo(0, 40);
		for(int i=0;i<40;i++){

			System.out.println(all.get(i));
			
			
		}
		System.out.println("====");
		
	}
	@Ignore
	@Test
	public void Teat_1002() {
		
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory ss = (SqlSessionFactory) app.getBean("sqlSessionFactory");
		ValidateCodeDAO dao = new ValidateCodeDAO(ss);
		for(int i=0;i<42;i++){

			ValidateCode code = new ValidateCode();
			code.setCode_img("bg_img ("+(i+1)+").jpg");
			Integer all = dao.do_insertValidateCode(code);
			
		}
		System.out.println("====");
		
		
		
	}
	@Test
	@Ignore
	public void Teat_1001() {
		
		String id = "adv_1005";
		Integer int_id = Integer.valueOf(id.substring(4, id.length()));
		String ss = id.substring(0, 4)+(int_id+1);
		System.out.println(ss);
		
	}
	

}
