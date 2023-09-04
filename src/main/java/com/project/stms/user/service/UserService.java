package com.project.stms.user.service;


import com.project.stms.command.UserVO;

public interface UserService {
	public int join(UserVO userVO);
	public UserVO login(String user_email) ;
	public int changePW(UserVO userVO);
	
}
