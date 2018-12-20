package com.hicode.oa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.DepositService;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Auditions;
import com.hicode.oa.tool.Deposit;
import com.hicode.oa.tool.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/deposit")
public class DepositController {
	
	@Autowired
	private DepositService depositService;

	@RequestMapping(value = "/to_login")
	public String logAction(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非管理员用户
		if (obj.getUserType().getType_leibie() != 3) {
			return "redirect:/Fighting.html";
		}
		return "/WEB-INF/ManagerPage/deposit.html";
	}
	
	@ResponseBody
	@RequestMapping(value = "/showDepositByInfo", method = RequestMethod.POST)
	public String showDepositByInfo(HttpServletRequest request) {
		// 页码
		String page = request.getParameter("page");

		// 开始数字
		Integer start = 0;
		// 每页显示条数
		Integer num = 20;

		if (page != null) {
			start = (Integer.valueOf(page) - 1) * 20;
		}

		Integer all_num = null;
		if (page.equals("1")) {
			all_num = depositService.getCustomerForCount();

			if (all_num != null) {
				all_num = (all_num % 20 == 0) ? (all_num / 20) : (all_num / 20 + 1);
			}

		}
		List<Deposit> advs = depositService.getDepositInfo(start, num);

		JSONArray objs = new JSONArray();

		for (Deposit adv1 : advs) {
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getDep_id());
			obj.put("name", adv1.getAuditions().getSt_name());
			obj.put("pay_time", adv1.getPay_time());
			obj.put("num", adv1.getMoney());
			obj.put("tel", adv1.getPhone());
			obj.put("qianyue", adv1.getAdviser().getAdv_name());
			obj.put("yaoyue", adv1.getAdviser2().getAdv_name());
			obj.put("if_ok", adv1.getIfSignup());
			obj.put("tuimoney", adv1.getRefundMoney());
			obj.put("end_time", adv1.getRefund_time());
			obj.put("beizhu", adv1.getRemark());
			objs.add(obj);
		}
		JSONObject obj_arr = new JSONObject();
		obj_arr.put("list_advs", objs);

		if (page.equals("1")) {
			obj_arr.put("all_num", all_num);
		}

		return obj_arr.toString();
	}

	/**
	 * 添加交押金学员
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/do_insertDeposit", method = RequestMethod.POST)
	public String do_insertDeposit(HttpServletRequest request) {
		String aud_id = request.getParameter("userName");
		String pay_time = request.getParameter("pay_time");
		String money = request.getParameter("money");
		String phone = request.getParameter("phone");
		
		String adv_id = request.getParameter("adv_id");
		String adv_id2 = request.getParameter("adv_id2");
		String ifsignup = request.getParameter("ifsignup");
		String refundmoney = request.getParameter("refundmoney");
		String refund_time = request.getParameter("refund_time");
		String remarks = request.getParameter("remarks");
		
		Deposit deposit = new Deposit();
		
		Auditions auditions = new Auditions();
		auditions.setAu_id(Integer.valueOf(aud_id));

		Adviser adviser = new Adviser();
		adviser.setAdv_id(adv_id);
		Adviser adviser2 = new Adviser();
		adviser2.setAdv_id(adv_id2);

		deposit.setAuditions(auditions);
		deposit.setMoney(Float.valueOf(money));
		deposit.setPhone(phone);
		deposit.setAdviser(adviser);
		deposit.setAdviser2(adviser2);
		deposit.setIfSignup(Integer.valueOf(ifsignup));
		deposit.setRefundMoney(Float.valueOf(refundmoney));
		deposit.setRemark(remarks);

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sf.parse(pay_time);
			deposit.setPay_time(d);
			if(refund_time != null && refund_time != ""){
				d = sf.parse(refund_time);
				deposit.setRefund_time(d);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer count = depositService.do_insertDeposit(deposit);
		JSONObject obj_arr = new JSONObject();
		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}

		return obj_arr.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/do_updateDeposit", method = RequestMethod.POST)
	public String do_updateDeposit(HttpServletRequest request) {
		String id = request.getParameter("id");
		String aud_id = request.getParameter("userName");
		String pay_time = request.getParameter("pay_time");
		String money = request.getParameter("money");
		String phone = request.getParameter("phone");
		
		String adv_id = request.getParameter("adv_id");
		String adv_id2 = request.getParameter("adv_id2");
		String ifsignup = request.getParameter("ifsignup");
		String refundmoney = request.getParameter("refundmoney");
		String refund_time = request.getParameter("refund_time");
		String remarks = request.getParameter("remarks");
		
		Deposit deposit = new Deposit();
		
		Auditions auditions = new Auditions();
		auditions.setAu_id(Integer.valueOf(aud_id));

		Adviser adviser = new Adviser();
		adviser.setAdv_id(adv_id);
		Adviser adviser2 = new Adviser();
		adviser2.setAdv_id(adv_id2);

		deposit.setDep_id(Integer.valueOf(id));
		deposit.setAuditions(auditions);
		deposit.setMoney(Float.valueOf(money));
		deposit.setPhone(phone);
		deposit.setAdviser(adviser);
		deposit.setAdviser2(adviser2);
		deposit.setIfSignup(Integer.valueOf(ifsignup));
		deposit.setRefundMoney(Float.valueOf(refundmoney));
		deposit.setRemark(remarks);

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sf.parse(pay_time);
			deposit.setPay_time(d);
			if(refund_time != null && refund_time != ""){
				d = sf.parse(refund_time);
				deposit.setRefund_time(d);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer count = depositService.do_updateDeposit(deposit);
		JSONObject obj_arr = new JSONObject();
		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}

		return obj_arr.toString();
	}

	
	
	
	
	
}
