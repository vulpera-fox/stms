package com.project.stms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

	//홈
	@GetMapping("/main")
	public String main(HttpServletRequest request, Model model) {
	        // Authorization 헤더의 값을 가져옵니다.
		
	        String token = request.getHeader("Authorization");

	        // 모델에 token을 추가합니다.
	        model.addAttribute("token", token);
	        System.out.println(token);
		return "main";
	}
}
