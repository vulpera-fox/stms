package com.project.stms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	
	@GetMapping("/")
	public String main() {
		return "Main";
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
}
