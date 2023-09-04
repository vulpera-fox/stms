package com.project.stms.user.service;


import org.apache.ibatis.annotations.Mapper;

import com.project.stms.command.UserVO;


@Mapper	
public interface UserMapper {	
	public int join(UserVO userVO);
	public UserVO login(String user_email);
	public int changePW(UserVO userVO);

}