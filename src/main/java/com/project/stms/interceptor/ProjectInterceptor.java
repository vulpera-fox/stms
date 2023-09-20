package com.project.stms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.project.stms.service.project.ProjectService;

public class ProjectInterceptor implements HandlerInterceptor {
	
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		String user_email = (String) session.getAttribute("user_email");
		
		String user_role = (String) session.getAttribute("user_role");
		
		String user_id = (String) session.getAttribute("user_id");

		
		
		request.setAttribute("user_email", user_email);
		request.setAttribute("user_role", user_role);
		request.setAttribute("user_id", user_id);
		
		
		
		
		return true;

	}

}
