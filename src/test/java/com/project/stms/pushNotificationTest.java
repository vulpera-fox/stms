package com.project.stms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.stms.service.notification.SseServiceImple;

@SpringBootTest
public class pushNotificationTest {

	@Autowired
	private SseServiceImple sseServiceImpl;
	
	
	@Test
	public void showPopupNotification() {
		
		sseServiceImpl.send("CUSTOMER", "알림 테스트");
		
	}
}
