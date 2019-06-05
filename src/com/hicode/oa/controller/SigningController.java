package com.hicode.oa.controller;

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

import com.hicode.oa.service.AdviserService;
import com.hicode.oa.service.SigningService;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Auditions;
import com.hicode.oa.tool.Signing;
import com.hicode.oa.tool.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 跟单详情
 * 
 * @author XinPeiXiang 2019-05-09
 *
 */
@Controller
@RequestMapping("/signing")
public class SigningController {

	@Autowired
	private SigningService signingService;
	@Autowired
	private AdviserService adviserService;
	
	
	@RequestMapping("/to_login")
	public String login(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非课程顾问
		if (obj.getUserType().getType_leibie() != 5) {
			return "redirect:/Fighting.html";
		}
		
		return "/WEB-INF/AdviserPage/genzong.html";
	}
	
	@RequestMapping("/login_done")
	public String login_done(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非课程顾问
		if (
				obj.getUserType().getType_leibie() != 5 && 
				obj.getUserType().getType_leibie() != 6
				) {
			
			return "redirect:/Fighting.html";
		}
		
		return "/WEB-INF/AdviserPage/genzong_success.html";
	}

	@ResponseBody
	@RequestMapping(value = "/showSigningByInfo", method = RequestMethod.POST)
	public String showSigningByInfo(HttpServletRequest request) 	{

		Map<String, Object> map = new HashMap<String, Object>();

		//sig_id,au_id,situation,category,tracking,if_signup,adv_id,history
		//stu_name,stu_school,stu_class,phone,category,if_signup,adv_id
		String stu_name = request.getParameter("stu_name");
		if (stu_name != null & stu_name != "") {
			map.put("stu_name", "%" + stu_name + "%");
		}
		
		String stu_school = request.getParameter("stu_school");
		if (stu_school != null & stu_school != "") {
			map.put("stu_school", stu_school);
		}
		
		String stu_class = request.getParameter("stu_class");
		if (stu_class != null & stu_class != "") {
			map.put("stu_class", stu_class);
		}
		
		String phone = request.getParameter("phone");
		if (phone != null & phone != "") {
			map.put("phone", phone);
		}
		//客户类型
		String category = request.getParameter("category");
		if (category != null & category != "") {
			map.put("category", category);
		}
		
		//当前接单人
		HttpSession session = request.getSession();
		UserInfo obj_user = (UserInfo) session.getAttribute("user");
		if (
				obj_user == null ||
				(obj_user.getUserType().getType_leibie() != 5 && 
				obj_user.getUserType().getType_leibie() != 6)
				) {
			return null;
		}
		String adv_now_id = obj_user.getUser_name();
		map.put("adv_now_id", adv_now_id);
		
		//是否报名
		String if_signup = request.getParameter("if_signup");
		if (if_signup != null & if_signup != "") {
			map.put("if_signup", if_signup);
			//看签单成功的，不需要只看自己的
			if(if_signup.equals("1")){
				map.remove("adv_now_id");
			}
		}
		
		//最终签单顾问
		String qian_gu = request.getParameter("qian_gu");
		if (qian_gu != null & qian_gu != "") {
			map.put("adv_success_id", qian_gu);
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
		
		List<Signing> advs = signingService.getSigningBySomeOption(map);

		JSONArray objs = new JSONArray();

		for (Signing adv1 : advs) {
			
			JSONObject obj = new JSONObject();
			obj.put("id", adv1.getSig_id());
			obj.put("name", adv1.getAuditions().getSt_name());
			if(null != adv1.getAuditions().getSchool()){
				obj.put("school", adv1.getAuditions().getSchool().getSch_name());
			}else{
				obj.put("school", "未知");
			}
			obj.put("nianji", adv1.getAuditions().getSt_class());
			obj.put("phone", adv1.getAuditions().getPhone());
			obj.put("begin_time", adv1.getAuditions().getSt_time());//接单时间
			obj.put("firstRemark", adv1.getSituation());//当天面资情况
			obj.put("fenlei", adv1.getCategory());//用户分类
			obj.put("zhuizong_01", adv1.getTracking_one());//第一次跟踪细则
			obj.put("zhuizong_02", adv1.getTracking_two());//第二次跟踪细则
			obj.put("zhuizong_03", adv1.getTracking_three());//第三次跟踪细则
			obj.put("if_signup", adv1.getIf_signup());//是否报名（0:未报名；1:已报名；2:死单）
			obj.put("firstPeople", adv1.getAdviser().getAdv_name());//第一接单人
			obj.put("nowPeople", adv1.getAdviser_now().getAdv_name());//当前接单人
			obj.put("successPeople", adv1.getAdv_success_id().getAdv_name());//最终签单人
			objs.add(obj);
		}
		obj_arr.put("list_advs", objs);
		
		if (page.equals("1")) {

			all_num = signingService.getSigningForCountBySomeOption(map);

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
	@RequestMapping(value = "/do_insertSigning", method = RequestMethod.POST)
	public String do_insertSigning(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 只有课程顾问具有添加权限
		if (obj.getUserType().getType_leibie() != 5) {
			obj_arr.put("list_advs", "ok1");
			return obj_arr.toString();
		}
		
		String aud_id = request.getParameter("userName");
		String leibie_sel = request.getParameter("leibie_sel");
		String qiandan_sel = request.getParameter("qiandan_sel");
		
		String adviser_sel = request.getParameter("adviser_sel");
		Adviser adviser = new Adviser();
		adviser.setAdv_id(adviser_sel);
		
		
		String dang_tian = request.getParameter("dang_tian");
		String first_time = request.getParameter("first_time");
		String second_time = request.getParameter("second_time");
		String third_time = request.getParameter("third_time");

		//sig_id,au_id,situation,category,tracking_one,tracking_two,tracking_three,if_signup,adv_id,history
		
		Signing signing = new Signing();
		
		
		Integer num = signingService.getSigningBy_AuditionsID(Integer.valueOf(aud_id));
		//该学员信息已存在
		if(num>0){
			obj_arr.put("list_advs", "ok2");
			return obj_arr.toString();
		}
		
		Auditions auditions = new Auditions();
		auditions.setAu_id(Integer.valueOf(aud_id));
		
		signing.setAuditions(auditions);
		signing.setSituation(dang_tian);//面资当天
		signing.setCategory(Integer.valueOf(leibie_sel));//用户分类
		signing.setIf_signup(Integer.valueOf(qiandan_sel));//签单/死单
		if(qiandan_sel != null && qiandan_sel.equals("1")){//以报名
			Adviser adviser_success = new Adviser();
			adviser_success.setAdv_id(adviser_sel);
			signing.setAdv_success_id(adviser_success);
		}
		signing.setTracking_one(first_time);
		signing.setTracking_two(second_time);
		signing.setTracking_three(third_time);
		signing.setAdviser(adviser);
		signing.setAdviser_now(adviser);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = sf.format(new Date());
		Adviser adviser2 = adviserService.getAdviserByID(adviser_sel);
		signing.setHistory(d+" : "+adviser2.getAdv_name());
		
		Integer count = signingService.do_insertSigning(signing);
		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}
		
		return obj_arr.toString();
	}

	
	/**
	 * 修改追踪学员细则
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/do_updateSigning", method = RequestMethod.POST)
	public String do_updateSigning(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 只有课程顾问具有修改权限
		if (obj.getUserType().getType_leibie() != 5) {
			obj_arr.put("list_advs", "ok1");
			return obj_arr.toString();
		}
		String sig_id = request.getParameter("id");
		String leibie_sel = request.getParameter("leibie_sel");
		String qiandan_sel = request.getParameter("qiandan_sel");
		
		
		String adviser_sel = request.getParameter("adviser_sel");
		
		String dang_tian = request.getParameter("dang_tian");
		String first_time = request.getParameter("first_time");
		String second_time = request.getParameter("second_time");
		String third_time = request.getParameter("third_time");

		//sig_id,au_id,situation,category,tracking_one,tracking_two,tracking_three,if_signup,adv_id,history
		
		Signing signing = new Signing();
		
		
		signing.setSig_id(Integer.valueOf(sig_id));
		signing.setSituation(dang_tian);//面资当天
		signing.setCategory(Integer.valueOf(leibie_sel));//用户分类
		signing.setIf_signup(Integer.valueOf(qiandan_sel));//签单/死单
		signing.setTracking_one(first_time);
		signing.setTracking_two(second_time);
		signing.setTracking_three(third_time);
		
		if(qiandan_sel != null && qiandan_sel.equals("1")){//以报名
			Adviser adviser_success = new Adviser();
			adviser_success.setAdv_id(adviser_sel);
			signing.setAdv_success_id(adviser_success);
		}
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = sf.format(new Date());
		Adviser adviser2 = adviserService.getAdviserByID(adviser_sel);
		signing.setHistory(d+" : "+adviser2.getAdv_name());
		Integer count = signingService.do_updateSigning(signing);
		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}
		
		return obj_arr.toString();
	}

		
	
}
