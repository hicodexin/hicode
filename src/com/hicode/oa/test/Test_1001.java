package com.hicode.oa.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hicode.oa.dao.UserInfoDAO;
import com.hicode.oa.dao.UserStateDAO;
import com.hicode.oa.dao.UserTypeDAO;
import com.hicode.oa.dao.ValidateCodeDAO;
import com.hicode.oa.service.TeacherService;
import com.hicode.oa.service.ValidateCodeService;
import com.hicode.oa.tool.Teacher;
import com.hicode.oa.tool.UserInfo;
import com.hicode.oa.tool.UserState;
import com.hicode.oa.tool.UserType;
import com.hicode.oa.tool.ValidateCode;

public class Test_1001 {
	
	
	@Test
	public void Teat_1007() {
		
		String[] abc ={"w","q","e","ww","rrr","qqq","rrrrr","wwwww"};
		for(String s:abc){
			
			if(s.equals("rrr")){
				System.out.println("=====");
				break;
			}
			System.out.println("ppp = "+s);
		}
		
	}
	@Ignore
	@Test
	public void Teat_1006() {
		
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory ss = (SqlSessionFactory) app.getBean("sqlSessionFactory");
		
		UserInfoDAO dao = new UserInfoDAO(ss);
		
		UserInfo u1 = new UserInfo();
		u1.setUser_id("u_1001");
		u1.setUser_name("唐僧");
		u1.setUser_pwd("123456");
		u1.setCreate_date(new Date());
		 UserState dd = new UserState();
		 dd.setState_id(1);
		u1.setUserState(dd);
		
		UserType pp = new UserType();
		pp.setType_id(4);
		u1.setUserType(pp);
		
		Integer tt = dao.do_insertUserInfo(u1);
		System.out.println("===="+tt);
		
	}
	@Ignore
	@Test
	public void Teat_1005() {
		
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory ss = (SqlSessionFactory) app.getBean("sqlSessionFactory");
		UserTypeDAO dao = new UserTypeDAO(ss);
		
		UserType userType = new UserType();
		userType.setType_leibie(0);
		userType.setType_remarks("游客");
		
		UserType userType2 = new UserType();
		userType2.setType_leibie(1);
		userType2.setType_remarks("普通用户");
		
		UserType userType3 = new UserType();
		userType3.setType_leibie(2);
		userType3.setType_remarks("会员用户");
		
		UserType userType4 = new UserType();
		userType4.setType_leibie(3);
		userType4.setType_remarks("管理员");
		
		Integer tt = dao.do_insertUserType(userType);
		dao.do_insertUserType(userType2);
		dao.do_insertUserType(userType3);
		dao.do_insertUserType(userType4);
		System.out.println("===="+tt);
		
	}
	
	@Ignore
	@Test
	public void Teat_1004() {
		
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory ss = (SqlSessionFactory) app.getBean("sqlSessionFactory");
		UserStateDAO dao = new UserStateDAO(ss);
		
		UserState userState = new UserState();
		userState.setState_type(1);
		userState.setState_remarks("已锁定");
		
		UserState userState2 = new UserState();
		userState2.setState_type(2);
		userState2.setState_remarks("已废弃");
		
		
		Integer tt = dao.do_insertUserState(userState );
		Integer tt2 = dao.do_insertUserState(userState2 );
		System.out.println("===="+tt);
		
	}
	@Ignore
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
