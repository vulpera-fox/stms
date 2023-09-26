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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.stms.command.ProjectVO;
import com.project.stms.command.TaskVO;
import com.project.stms.service.task.TaskService;
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
		
		//작업자(세션으로 값 불러올 것)
		
		
		//리스트
		ArrayList<TaskVO> list = taskService.getTaskList(cri);
		model.addAttribute("list", list);
		
		int total = taskService.getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("pageVO", pageVO);
		
		System.out.println(pageVO.toString());
		
		return "task/taskList";
	}
	
	//작업등록(프로젝트목록 및 검색기능)
	@GetMapping("taskReg")
	public String taskReg(Model model, Criteria cri) {

		ArrayList<ProjectVO> list = taskService.getPjtList(cri);
		model.addAttribute("list", list);
		System.out.println(list.toString());
		
		int total = taskService.getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		System.out.println(pageVO.toString());
		model.addAttribute("pageVO", pageVO);
		
		return "task/taskReg";
	}

	@GetMapping("taskDetail")
	public String taskDetail(@RequestParam("task_id") int task_id, Model model) {
		
		TaskVO taskVO = taskService.getTaskDetail(task_id);
		model.addAttribute("taskVO", taskVO);
		
		return "task/taskDetail"; 
	}
	//작업결과 등록
	@PostMapping("commentRegForm")
	public String commentRegForm(TaskVO vo, Model model) {
		
		int task_id = vo.getTask_id();
		String comment_dtl = vo.getComment_dtl();
		
		
		taskService.registComment(task_id, comment_dtl);
		
		return "redirect:/task/taskList";
	}
	
	
	
	//작업수정페이지

	@GetMapping("taskModify")
	public String taskModify(@RequestParam("task_id") int task_id, Model model) {
		
		TaskVO taskVO = taskService.getModify(task_id);
		
		//System.out.println("수정페이지 출력값: " + taskVO.toString());
		
		model.addAttribute("taskVO", taskVO);
		
		
		return "task/taskModify";
	}

	//작업수정기능(관리자, 작업자)
	@PostMapping("taskModForm")
	public String taskModForm(TaskVO vo, RedirectAttributes ra) {
		
		System.out.println(vo.toString());
		
		int result = taskService.modiTask(vo);
		
		System.out.println(result);
		
		String msg = result == 1 ? "업데이트 성공" : "업데이트 실패";
		
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/task/taskList";
	}
	
	//작업자 변경 요청(작업자 -> 관리자)
	@PostMapping("changeMemberForm")
	public String changeMemberForm(TaskVO vo) {
		
		int task_id = vo.getTask_id();
		
		taskService.changeMember(task_id);
		
		return "redirect:/task/taskList";
	}
	
	//작업리스트 삭제
	@PostMapping("taskDeleteForm")
	public String taskDeleteForm(@RequestParam("task_id") int task_id) {
		
		
		taskService.deleteTaskList(task_id);
		
		return "redirect:/task/taskList";
	}
	
	
	//작업템플릿등록(없어도 될듯!!!)
	@GetMapping("taskTemplateReg")
	public String taskTemplateReg() {
		return "task/tasktemplateReg";
	}
	
	//작업템플릿관리
	@GetMapping("taskTemplateList")
	public String taskTemplateList(Model model) {
		
		ArrayList<TaskVO> list = taskService.getTemplateList();
		model.addAttribute("list", list);
		
		System.out.println(list.toString());
		
		
		return "task/taskTemplateList";
	}
	
	//작업템플릿등록(모달)
	@PostMapping("tempRegForm")
	public String tempRegForm(TaskVO vo, RedirectAttributes ra) {
		
		int result = taskService.getTemplate(vo);
		String msg = result == 1 ? "등록되었습니다" : "등록실패";
		ra.addFlashAttribute("msg", msg);
		
		
		return "redirect:/task/taskTemplateList";
	}
	
	//작업템플릿 삭제
	@PostMapping("/tempDelForm")
	public String tempDelForm(@RequestParam("tem_id") Integer tem_id) {
		
		System.out.println(tem_id);
		taskService.deleteTemplate(tem_id);
		
		
		return "redirect:/task/taskTemplateList";
	}
	
	
	
	//메인화면(캘린더 뷰)
	@GetMapping("taskDashboard")
	public String taskCalendar() {
		return "task/taskDashboard";
	}
	
	
	//작업 리포트
	@GetMapping("taskReport")
	public String taskReport() {
		return "task/taskReport";
	}
	
}
