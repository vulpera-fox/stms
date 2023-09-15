package com.project.stms.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.stms.command.NewsVO;
import com.project.stms.service.question.QuestionService;
import com.project.stms.util.NewsCriteria;
import com.project.stms.util.NewsPageVO;

@RequestMapping("/question")
@Controller
public class QuestionController {
	
	@Autowired
//	@Qualifier("questionService")
	QuestionService questionService;
	
	@GetMapping("/customer_question")
	public String QuestionList(Model model, NewsCriteria cri) {
		
		ArrayList<NewsVO> list = questionService.getQlist(cri);
		model.addAttribute("list", list);
		
		int total = questionService.getTotal(cri);
		
		NewsPageVO qpageVO = new NewsPageVO(cri,total);
		model.addAttribute("qpageVO", qpageVO);
		
		System.out.println(qpageVO.getPageList());
		System.out.println(qpageVO.getRealEnd());
		System.out.println(qpageVO.getEnd());
		
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
