package com.project.stms.service.notification;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.project.stms.command.NotificationVO;

public interface SseService {
	
	public SseEmitter subscribe(String user_id, String lastEventId);
	
	public void send(String receiver, String content);
	
	public NotificationVO createNotification(String receiver, String content);
	
	public void sendToClient(SseEmitter emitter, String id, Object data);
}
