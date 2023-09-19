package com.project.stms.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.stms.command.NewsVO;
import com.project.stms.service.question.QuestionService;
import com.project.stms.util.NewsCriteria;
import com.project.stms.util.NewsPageVO;

@RequestMapping("/question")
@Controller
public class QuestionController {
	
	@Autowired
	//@Qualifier("questionService")
	private QuestionService questionService;
	
	@GetMapping("/customer_question")
	public String QuestionList(Model model, NewsCriteria cri) {
		
		ArrayList<NewsVO> list = questionService.getQlist(cri);
		model.addAttribute("list", list);
		
		int total = questionService.getTotal(cri);
		
		NewsPageVO qpageVO = new NewsPageVO(cri,total);
		model.addAttribute("qpageVO", qpageVO);
	
		return "question/customer_question";
		
	}
	
	@GetMapping("/customer_questionDetail")
	public String QuestionDetail(@RequestParam("post_id") int post_id, Model model) {
		
		NewsVO vo = questionService.getQdetail(post_id);
		
		model.addAttribute("vo",vo);
		
		questionService.updateQviewCount(post_id);
		
		
		return "question/customer_questionDetail";
	}
	
	@GetMapping("/customer_questionWrite")
	public String RegiPost() {
		
		return "question/customer_questionWrite";
	}
	
	@PostMapping("/regiQuestion")
	public String regiQuestion(NewsVO vo, 
							   Model model, 
							   RedirectAttributes ra) {
		
		LocalDateTime now = LocalDateTime.now();
		vo.setPost_regdate(now);
		
		int result = questionService.regiQuestion(vo);
		
		String msg = result == 1? "작성하신 글이 등록되었습니다.": "등록에 실패하였습니다.";
		
		ra.addFlashAttribute("msg",msg);
		
		return "redirect:/question/customer_question";
	}
	
//	@PostMapping("/deleteQ")
//	public String deleteQ (@RequestParam("post_id") int post_id, Model model) {
//		
//		
//		
//		
//	}
	
	
	
}
