package com.project.stms.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.stms.service.user.MyUserDetails;




@Controller
public class HomeController {

	//홈
	@GetMapping("/main")
	public String main(Authentication auth) {


		//2nd -시큐리티세션을 직접사용
		Authentication authentication = SecurityContextHolder
				.getContext()
				.getAuthentication();

		if(authentication.getPrincipal() instanceof MyUserDetails) {
			MyUserDetails details =
					(MyUserDetails)authentication.getPrincipal();
			System.out.println(details);
		}

		return "main";
	}
}
