package com.hicode.oa.test;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hicode.oa.controller.DepositController;
import com.hicode.oa.dao.TeacherDAO;
import com.hicode.oa.tool.Teacher;

public class Test_Connection {
	
	private static final String nameSpace = Teacher.class.getName();


	public static void main(String[] args) {
		
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		/*
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(Test_Connection.class.getResourceAsStream("/configuration.xml"));
		
		SqlSession session = factory.openSession();
		
		
		System.out.println(session);
		
		session.close();
		*/
		
		System.out.println("abc"+nameSpace);
		
		SqlSessionFactory factory = (SqlSessionFactory) app.getBean("sqlSessionFactory");
		
		/*DataSource dataSource = (DataSource)app.getBean("dataSource");
		
		System.out.println(dataSource);
		
		SqlSessionTemplate template = new SqlSessionTemplate(factory);
		Teacher tea = template.selectOne("com.hicode.oa.tool.Teacher.getTeacherByID", "t_1001");
		
		System.out.println(template);
		*/
		TeacherDAO dao =  (TeacherDAO) app.getBean("teacherDAO");
		
//		Teacher tea = dao.getTeacherByID("t_1004");
//		System.out.println(tea.getT_id()+" : "+tea.getT_name()+" : "+tea.getTitle() );
//	

		
		
		
		
	
	}
	
}
