package com.project.stms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

	@GetMapping("/api/v1/hello")
	public String hello() {
		return "<h3>hello</h3>";
	}

	//토큰 기반으로 한 사용자요청 정보 반환기능
	@PostMapping("/api/v1/getInfo")
	public ResponseEntity<Object> getInfo() {
		System.out.println("토큰이 있으면 호출됨(데이터베이스 연결처리)");
		return new ResponseEntity<>("데이터", HttpStatus.OK);
	}

	//회원가입 =>  토큰이 필요할까..?
	@PostMapping("/join")
	public ResponseEntity<Object> join() {
		return new ResponseEntity<>("가입성공", HttpStatus.OK);

	}
	
	@GetMapping("/random")
	public String random() {
		
		String num = "";
		for (int i = 0; i < 6; i++) {
			
		num += (int)(Math.random()*10);
		
		}
		
		return num;
	}
}
