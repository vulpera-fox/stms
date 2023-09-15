package com.project.stms.command;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestProjectController {
	
	
	
	
	public ResponseEntity<UserVO> getNewPage() {
		
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
