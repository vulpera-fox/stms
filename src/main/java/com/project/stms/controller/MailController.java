package com.project.stms.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.stms.command.MailVO;
import com.project.stms.command.UserVO;
import com.project.stms.security.config.JWTService;
import com.project.stms.service.mail.MailService;
import com.project.stms.service.user.UserService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class MailController {
	private MailService mailService;
	private UserService userService;

	@PostMapping("/mail")
	public void execMail(@RequestBody MailVO mailVO) {
		System.out.println(mailVO.toString());
		mailService.mailSend(mailVO);
	}

	@GetMapping("/random")
	public String random() {

		String num = "";
		for (int i = 0; i < 6; i++) {

			num += (int)(Math.random()*10);

		}

		return num;
	}

	@PostMapping("/checkId")
	public String checkId(@RequestBody UserVO userVO) {

		String user_email = userVO.getUser_email();

		userVO = userService.checkId(user_email);

		if(userVO == null) {
			System.out.println("없어 사용해도돼");
			return user_email;

		} else {
			System.out.println("중복이야");
			return "overlap";
		}
	}

	@PostMapping("/changePE")
	public String changePE(@RequestBody UserVO userVO) {

		String user_email = userVO.getUser_email();
		userVO = userService.changePE(user_email);

		if(userVO != null) {
			System.out.println("존재함");

			return "exist";

		}

		return null;
	}
	
	
	@GetMapping("/timeOut")
	public int timeOut(HttpServletRequest request) {

		int seconds = JWTService.decodeJwt(request);

		if(seconds!=0) {
			
		//	System.out.println(seconds);
			return seconds;
		}
		
		return 0;
	}
} 