package com.project.stms.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.stms.command.TaskVO;
import com.project.stms.task.service.TaskService;

@RestController
public class TaskRestController {

	@Autowired
	private TaskService taskService;
	
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
		
		TaskVO taskVO = taskService.getTaskModify(vo);
		
		return new ResponseEntity<>(taskVO, HttpStatus.OK);
	}
	
}
