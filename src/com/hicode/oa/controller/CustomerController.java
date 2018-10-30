package com.hicode.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.CustomerService;
import com.hicode.oa.tool.Customer;

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
		List<Customer> advs = customerService.getCustomerAll(s, e);
		
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
		
		return obj_arr.toString();
	}
	
	
	@RequestMapping(value="/showCustomer")
	public String showCustomer(){
		System.out.println("==========okk============");
		Customer adv = customerService.getCustomerByID(3);
		
		List<Customer> advs = customerService.getCustomerAll(0, 30);
		System.out.println("==========ok1============");
		
		return "/welcome.html";
	}
	
}
