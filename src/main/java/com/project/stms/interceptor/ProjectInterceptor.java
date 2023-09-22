package com.project.stms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ProjectInterceptor implements HandlerInterceptor {
	
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		HttpSession session = request.getSession();
//
//		String user_email = (String) session.getAttribute("user_email");
//		
//		String user_role = (String) session.getAttribute("user_role");
//		
//		
//		request.setAttribute("user_email", user_email);
//		
//		request.setAttribute("user_email", user_email);
//		
//		System.out.println(user_email + " 유저이메일");
//		
//		System.out.println(user_role + " 유저권한");
		
		return true;

	}

}
