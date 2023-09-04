package com.project.stms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	//서비스 데이터 주입(연결)
//	@Autowired
//	@Qualifier("taskService")
//	private TaskService taskService;
	
	
	
	//작업조회
	@GetMapping("/taskList")
	public String taskList() {
		return "task/taskList";
	}
	
	//작업등록
	@GetMapping("taskReg")
	public String taskReg() {
		return "task/taskReg";
	}
	
	//작업상세
	@GetMapping("taskDetail")
	public String taskDetail() {
		return "task/taskDetail";
	}
	
	//작업수정
	@GetMapping("taskModify")
	public String taskModify() {
		return "task/taskModify";
	}
	
	//작업템플릿등록
	@GetMapping("taskTemplateReg")
	public String taskTemplateReg() {
		return "task/tasktemplateReg";
	}
	
	//작업템플릿관리
	@GetMapping("taskTemplateList")
	public String taskTemplateList() {
		return "task/taskTemplateList";
	}
	
	//메인화면(캘린더 뷰)
	@GetMapping("taskCalendar")
	public String taskCalendar() {
		return "task/taskCalendar";
	}
	
	//작업 리포트
	@GetMapping("taskReport")
	public String taskReport() {
		return "task/taskReport";
	}
	
}
