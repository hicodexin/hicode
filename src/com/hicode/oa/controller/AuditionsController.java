package com.hicode.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hicode.oa.service.AdviserService;
import com.hicode.oa.service.AuditionsService;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Auditions;

/**
 * 试听课详情控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/auditions")
public class AuditionsController {

	@Autowired
	private AuditionsService auditionsService;
	
	@RequestMapping(value="/showAuditions")
	public String showAuditions(){
		System.out.println("==========ok============");
		Auditions adv = auditionsService.getAuditionsByID(new Integer(2));
		System.out.println(adv.getAu_id()+" : "+adv.getSt_name() );
		
		List<Auditions> advs = auditionsService.getAuditionsAll(0, 30);
		System.out.println("==========ok1============");
		for (Auditions adv1 : advs) {
			System.out.println(adv1.getAu_id()+" : "+adv1.getSt_name());
		}
		
		return "/welcome.html";
	}
	
	
	
}
