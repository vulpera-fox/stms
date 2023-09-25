package com.project.stms.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.stms.command.NotificationVO;
import com.project.stms.service.notification.NotificationService;
import com.project.stms.util.Criteria;

@RestController
public class NotificationController {
	
	@Autowired
	@Qualifier("notificationService")
	private NotificationService notificationService;
	
	@PostMapping("/getNotiCount")
	public ResponseEntity<ArrayList<NotificationVO>> getNotiCount(@RequestBody NotificationVO vo, HttpServletRequest http) {
		
		HttpSession session = http.getSession();
		
		String user_role = (String)session.getAttribute("user_role");
		
		ArrayList<NotificationVO> count = notificationService.getCount(vo.getRcv_id(), user_role);
		
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
		
		notificationService.changeDelStatus(vo.getNoti_id(), vo.getRcv_del_yn());
		notificationService.getReadChk(vo.getNoti_id());
		
		return new ResponseEntity<>(vo.getNoti_id(), HttpStatus.OK);
	}
	
	@PostMapping("/markAllAsRead")
	public ResponseEntity<String> markAllAsRead(@RequestBody NotificationVO vo) {
			
		notificationService.markAllAsRead(vo.getRcv_id());
		
		return new ResponseEntity<>(vo.getRcv_id(), HttpStatus.OK);
	}
	
	@PostMapping("/getPopUpList")
	public ResponseEntity<ArrayList<NotificationVO>> getPopUpList(@RequestBody NotificationVO vo, HttpServletRequest http) {
		
		String rcv_id = vo.getRcv_id();
		String category = vo.getCategory();
		
		HttpSession session = http.getSession();
		
		String user_role = (String)session.getAttribute("user_role");
		
		ArrayList<NotificationVO> list = notificationService.getPopUpList(rcv_id, category, user_role);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
//	@PostMapping("/getData")
//	public ResponseEntity<Integer> getData(@RequestBody PageVO vo){
//		
//		ArrayList<NotificationVO> pageNumber = notificationService.getData(vo.getPage());
//		
//		return ResponseEntity.ok(1);
//	}
	
	@PostMapping("/getSearchResult")
	public ResponseEntity<ArrayList<NotificationVO>> getSearchResult(@RequestBody Criteria cri) {		
		
		ArrayList<NotificationVO> list = notificationService.getSearchResult(cri, "CUSTOMER");
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
