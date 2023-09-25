package com.project.stms.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.stms.command.TaskVO;
import com.project.stms.service.notification.NotificationService;
import com.project.stms.service.task.TaskService;

@RestController
public class TaskRestController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private NotificationService notificationService;
	
	private static final Logger log = LoggerFactory.getLogger(TaskRestController.class);
	
	//프로젝트에 따른 작업목록 조회
	@GetMapping("taskSearch")
	public ResponseEntity<ArrayList<TaskVO>> taskSearch(@RequestParam("pjt_id") Integer pjt_id) {
		
		System.out.println(pjt_id);
		
		ArrayList<TaskVO> list2 = taskService.getTaskSearch(pjt_id);
		
		System.out.println(list2.toString());
				
		return new ResponseEntity<>(list2, HttpStatus.OK);
	}	
	
	

	//작업 상세내용 불러오기
	@GetMapping("taskDetailSearch")
	public ResponseEntity<TaskVO> taskDetailSearch(@RequestParam("task_id") Integer task_id) {
		
		System.out.println(task_id);
		
		TaskVO taskVO = taskService.getTaskDetailSearch(task_id);
		
		System.out.println(taskVO.toString());
		
		
		return new ResponseEntity<>(taskVO,HttpStatus.OK);
	}
	
	
	//작업 수정
	
	@PostMapping("taskModifyForm")
	public ResponseEntity<TaskVO> taskModifyForm(@RequestBody TaskVO vo) {
		
		System.out.println(vo.toString());
		
		taskService.updateTask(vo);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	//작업등록카드(타입A:작업목록 있는 경우)
	@GetMapping("getRegBoxA")
	public ResponseEntity<ArrayList<TaskVO>> getRegBoxA(@RequestParam("pjt_id") Integer pjt_id) {
		
		ArrayList<TaskVO> taskVO = taskService.getRegBoxA(pjt_id);
		
		System.out.println("등록시:" + taskVO.toString());
		
		return new ResponseEntity<>(taskVO, HttpStatus.OK);
	}
	
	//A타입 작업 등록
	@PostMapping("newTaskRegA")
	public ResponseEntity<TaskVO> newTaskRegA(@RequestBody TaskVO vo) {
		
		System.out.println(vo.toString());
		
		taskService.taskRegist(vo);
		
		notificationService.createTaskNotification(vo.getUser_id(), vo.getTask_date(), vo.getTask_nm(), vo.getPjt_id());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//작업 삭제(다시 찾아볼 것)
	@PostMapping("delTask")
	public ResponseEntity delTask(@RequestBody TaskVO vo) {
		
		int task_id = vo.getTask_id();
		
		System.out.println("삭제 파라미터:" + task_id);
		
		taskService.deleteTaskList(task_id);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	//작업등록시 작업자 리스트 불러오기
	@GetMapping("getPjtMembers")
	public ResponseEntity<ArrayList<TaskVO>> getPjtMembers(@RequestParam("pjt_id") Integer pjt_id) {
		
		System.out.println("매개변수:" + pjt_id);
		ArrayList<TaskVO> list = taskService.getPjtMembers(pjt_id);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//작업등록시 템플릿 리스트 불러오기
	@GetMapping("getTaskTemp")
	public ResponseEntity<ArrayList<TaskVO>> getTaskTemp() {
		
		ArrayList<TaskVO> list = taskService.getTaskTemp();
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//템플릿 적용하기
	@GetMapping("applyTemp")
	public ResponseEntity<TaskVO> applyTemp(@RequestParam("tem_nm") String tem_nm) {
		
		
		TaskVO taskVO = taskService.applyTemp(tem_nm);
		
		System.out.println("템플릿 적용값 : " + taskVO.toString());
		
		return new ResponseEntity<>(taskVO, HttpStatus.OK);
	}
	
	
	//작업담당자 변경요청시 알림기능
	@PostMapping("changeMemberNoti")
	public ResponseEntity changeMemberNoti(@RequestParam("rcv_id") String rcv_id) {
		
		notificationService.createChangeMemberNotification(rcv_id);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	
	//작업시작시간 체크
	@PostMapping("checkStartTaskTime")
	public ResponseEntity<TaskVO> checkStartTaskTime(@RequestBody TaskVO vo) {
		
		taskService.checkStartTime(vo);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	//작업종료시간 체크
	@PostMapping("checkEndTaskTime")
	public ResponseEntity<TaskVO> checkEndTaskTime(@RequestBody TaskVO vo) {
		
		taskService.checkEndTime(vo);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	//캘린더뷰 조회하기
	@GetMapping("getCalendar")
	public List<TaskVO> getCalendar() {
		
		List<TaskVO> list = taskService.getCalendar();
		
		return list;
	}
}
