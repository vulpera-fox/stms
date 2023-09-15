package com.project.stms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.stms.command.EmailMessageVO;
import com.project.stms.service.mail.EmailService;

@SpringBootTest
public class sendTest {

	@Autowired
	private EmailService emailService;
	
	@Test
	public void sendtest() {
		EmailMessageVO emailMessage = EmailMessageVO.builder()
                .to("alfo3347@gmail.com")
                .subject("테스트 메일 제목")
                .message("테스트 메일 본문")
                .build();
        emailService.sendMail(emailMessage);
	}
}
