package com.project.stms.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.stms.command.UserVO;
import com.project.stms.service.user.UserService;


@Controller
public class UserController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	@Qualifier("userService")
	private UserService userService;


	@PostMapping("/joinForm")
	public String joinForm(@Valid @ModelAttribute("userVO") UserVO userVO, Errors errors, Model model) {


		if(errors.hasErrors()) { //에러가 있다면 true, 없다면 false
			//1. 유효성 검사에 실패한 에러 확인
			List<FieldError> list = errors.getFieldErrors();

			//2. 반복처리
			for(FieldError err : list ) {
				//System.out.println(err);
				//System.out.println(err.getField()); //에러가 난 필드명
				//System.out.println(err.getDefaultMessage()); //메시지 출력
				//System.out.println(err.isBindingFailure()); //유효성검사에 의해서 err라면 false, 아니라면 true반환

				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField() , "잘못된 값 입력입니다" );
				} else {
					model.addAttribute("valid_" + err.getField() , err.getDefaultMessage());								
				}
			}


			return "user/log"; //실패시 원래 화면으로

		} //err end

		//userService.insertProfile(null, null);
		System.out.println(userVO.toString());

		//비번 단방향 암호화
		String pw = bCryptPasswordEncoder.encode(userVO.getUser_pw());
		userVO.setUser_pw(pw);

		userService.join(userVO);


		return "redirect:/log";
	}

	@GetMapping("/log")
	public String log(@RequestParam (value = "err", required = false)String err, Model model) {

		if(err != null) {
			model.addAttribute("msg", "아이디 비밀번호를 확인하세요");
		}

		return "user/log";
	}

	@PostMapping("/changePW")
	public String changePW(UserVO userVO) {
		System.out.println(userVO.toString());

		String pw = bCryptPasswordEncoder.encode(userVO.getUser_pw());
		userVO.setUser_pw(pw);
		userService.changePW(userVO);
		return "redirect:/log";
	}


	@GetMapping("/user/mypage")
	public String myPage() {

		return "user/mypage";
	}
	
	@GetMapping("/user/mypageE")
	public String myPageE() {

		return "user/mypageE";
	}

	//로그아웃
	@GetMapping("/user/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// 세션을 무효화합니다.
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// 쿠키를 삭제합니다.
		Cookie[] list = request.getCookies();
		if (list != null) {
			for (Cookie autho : list) {
				if(autho.getName().equals("Authorization")) {
					autho.setValue(""); 
					autho.setMaxAge(0); 
					autho.setPath("/"); //이거 넣어주닌까 쿠키가 

					response.addCookie(autho);
				}
				if(autho.getName().equals("Refreshtoken")) {
					autho.setValue(""); 
					autho.setMaxAge(0); 
					autho.setPath("/"); //이거 넣어주닌까 쿠키가

					response.addCookie(autho);
				}
			}

			return "redirect:/";
		}

		return "redirect:/";
	}

	//탈퇴
	@GetMapping("/user/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("userVO");

		int num = userService.delete(userVO.getUser_id());
		if(num == 1) {

			session.invalidate();

			Cookie[] list = request.getCookies();

			for (Cookie autho : list) {
				if(autho.getName().equals("Authorization")) {
					autho.setValue(""); 
					autho.setMaxAge(0); 
					autho.setPath("/");
					response.addCookie(autho);

					return "redirect:/";
				}
			}
		}

		return "redirect:/";
	}

	@PostMapping("/user/updateForm")
	public String update(HttpServletRequest request, HttpServletResponse response, UserVO userVO) {
		HttpSession session = request.getSession();


		System.out.println(userVO.toString());

		if(!userVO.getUser_pw().equals("")) {

			String pw = bCryptPasswordEncoder.encode(userVO.getUser_pw());
			userVO.setUser_pw(pw);
			System.out.println(userVO.getUser_pw()+"이것도 찍히냐");
		}


		int result = userService.update(userVO);

		if(result==1) {

			UserVO userVO2 = (UserVO)session.getAttribute("userVO");
			userVO2.setOrg_file_nm(userVO.getOrg_file_nm());
			userVO2.setUser_group(userVO.getUser_group());
			session.setAttribute("userVO", userVO2);

		} else {
			System.out.println("수정안됨");
		}


		return "user/mypage";
	}

}
