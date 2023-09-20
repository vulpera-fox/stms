package com.project.stms.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.stms.command.UserVO;
import com.project.stms.security.config.JWTService;
import com.project.stms.service.user.MyUserDetails;


public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter{

	
	//attemptAuthentication을 오버라이딩 하면
	//클라이언트에서 포스트형태로 /login 가 들어오면 실행됨
	
	private AuthenticationManager authenticationManager;
	
	//생성될때 AuthenticationManager을 생성자 매개변수로 받음
	public CustomLoginFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		System.out.println("======attemptAuthentication 실헹됨======");
		
		//로그인 처리 - 로그인을 시도하는 사람은 반드시 form타입으로 전송(JSON형식으로 받을 수도 있지만 JSON맵핑처리를 해야함)
		//1. username, password를 받음
		String user_email = request.getParameter("user_email");
		String user_pw = request.getParameter("user_pw");
		
		System.out.println(user_email);
		System.out.println(user_pw);
		
		//스프링 시큐리티가 로그인에 사용하는 토큰객체
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(user_email, user_pw);
		//AuthenticationManager(로그인을 시도해줌) 가 실행되면 userDetailService의 loadUserByUsername이 동작
		System.out.println("이게 커스텀 필터에있는 시큐리티 토큰이야 :"+token);
		
		Authentication authentication = authenticationManager.authenticate(token);
		
		System.out.println("내가 실행되었다는 것은 로그인성공: "+authentication);
		

		
		
		
		return authentication; //여기서 반환되는 return은 시큐리티세션이 가져가서 new 시큐리티세션(new 인증객체(new 유저객체)) 형태로 저장시킴
	}



	//로그인성공 후에 실행되는 메서드
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		System.out.println("===로그인 성공 핸들러===");
		//토큰발행, 헤더말고 쿠키에 담고 클라이언트로 전달
		System.out.println("로그인 성공 이후 인증객체" + authResult);
		MyUserDetails principal = (MyUserDetails) authResult.getPrincipal();
		String token = JWTService.createToken(principal.getUsername()); //회원 아이디
		System.out.println(token);
		
		Cookie cookie = new Cookie("Authorization", token);
		cookie.setHttpOnly(true); //자바스크립트로 쿠키를 탈취할 수 있는데 못하게 할려면 setHttpOnly 해주면됨
		response.addCookie(cookie);//jwt를 쿠키에 저장했음 
		
		
		HttpSession session = request.getSession();//아이디를 세션에 저장해서 넘겼음
		session.setAttribute("user_email", principal.getUsername());
		session.setAttribute("user_id", principal.getUser_id());
		session.setAttribute("user_role", principal.getUser_role());
		
		
		UserVO userVO = new UserVO();
		userVO.setUser_email(principal.getUsername());
		userVO.setUser_id(principal.getUser_id());
		userVO.setUser_role(principal.getUser_role());
		userVO.setUser_adr(principal.getUser_adr());
		userVO.setUser_nm(principal.getUser_nm());
		
		session.setAttribute("userVO", userVO);
		

		
		if(principal.getUser_role().equals("ROLE_ENGINEER")) {
			response.sendRedirect("/project/ProjectMain");
			
		} else if(principal.getUser_role().equals("ROLE_CUSTOMER")){
			response.sendRedirect("/");
			
		} else if(principal.getUser_role().equals("ROLE_ADMIN")) {
			response.sendRedirect("/task/taskList");
			
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		System.out.println("===로그인 실패 핸들러===");
		
		response.setContentType("text/html; charset=UTF-8;");
		response.sendRedirect("/log?error=true");

	}

}
