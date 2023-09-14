package com.project.stms.service.user;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.stms.command.UserVO;


@Mapper	
public interface UserMapper {	
	public int join(UserVO userVO);
	public UserVO login(String user_email);
	public int changePW(UserVO userVO);
	public List<UserVO> userInfo(String user_email);
	public UserVO checkId(String user_email);
	public UserVO changePE(String user_email);
}