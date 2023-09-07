package com.project.stms.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.stms.command.TaskVO;
import com.project.stms.task.service.TaskService;
import com.project.stms.util.Criteria;
import com.project.stms.util.PageVO;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	//서비스 데이터 주입(연결)
	@Autowired
	@Qualifier("taskService")
	private TaskService taskService;
	
	
	
	//작업조회
	@GetMapping("/taskList")
	public String taskList(Model model, Criteria cri) {
		
		//임시작업자
		String writer = "admin";
		
		//리스트
		ArrayList<TaskVO> list = taskService.getTaskList(cri);
		model.addAttribute("list", list);
		
		int total = taskService.getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("pageVO", pageVO);
		
		System.out.println(pageVO.toString());
		
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
	
	//작업수정페이지
	@GetMapping("taskModify")
	public String taskModify(int task_id) {
		
		TaskVO taskVO = taskService.getModify(task_id);
		
		
		return "task/taskModify";
	}
	
	@PostMapping("taskDeleteForm")
	public String taskDeleteForm(@RequestParam("task_id") int task_id) {
		
		taskService.deleteTaskList(task_id);
		
		return "redirect:/task/taskList";
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
