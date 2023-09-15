package com.project.stms.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.project.stms.security.config.JWTService;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

	//생성자
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	//필터기능
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("====JwtAuthorizationFilter 실행됨====");
		
		//String headers = request.getHeader("Authorization");
		Cookie[] list = request.getCookies();
		
		String cookie = "";
		
		for(Cookie cookies:list) {
			if(cookies.getName().equals("Authorization")) {//쿠키 네임이 Authorization인 쿠키의 값을 가져옴
				
				System.out.println("토큰값 쿠키로 가져옴"+cookies.getValue());
				cookie = cookies.getValue();
			}
		}
		
		if(cookie == null ) {
			response.setContentType("text/plain; charset=UTF-8");
			response.sendError(403, "토큰없음");
			
			return; //함수종료
		}
		
		//토큰의 유효성검사
		try {
			String token = cookie; //Bearer공백 이후에 진짜 토큰
			boolean result = JWTService.validateToken(token); //토큰검증
			if(result) { //result == true면 정상토큰
				System.out.println("유효성검사 통과");
				chain.doFilter(request, response); //컨트롤러로 연결
				

				
			} else { //토큰이 만료됨
				response.setContentType("text/plain; charset=UTF-8");
				response.sendError(403, "토큰만료");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//토큰이 위조거나 토큰 만료
			response.setContentType("text/plain; charset=UTF-8");
			response.sendError(403, "토큰위조");
		}
		
//		super.doFilterInternal(request, response, chain);
	}
	
	//

}
