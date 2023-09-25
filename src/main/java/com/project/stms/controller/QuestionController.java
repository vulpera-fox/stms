package com.project.stms.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.stms.command.NewsVO;
import com.project.stms.command.UserVO;
import com.project.stms.service.question.QuestionService;
import com.project.stms.util.NewsCriteria;
import com.project.stms.util.NewsPageVO;

@RequestMapping("/question")
@Controller
public class QuestionController {

	@Autowired
	// @Qualifier("questionService")
	private QuestionService questionService;

	/* 게시글 조회 */
	@GetMapping("/customer_question")
	public String QuestionList(Model model, NewsCriteria cri) {

		ArrayList<NewsVO> list = questionService.getQlist(cri);
		model.addAttribute("list", list);

		int total = questionService.getTotal(cri);

		NewsPageVO qpageVO = new NewsPageVO(cri, total);
		model.addAttribute("qpageVO", qpageVO);

		return "question/customer_question";

	}

	/* 나의문의글 보기 */
	@GetMapping("/customer_myQuestion")
	public String myQuestion(Model model, NewsCriteria cri, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_email");
		System.out.println("내 아이디는:" + user_id);
		ArrayList<NewsVO> list = questionService.getMyQlist(cri, user_id);
		model.addAttribute("list", list);
		System.out.println(list.toString());
		NewsVO vo = new NewsVO();
		System.out.println("null이라고?"+ vo.getPost_id());
		
		
		cri.setUser_id(user_id);

		int total = questionService.getQtotal(cri);

		NewsPageVO mpageVO = new NewsPageVO(cri, total);
		model.addAttribute("mpageVO", mpageVO);

		return "question/customer_myQuestion";

	}

	/* 나의 게시글 작성 */
	@GetMapping("/customer_questionMyWrite")
	public String MyRegiPost(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_email");
		model.addAttribute("user_id", user_id);

		return "question/customer_questionMyWrite";
	}

	/* 게시글 상세 보기 */
	@GetMapping("/customer_questionDetail")
	public String QuestionDetail(@RequestParam("post_id") int post_id, Model model) {

		NewsVO vo = questionService.getQdetail(post_id);

		model.addAttribute("vo", vo);

		questionService.updateQviewCount(post_id);

		return "question/customer_questionDetail";
	}

	/* 게시글 작성 페이지*/
	@GetMapping("/customer_questionWrite")
	public String RegiPost() {

		return "question/customer_questionWrite";
	}

	/* 게시글 등록 */
	@PostMapping("/regiQuestion")
	public String regiQuestion(NewsVO vo, Model model, RedirectAttributes ra, HttpServletRequest request) {

		LocalDateTime now = LocalDateTime.now();
		vo.setPost_regdate(now);

		HttpSession session = request.getSession();

		String user_id = (String) session.getAttribute("user_email");
		vo.setUser_id(user_id);

		System.out.println("작성자 구함:" + vo.getUser_id());

		int result = questionService.regiQuestion(vo);

		String msg = result == 1 ? "작성하신 글이 등록되었습니다." : "등록에 실패하였습니다.";

		ra.addFlashAttribute("msg", msg);

		return "redirect:/question/customer_question";
	}

	/* 나의 문의 글 등록 */
	@PostMapping("/regiMyQuestion")
	public String regiMyQuestion(NewsVO vo, Model model, RedirectAttributes ra, HttpServletRequest request) {

		LocalDateTime now = LocalDateTime.now();
		vo.setPost_regdate(now);
		HttpSession session = request.getSession();

		String user_id = (String) session.getAttribute("user_email");
		vo.setUser_id(user_id);

		int result = questionService.regiQuestion(vo);

		String msg = result == 1 ? "작성하신 글이 등록되었습니다." : "등록에 실패하였습니다.";

		ra.addFlashAttribute("msg", msg);

		return "redirect:/question/customer_myQuestion";
	}

	@PostMapping("/deleteQ")
	public String deleteQ(@RequestParam("post_id") int post_id, RedirectAttributes ra) {

		int result = questionService.deleteQ(post_id);
		String delMsg =	result == 1 ? "해당 게시글을 삭제하였습니다." : "삭제 실패";
		
		ra.addFlashAttribute("delMsg",delMsg);

		return "redirect:/question/customer_question";
	}

	@GetMapping("/modifyQ")
	public String modifyQ(@RequestParam("post_id") int post_id, Model model) {

		NewsVO vo = questionService.getQdetail(post_id);

		model.addAttribute("vo", vo);

		System.out.println(vo.toString());

		return "question/customer_questionModify";
	}

	@PostMapping("/updateQ")
	public String updateQ(NewsVO vo) {

		questionService.updateQ(vo);

		return "redirect:/question/customer_question";
	}

	@GetMapping("/replyQ")
	public String replyQ(@RequestParam("post_id") int post_id, Model model) {

		NewsVO vo = questionService.getQdetail(post_id);

		model.addAttribute("vo", vo);

		return "question/customer_questionReply";
	}

	@PostMapping("/regiReply")
	public String regiReply(NewsVO vo, Model model, RedirectAttributes ra, HttpServletRequest request,
			@RequestParam("post_id") int post_id) {

		LocalDateTime now = LocalDateTime.now();
		vo.setPost_regdate(now);
		HttpSession session = request.getSession();

		String user_id = (String) session.getAttribute("user_email");
		vo.setUser_id(user_id);
		vo.setOrg_id(post_id);
//		vo.setPost_secret_yn(post_secret_yn);
//		vo.setPost_pw(post_pw);

		if (vo.getDepth() == null) {

			vo.setDepth(1);

		} else {

			vo.setDepth(vo.getDepth() + 1);
		}

		if (vo.getOrder_no() == null) {

			vo.setOrder_no(1);

		} else {

			vo.setOrder_no(vo.getOrder_no() + 1);
		}

		System.out.println(vo.getOrg_id());
		System.out.println(vo.toString());
		int result = questionService.regiReply(vo);

		String reply = result == 1 ? "답변이 등록되었습니다." : "등록에 실패하였습니다.";

		ra.addFlashAttribute("reply", reply);

		return "redirect:/question/customer_question";
	}

}
