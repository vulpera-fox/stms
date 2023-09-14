package com.project.stms.mail.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.stms.command.MailVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "YOUR_EMAIL_ADDRESS";

    public void mailSend(MailVO mailVO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailVO.getAddress());
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject(mailVO.getTitle());
        message.setText(mailVO.getMessage());

        mailSender.send(message);
    }
} 