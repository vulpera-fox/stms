package com.project.stms;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.stms.command.NotificationVO;

@SpringBootTest
public class notificationTest {
	
	@Test
	public void testCode01() {
		Date date = new Date();
		
		for(int i = 1; i <= 30; i++) {
			
			NotificationVO vo = NotificationVO.builder()
									.pre_noti_id(1)
									.rcv_id("CUSTOMER")
									.send_id("ADMIN")
									.noti_nm(i+ "번 점검")
									.noti_dtl(date + "에 점검예정")
									.task_date("2023-09-27")
									.build();
			
		}
	}
}
