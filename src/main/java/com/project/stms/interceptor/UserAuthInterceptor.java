package com.project.stms.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserAuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("컨트롤러 전에 실행됨");
		System.out.println("어떤 경로로 들어왔는지 확인:" + request.getRequestURI());

		HttpSession session = request.getSession();
		System.out.println(session);
		System.out.println(session.getAttribute("userVO"));
		System.out.println(session.getAttribute("user_role"));
		
		if (session != null && session.getAttribute("user_role") != null) {
			
			if (request.getRequestURI().equals("/")) {
				
				if (session.getAttribute("user_role").equals("ROLE_ADMIN")) {

					response.sendRedirect( request.getContextPath()+ "project/projectMain");
					
					return false;
			
				} else if (session.getAttribute("user_role").equals("ROLE_ENGINEER")) {

					response.sendRedirect(request.getContextPath()+ "task/taskDashboard");
					
					return false;
				}
			}

		} 
		
		return true;
		
	}

}
