package com.project.stms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.project.stms.service.notification.SseServiceImple;

@RestController
@RequestMapping("/test")
public class SseController {
	
	@Autowired
	private SseServiceImple sseServiceImple;
	
	@GetMapping(value = "/subscribe/{rcv_id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter subscribe(@PathVariable String rcv_id, 
							    @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
		
		System.out.println(rcv_id + " " + lastEventId);
		
		return sseServiceImple.subscribe(rcv_id, lastEventId);
	}
	
	
}
