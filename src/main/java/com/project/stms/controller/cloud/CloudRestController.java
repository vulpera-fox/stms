package com.project.stms.controller.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.stms.command.SesVO;
import com.project.stms.service.aws.SesService;


@RestController
public class CloudRestController {

	
	@Autowired
	private SesService ses;

	
	@PostMapping("/send_email")
	public ResponseEntity<String> send_email(@RequestBody SesVO sesVO){
		
		String sender = "yjhzea@gmail.com";//발신자 주소
		String recipient = sesVO.getRecipient();//수신자 주소
		String subject = "STMS 인증메일 입니다";//제목
		String HTMLBODY = "<h1>STMS 인증메일 입니다</h1>"
			     + "인증번호는" + sesVO.getMessage();
		
		ses.sendEmail(sender, recipient, subject, HTMLBODY);
		
		return new ResponseEntity<>("응답데이터", HttpStatus.OK);
	} 
	
	
}
