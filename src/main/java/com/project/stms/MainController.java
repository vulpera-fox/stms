package com.project.stms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("api/customer_main")
	public String customer_main() {
		
		return "customer_main";
	}
	
	@GetMapping("/layout_admin")
	public String layout_admin() {
		return "include/layout_admin";
	}
	
	@GetMapping("/layout_customer")
	public String layout_cutomer() {
		return "include/layout_customer";
	}
	
	@GetMapping("/layout_engineer")
	public String layout_engineer() {
		return "include/layout_engineer";
	}
	
	@GetMapping("/notification/notificationPopUp")
	public String notificationPopUp() {
		return "notification/notificationPopUp";
	}
	
	@GetMapping("/test/SseSubscribe")
	public String SseSubscribe() {
		return "test/SseSubscribe";
	}
}