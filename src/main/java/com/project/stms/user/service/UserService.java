package com.project.stms.user.service;


import java.util.List;

import com.project.stms.command.UserVO;

public interface UserService {
	public int join(UserVO userVO);
	public int changePW(UserVO userVO);
	public List<UserVO> userInfo(String user_email);
	public UserVO checkId(String user_email);
	public UserVO changePE(String user_email);
}
