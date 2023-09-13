package com.project.stms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/question")
@Controller
public class QuestionController {
	
	@GetMapping("/customer_question")
	public String QuestionList() {
		
		return "question/customer_question";
		
	}
	
	@GetMapping("/customer_questionDetail")
	public String QuestionDetail() {
		
		return "question/customer_questionDetail";
	}
	
	@GetMapping("/customer_questionWrite")
	public String goWrite() {
		
		return "question/customer_questionWrite";
	}
	
	
}
