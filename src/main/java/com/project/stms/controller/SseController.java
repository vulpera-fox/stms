package com.project.stms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.project.stms.service.notification.SseService;

@RestController
public class SseController {

	@Autowired
	private SseService SseService;

	/**
	 * @title 로그인 한 유저 sse 연결
	 */
	@GetMapping("/test/subscribe/{rcv_id}")
	public SseEmitter subscribe(@PathVariable("rcv_id") String rcv_id,
								@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId,
								HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return SseService.subscribe(rcv_id, lastEventId);
	}
}
