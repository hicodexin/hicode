package com.hicode.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hicode.oa.service.CustomerService;
import com.hicode.oa.tool.Auditions;
import com.hicode.oa.tool.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/showCustomer")
	public String showCustomer(){
		System.out.println("==========okk============");
		Customer adv = customerService.getCustomerByID(3);
		System.out.println(adv.getAu_id()+" : "+adv.getPhone()+" : "+adv.getT_id() );
		
		List<Customer> advs = customerService.getCustomerAll(0, 30);
		System.out.println("==========ok1============");
		for (Customer adv1 : advs) {
			System.out.println(adv1.getAu_id()+" : "+adv1.getPhone()+" : "+adv1.getT_id() );
		}
		
		return "/welcome.html";
	}
	
}
