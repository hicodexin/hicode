package com.hicode.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hicode.oa.service.AdviserService;
import com.hicode.oa.tool.Adviser;

/**
 * 顾问控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/adviser")
public class AdviserController {

	@Autowired
	private AdviserService adviserService;
	
	@RequestMapping("/showAdviser")
	public String showAdviser(){
		System.out.println("==========ok============");
		Adviser adv = adviserService.getAdviserByID("adv_1001");
		System.out.println(adv.getAdv_id()+" : "+adv.getAdv_name() );
		
		List<Adviser> advs = adviserService.getAdviserAll(0, 4);
		System.out.println("==========ok1============");
		for (Adviser adv1 : advs) {
			System.out.println(adv1.getAdv_id()+" : "+adv1.getAdv_name() );
		}
		
		return "/welcome.html";
	}
	
	
	
}
