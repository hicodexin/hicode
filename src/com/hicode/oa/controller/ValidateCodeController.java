package com.hicode.oa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicode.oa.service.ValidateCodeService;
import com.hicode.oa.tool.ValidateCode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 获取验证码
 * @author xinpeixiang 2018-11-30
 *
 */
@Controller
@RequestMapping("/validateCode")
public class ValidateCodeController {

	@Autowired
	private ValidateCodeService validateCodeService;
	
	
	/**
	 * 图片点击旋转
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserAndPass")
	public String getUserAndPass(HttpServletRequest request){
		
		String the_img = request.getParameter("the_img");
		String deg = request.getParameter("deg");
		
		JSONObject obj = new JSONObject(); 
		Integer ss = Integer.valueOf(deg);
		ss = ss+90;
		
		obj.put("the_img", the_img);//图片名称
		obj.put("vc_deg", (ss));
		
		return ss.toString();
	}
	
	/**
	 * 图片点击旋转
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCodeDeg")
	public String getCodeDeg(HttpServletRequest request){
		
		String the_img = request.getParameter("the_img");
		String deg = request.getParameter("deg");
		
		JSONObject obj = new JSONObject(); 
		Integer ss = Integer.valueOf(deg);
		ss = ss+90;
		
		obj.put("the_img", the_img);//图片名称
		obj.put("vc_deg", (ss));
		
		return ss.toString();
	}
	
	/**
	 * 获取验证码（4张图片）
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getValidateCode")
	public String getValidateCode(HttpServletRequest request){
		
		List<ValidateCode> vclist = validateCodeService.getValidateCodeInfo(0, 100);
		
		int[] deg = { 90, 180, 270 };
		List<String> imglist = new ArrayList<String>();
		JSONArray array = new JSONArray();

		int count = 0;
		w1: while (count < 4) {
			int x = (int) (Math.random() * vclist.size());
			for (String k : imglist) {
				if (k != null && k.equals(vclist.get(x).getCode_img())) {
					continue w1;
				}
			}
			int d = (int) (Math.random() * deg.length);
			JSONObject obj = new JSONObject(); 
			obj.put("id", vclist.get(x).getCode_id());//图片id
			obj.put("vc_name", vclist.get(x).getCode_img());//图片名称
			obj.put("vc_deg", deg[d]);//随机给角度
			
			
			imglist.add(vclist.get(x).getCode_img());
			array.add(obj);
			count++;
		}
		return array.toString();
	}
	
	
	
	
	
	
}



















