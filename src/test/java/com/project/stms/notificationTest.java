package com.project.stms;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.stms.command.ProjectVO;
import com.project.stms.service.notification.NotificationService;

@SpringBootTest
public class notificationTest {
	
	@Autowired
	NotificationService notificationService;

	String req_user_id = "33";
	String admin = "ADMIN";
	String uuid = UUID.randomUUID().toString();
	
	@Test
	public void userRequestProject() {

		//pjt_nm, pjt_dtl, pjt_st_dt, pjt_end_dt, req_user_id, server_id
		
		
		ProjectVO vo = ProjectVO.builder()
								.pjt_nm(uuid)
								.pjt_dtl("test project 요청 상세")
								.pjt_st_dt("2023-09-18")
								.pjt_end_dt("2023-09-20")
								.req_user_id(req_user_id)
								.server_id("1")
								.build();
		
		notificationService.createProjectNotification(admin, vo.getReq_user_id(), vo.getPjt_nm());
		
	}
	
	@Test
	public void adminAllowProject() {
		ProjectVO vo = ProjectVO.builder()
				.pjt_nm(uuid)
				.pjt_dtl("test project 요청 상세")
				.pjt_st_dt("2023-09-18")
				.pjt_end_dt("2023-09-20")
				.req_user_id(req_user_id)
				.server_id("1")
				.build();

		notificationService.createProjectNotification(vo.getReq_user_id(), admin, vo.getPjt_nm());
		
	}
	
	@Test
	public void engineerReceiveProjectNotification() {
		
		String pjt_nm = "123";
		
		List<String> users = List.of("23", "24", "25", "27", "U2023090002", "41");
		
		
		for(int i = 0; i < users.size(); i++) {
		
		notificationService.createProjectNotification(users.get(i), admin, pjt_nm);
		
		}
	}
}
