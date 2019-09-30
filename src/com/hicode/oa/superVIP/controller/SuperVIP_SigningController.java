package com.hicode.oa.superVIP.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.AdviserService;
import com.hicode.oa.service.SigningService;
import com.hicode.oa.service.UserInfoService;
import com.hicode.oa.tool.Adviser;
import com.hicode.oa.tool.Signing;
import com.hicode.oa.tool.SymmetricEncoder;
import com.hicode.oa.tool.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 超级管理员_课程顾问跟进细则
 * 
 * @author XinPeiXiang 2019-04-30
 *
 */
@Controller
@RequestMapping("/svipSigning")
public class SuperVIP_SigningController {
	
	@Autowired
	private SigningService signingService;
	@Autowired
	private AdviserService adviserService;
	@Autowired
	private UserInfoService userInfoService;
	

	@ResponseBody
	@RequestMapping(value = "/postUrl" , method = RequestMethod.POST)
	public String postUrl(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非超级管理员
		if (obj.getUserType().getType_leibie() != 6) {
			return "redirect:/Fighting.html";
		}
		
		JSONObject obj_arr = new JSONObject();
		
		JSONArray jsar = new JSONArray();
		
		String uid = null;
		for(int i=15;i<=24;i++){
			JSONObject jsob = new JSONObject();
			uid = "us_10"+i;
			UserInfo userInfo = userInfoService.getUserInfoByID(uid);
			if(userInfo == null ){ 
				continue;
			}
			String remark =	userInfo.getRemarks();
			if(remark==null || remark == ""){
				remark = userInfo.getUser_name();
			}
//			System.out.println("用户id：" + uid);
			String date = new SimpleDateFormat("yyyy+MM+dd").format(new Date());
			String key = UUID.randomUUID().toString() + "@" + uid + "@" + date + "@"+SymmetricEncoder.getSymbol();
//			System.out.println(key);
			String encodedKey = SymmetricEncoder.invokeEncryptEncode(key);
//			System.out.println("加密后的秘钥：" + encodedKey);
			String url01 = "/hicode/svipSigning/openSomeADVList.spc?name=";
			jsob.put("urls", url01+encodedKey);
			jsob.put("beizhu", remark);
			jsar.add(jsob);
		}
		obj_arr.put("giveUrl", jsar);
		return obj_arr.toString();
	}
	
	@RequestMapping("/openSomeADVList")
	public String openSomeTMKList(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");
		// 非超级管理员
		if (obj.getUserType().getType_leibie() != 6) {
			return "redirect:/Fighting.html";
		}
		
		String adv_name = request.getParameter("name");
		if (adv_name != null & adv_name != "") {
			
			String encodedKey = adv_name;
			String key1 = null;
			try{
				key1 = SymmetricEncoder.invokeDecryptEncode(encodedKey);
			}catch(Exception e){
				return "redirect:/Fighting.html";
			}
			
			if(key1 == null){
				return "redirect:/Fighting.html";
			}
//			System.out.println("解密后的秘钥：" + key1);
//			System.out.println("用户id为：" + key1.split("@")[1]);
			
			String user_id = key1.split("@")[1];
			UserInfo info = userInfoService.getUserInfoByID(user_id);
			if(info == null){
				return "redirect:/Fighting.html";
			}
			adv_name = info.getUser_name();
			session.setAttribute("adv_name", adv_name);
		}
		return "/WEB-INF/SuperVIP/VIP_ADV.html";
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
		//是否报名
		String if_signup = request.getParameter("if_signup");
		if (if_signup != null & if_signup != "") {
			map.put("if_signup", if_signup);
		}
		
		//当前接单人
		HttpSession session = request.getSession();
		UserInfo obj_user = (UserInfo) session.getAttribute("user");
		if (obj_user == null ||obj_user.getUserType().getType_leibie() != 6) {
			return null;
		}
		
		Object adv_name = session.getAttribute("adv_name");
		if (adv_name != null & adv_name != "") {
			map.put("adv_now_id", adv_name);
		}else{
			System.out.println("adv id is null....");
			return null;
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
			obj.put("fenlei", adv1.getCategory());//用户分类
			obj.put("if_signup", adv1.getIf_signup());//是否报名（0:未报名；1:已报名；2:死单）
			obj.put("firstPeople", adv1.getAdviser().getAdv_name());//第一接单人
			obj.put("nowPeople", adv1.getAdviser_now().getAdv_name());//当前接单人
			obj.put("begin_time", adv1.getAuditions().getSt_time());//接单时间
			obj.put("firstRemark", adv1.getSituation());//当天面资情况
			obj.put("zhuizong_01", adv1.getTracking_one());//第一次跟踪细则
			obj.put("zhuizong_02", adv1.getTracking_two());//第二次跟踪细则
			obj.put("zhuizong_03", adv1.getTracking_three());//第三次跟踪细则
			obj.put("history", adv1.getHistory());//跟踪历史
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
	 * 修改学员跟进顾问
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/do_updateAdviserForSigning", method = RequestMethod.POST)
	public String do_updateSigning(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserInfo obj = (UserInfo) session.getAttribute("user");

		JSONObject obj_arr = new JSONObject();
		// 只有超级管理员具有修改权限
		if (obj.getUserType().getType_leibie() != 6) {
			obj_arr.put("list_advs", "ok1");
			return obj_arr.toString();
		}
		String sig_id = request.getParameter("id");
		String adviser_sel = request.getParameter("adviser_sel");
		String adviser_name = request.getParameter("adviser_name");
		
		Signing signing = new Signing();
		
		signing.setSig_id(Integer.valueOf(sig_id));
		Adviser adviser = new Adviser();
		adviser.setAdv_id(adviser_sel);
		signing.setAdviser_now(adviser);
		
		String history = signingService.getSigningHistoryBy_ID(Integer.valueOf(sig_id));
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = sf.format(new Date());
		//之前的历史记录+此刻的记录+修改人姓名
		signing.setHistory(history+"&&&"+d+" 转给 : "+adviser_name+"---由"+obj.getUser_name()+"修改");
		
		Integer count = signingService.do_updateAdviserForSigning(signing);
		
		if (count > 0) {
			obj_arr.put("list_advs", "ok");
		}
		
		return obj_arr.toString();
	}

		
	
	
	
	
}
