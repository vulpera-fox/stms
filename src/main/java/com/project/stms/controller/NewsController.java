package com.project.stms.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.stms.command.NewsVO;
import com.project.stms.service.news.NewsService;
import com.project.stms.util.NewsCriteria;
import com.project.stms.util.NewsPageVO;

@RequestMapping("/news")
@Controller
public class NewsController {
	
	@Autowired
	@Qualifier("newsService")
	private NewsService newsService;
	
	@GetMapping("/customer_news")
	public String getNews(Model model,NewsCriteria cri) {
							
		ArrayList<NewsVO> list = newsService.getNews(cri);
		model.addAttribute("list", list);
		System.out.println(list.toString());
		
		int total = newsService.getNewsTotal(cri);
		NewsPageVO npageVO = new NewsPageVO(cri, total);
		
		model.addAttribute("npageVO", npageVO);
		
		System.out.println(npageVO.toString());
		System.out.println(cri);
		System.out.println(cri.getAmount());
		
		return "news/customer_news";
		
	}
	
	@GetMapping("/customer_detail")
	public String getNewsDetail(@RequestParam("post_id") int post_id,
											  Model model,
											  NewsCriteria cri) {
		
		NewsVO vo = newsService.getNewsDetail(post_id);
		
		model.addAttribute("vo", vo);
		
		System.out.println(cri);
		
		NewsVO prev = newsService.getNewsPrev(post_id, cri);
		NewsVO next = newsService.getNewsNext(post_id, cri);
		
		if(prev == null) {
			
			 prev = new NewsVO();
			
			prev.setPost_title("이전 글이 존재하지 않습니다.");
			prev.setPost_id(0);
			
		} else if(next == null) {
			
			next = new NewsVO();
			next.setPost_title("다음 글이 존재하지 않습니다.");
			next.setPost_id(0);
		}
		
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("cri",cri);
		
		newsService.updateNewsViewCount(post_id);
		
		System.out.println(vo.toString());	
		
		return "news/customer_detail";
	}
	
	
	
	
	
	

}
