package com.project.stms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.stms.command.MailVO;
import com.project.stms.mail.service.MailService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class MailController {
    private MailService mailService;


    @PostMapping("/mail")
    public void execMail(@RequestBody MailVO mailVO) {
    	System.out.println(mailVO.toString());
        mailService.mailSend(mailVO);
    }
}