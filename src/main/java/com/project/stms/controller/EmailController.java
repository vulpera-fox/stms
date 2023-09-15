package com.project.stms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.stms.command.EmailMessageVO;
import com.project.stms.service.mail.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send-mail")
    public ResponseEntity sendMail() {
        EmailMessageVO emailMessage = EmailMessageVO.builder()
                .to("alfo3347@gmail.com")
                .subject("테스트 메일 제목")
                .message("테스트 메일 본문")
                .build();
        emailService.sendMail(emailMessage);
        return new ResponseEntity(HttpStatus.OK);
    }
}
