package com.hicode.oa.system;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

/**
 * 自定义session监听器
 * 
 * @author XinPeiXiang 2018-12-07
 *
 */
@Component
public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("+++++++++++++++sessionCreated+++++++++++++++" + arg0);
	}

	/**
	 * 通过自定义session监听器,当session过期时,将用户信息注销,以便再次登录
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {

		HttpSession session = arg0.getSession();

		ServletContext application = session.getServletContext();

		Map<String, HttpSession> userOnline = (Map<String, HttpSession>) application.getAttribute("userOnline");

		if (userOnline != null && userOnline.size() > 0) {

			Set<String> sets = userOnline.keySet();

			for (String s : sets) {

				if (userOnline.get(s) == session) {

					userOnline.remove(s);
					break;
				}
			}
		}
	}

}
