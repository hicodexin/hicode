package com.hicode.oa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.CustomerService;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Auditions;
import com.hicode.oa.tool.Customer;
import com.hicode.oa.tool.Subject;
import com.hicode.oa.tool.Teacher;
import com.hicode.oa.tool.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 报名学员信息
 * @author XinPeiXiang
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/to_login")
	public String login(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非会员用户、管理员用户、超级管理员、课程顾问
		if (
				obj.getUserType().getType_leibie() != 3 && 
				obj.getUserType().getType_leibie() != 2 && 
				obj.getUserType().getType_leibie() != 5 && 
				obj.getUserType().getType_leibie() != 6
				) {
			return "redirect:/Fighting.html";
		}
		return "/WEB-INF/VisitorsPage/Customer.html";
	}

	@ResponseBody
	@RequestMapping(value = "/showCustomerByInfo", method = RequestMethod.POST)
	public String showCustomerByInfo(HttpServletRequest request) 	{

		Map<String, Object> map = new HashMap<String, Object>();

		String stu_name = request.getParameter("stu_name");
		if (stu_name != null & stu_name != "") {
			map.put("stu_name", "%" + stu_name + "%");
		}
		String stu_subject = request.getParameter("stu_subject");
		if (stu_subject != null & stu_subject != "") {
			map.put("stu_subject", stu_subject);
		}
		
		String stu_period = request.getParameter("stu_period");
		if (stu_period != null & stu_period != "") {
			map.put("stu_period", stu_period);
		}
		
		String stu_teacher = request.getParameter("stu_teacher");
		if (stu_teacher != null & stu_teacher != "") {
			map.put("stu_teacher", stu_teacher);
		}
		String kai_time = request.getParameter("kai_time");
		if (kai_time != null & kai_time != "") {
			map.put("kai_time", kai_time);
		}
		String ting_time = request.getParameter("ting_time");
		if (ting_time != null & ting_time != "") {
			map.put("ting_time", ting_time);
		}
		
		String qian_gu = request.getParameter("qian_gu");
		if (qian_gu != null & qian_gu != "") {
			map.put("qian_gu", qian_gu);
		}
		
		String if_xufei = request.getParameter("if_xufei");
		if (if_xufei != null & if_xufei != "") {
			map.put("if_xufei", if_xufei);
		}

		// 页码
		String page = request.getParameter("page");
		// 开始数字
		Integer start = 0;
		// 每页显示条数
		Integer num = 30;

		if (page != null) {
			start = (Integer.valueOf(page) - 1) * 30;
		}

		Integer all_num = null;

		map.put("start", start);
		map.put("count", num);

		JSONObject obj_arr = new JSONObject();

		List<Customer> advs = customerService.getCustomerBySomeOption(map);

		JSONArray objs = new JSONArray();

		for (Customer adv1 : advs) {
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getEr_id());
			obj.put("name", adv1.getAuditions().getSt_name());
			obj.put("kemu", adv1.getSubject().getSub_name());
			obj.put("period", adv1.getPeriod());
			obj.put("money", adv1.getMoney());
			obj.put("giveClass", adv1.getGiveClass());
			obj.put("xufei", adv1.getIf_renewal());
			obj.put("qianyue", adv1.getAdviser().getAdv_name());
			obj.put("tea_name", adv1.getTeacher().getT_name());
			obj.put("firsttime", adv1.getFirst_time());
			obj.put("over", adv1.getIf_done());
			obj.put("tuifei", adv1.getIf_refund());
			obj.put("beizhu", adv1.getRemarks());
			objs.add(obj);
		}
		obj_arr.put("list_advs", objs);
		
		if (page.equals("1")) {

			all_num = customerService.getCustomerForCountBySomeOption(map);

			if (all_num != null) {
				all_num = (all_num % 30 == 0) ? (all_num / 30) : (all_num / 30 + 1);
			}
			obj_arr.put("all_num", all_num);
		}
		return obj_arr.toString();
	}


	/**
	 * 添加报名学员
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/do_insertCustomer", method = RequestMethod.POST)
	public String do_insertCustomer(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 只有管理员具有添加权限
		if (obj.getUserType().getType_leibie() != 3 && obj.getUserType().getType_leibie() != 6) {
			obj_arr.put("list_advs", "ok1");
			return obj_arr.toString();
		}
		
		String aud_id = request.getParameter("userName");
		String sub_id = request.getParameter("subject");
		String period = request.getParameter("period");
		
		String money = request.getParameter("money");
		String giveClass = request.getParameter("giveClass");
		String if_renewal = request.getParameter("if_renewal");

		String t_id = request.getParameter("the_teacher");
		String first_time = request.getParameter("first_time");

		String adv_id = request.getParameter("adviser_sel");
		String remarks = request.getParameter("remarks");

		Customer customer = new Customer();

		Auditions auditions = new Auditions();
		auditions.setAu_id(Integer.valueOf(aud_id));

		Subject subject = new Subject();
		subject.setSub_id(sub_id);

		Teacher teacher = new Teacher();
		teacher.setT_id(t_id);

		Adviser adviser = new Adviser();
		adviser.setAdv_id(adv_id);

		customer.setAuditions(auditions);
		customer.setSubject(subject);
		customer.setPeriod(Integer.valueOf(period));
		customer.setMoney(Float.valueOf(money));
		customer.setGiveClass(Integer.valueOf(giveClass));
		customer.setIf_renewal(Integer.valueOf(if_renewal));
		customer.setTeacher(teacher);

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d;
		try {
			d = sf.parse(first_time);
			customer.setFirst_time(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customer.setAdviser(adviser);
		customer.setRemarks(remarks);
		customer.setIf_done(0);
		customer.setIf_refund(0);

		Integer count = customerService.do_insertCustomer(customer);
		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}

		return obj_arr.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/do_updateCustomer", method = RequestMethod.POST)
	public String do_updateCustomer(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 只有管理员具有修改权限
		if (obj.getUserType().getType_leibie() != 3 && obj.getUserType().getType_leibie() != 6) {
			obj_arr.put("list_advs", "ok1");
			return obj_arr.toString();
		}
		
		String id = request.getParameter("id");
		String aud_id = request.getParameter("userName");
		String sub_id = request.getParameter("subject");
		String period = request.getParameter("period");
		
		String money = request.getParameter("money");
		String giveClass = request.getParameter("giveClass");
		String if_renewal = request.getParameter("if_renewal");

		String t_id = request.getParameter("the_teacher");
		String first_time = request.getParameter("first_time");

		String adv_id = request.getParameter("adviser_sel");
		String if_done = request.getParameter("if_done");
		String if_refund = request.getParameter("if_refund");
		String remarks = request.getParameter("remarks");

		Customer customer = new Customer();
		customer.setEr_id(Integer.valueOf(id));

		Auditions auditions = new Auditions();
		auditions.setAu_id(Integer.valueOf(aud_id));

		Subject subject = new Subject();
		subject.setSub_id(sub_id);

		Teacher teacher = new Teacher();
		teacher.setT_id(t_id);

		Adviser adviser = new Adviser();
		adviser.setAdv_id(adv_id);

		customer.setAuditions(auditions);
		customer.setSubject(subject);
		customer.setPeriod(Integer.valueOf(period));
		customer.setMoney(Float.valueOf(money));
		customer.setGiveClass(Integer.valueOf(giveClass));
		customer.setIf_renewal(Integer.valueOf(if_renewal));
		customer.setTeacher(teacher);

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d;
		try {
			d = sf.parse(first_time);
			customer.setFirst_time(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customer.setAdviser(adviser);
		customer.setRemarks(remarks);
		customer.setIf_done(Integer.valueOf(if_done));
		customer.setIf_refund(Integer.valueOf(if_refund));

		Integer count = customerService.do_updateCustomer(customer);
		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}

		return obj_arr.toString();
	}

}
