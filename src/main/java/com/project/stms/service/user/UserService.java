package com.project.stms.service.user;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.stms.command.UserVO;

public interface UserService {
	public int join(UserVO userVO);
	public int changePW(UserVO userVO);
	public UserVO userInfo(String user_email);
	public UserVO checkId(String user_email);
	public UserVO changePE(String user_email);
	public int delete(String user_id);
	//public void insertProfile(List<MultipartFile> list, String originalName);
}
