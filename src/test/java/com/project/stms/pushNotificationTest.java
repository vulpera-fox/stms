package com.project.stms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.stms.service.notification.SseService;

@SpringBootTest
public class pushNotificationTest {

	@Autowired
	SseService sseService;
	
	@Test
	public void showPopupNotification() {
		
		
		
	}
}
