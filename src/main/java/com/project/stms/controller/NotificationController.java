package com.project.stms.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.stms.command.NotificationVO;
import com.project.stms.service.notification.NotificationService;

@RestController
public class NotificationController {
	
	@Autowired
	@Qualifier("notificationService")
	private NotificationService notificationService;
	
	@PostMapping("/getNotiCount")
	public ResponseEntity<Integer> getNotiCount(@RequestBody NotificationVO vo) {
		
		Integer count = notificationService.getCount(vo.getRcv_id());
		
		return new ResponseEntity<>(count, HttpStatus.OK);
	}
	
	@PostMapping("/getList")
	public ResponseEntity<ArrayList<NotificationVO>> getList(@RequestBody NotificationVO vo) {
		
		ArrayList<NotificationVO> list = notificationService.getList(vo.getRcv_id());
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/getReadChk")
	public ResponseEntity<Integer> getReadChk(@RequestBody NotificationVO vo) {
					
		notificationService.getReadChk(vo.getNoti_id());
		
		return new ResponseEntity<>(vo.getNoti_id(), HttpStatus.OK);
	}
	
	@PostMapping("/changeDelStatus")
	public ResponseEntity<Integer> changeDelStatus(@RequestBody NotificationVO vo) {
		
		notificationService.changeDelStatus(vo.getNoti_id());
		notificationService.getReadChk(vo.getNoti_id());
		
		return new ResponseEntity<>(vo.getNoti_id(), HttpStatus.OK);
	}
	
	@PostMapping("/markAllAsRead")
	public ResponseEntity<String> markAllAsRead(@RequestBody NotificationVO vo) {
			
		notificationService.markAllAsRead(vo.getRcv_id());
		
		return new ResponseEntity<>(vo.getRcv_id(), HttpStatus.OK);
	}
	
	
}
