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

import com.hicode.oa.service.CustomerService;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Auditions;
import com.hicode.oa.tool.Customer;
import com.hicode.oa.tool.Subject;
import com.hicode.oa.tool.Teacher;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/to_login")
	public String login(){
		System.out.println("--------------------");
		return "/WEB-INF/VisitorsPage/Customer.html";
	}
	
	@ResponseBody
	@RequestMapping(value="/showCustomerByInfo")
	public String showCustomerByInfo(HttpServletRequest request){
		//页码
		String page = request.getParameter("page");
		
		//开始数字
		Integer start = 0;
		//每页显示条数
		Integer num = 10;
		
		if(page != null){
			start = (Integer.valueOf(page)-1)*10;
		}
		
		Integer all_num = null;
		if(page.equals("1")){
			all_num = customerService.getCustomerForCount();
			
			if(all_num != null){
				all_num = (all_num%10==0)?(all_num/10):(all_num/10+1);
			}
			
		}
		List<Customer> advs = customerService.getCustomerAll(start, num);
		
		JSONArray objs = new JSONArray();
		
		for (Customer adv1 : advs) {
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getEr_id());
			obj.put("name", adv1.getAuditions().getSt_name());
			obj.put("kemu", adv1.getSubject().getSub_name());
			obj.put("period", adv1.getPeriod());
			obj.put("xufei", adv1.getIf_renewal());
			obj.put("tea_name", adv1.getTeacher().getT_name());
			obj.put("firsttime", adv1.getFirst_time());
			obj.put("over", adv1.getIf_done());
			obj.put("tel", adv1.getPhone());
			obj.put("tuifei", adv1.getIf_refund());
			obj.put("beizhu", adv1.getRemarks());
			objs.add(obj);
		}
		JSONObject obj_arr = new JSONObject();
		obj_arr.put("list_advs", objs);
		
		if(page.equals("1")){
			obj_arr.put("all_num", all_num);
		}
		
		return obj_arr.toString();
	}
	
	/**
	 * 添加报名学员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/do_insertCustomer")
	public String do_insertCustomer(HttpServletRequest request){
		String aud_id = request.getParameter("userName");
		String sub_id = request.getParameter("subject");
		String period = request.getParameter("title");
		String if_renewal = request.getParameter("title");
		String t_id = request.getParameter("title");
		String first_time = request.getParameter("time_creatDate");
		String if_done = request.getParameter("if_Onthejob");
		
		String phone = request.getParameter("time_endDate");
		String if_refund = request.getParameter("title_updatetime");
		String remarks = request.getParameter("time_endDate");
		
		Customer customer = new Customer();
		
		Auditions auditions = new Auditions();
		auditions.setAu_id(Integer.valueOf(aud_id));
		
		Subject subject = new Subject();
		subject.setSub_id(sub_id);
		
		Teacher teacher = new Teacher();
		teacher.setT_id(t_id);
		
		customer.setAuditions(auditions);
		customer.setSubject(subject);
		customer.setPeriod(Integer.valueOf(period));
		customer.setIf_renewal(Integer.valueOf(if_renewal));
		customer.setTeacher(teacher);

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sf.parse(first_time);
			customer.setFirst_time(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customer.setIf_done(Integer.valueOf(if_done));
		customer.setPhone(phone);
		customer.setIf_refund(Integer.valueOf(if_refund));
		customer.setRemarks(remarks);
		
		Integer count = 1;
		JSONObject obj_arr = new JSONObject();
		if(count>0){
			obj_arr.put("list_advs", "ok");
		}
		
		return obj_arr.toString();
	}
	
	@RequestMapping(value="/showCustomer")
	public String showCustomer(){
		
		return "/welcome.html";
	}
	
}
