package com.project.stms.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.stms.command.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int join(UserVO userVO) {
		return userMapper.join(userVO);
	}


	@Override
	public int changePW(UserVO userVO) {
		return userMapper.changePW(userVO);
	}


	@Override
	public List<UserVO> userInfo(String user_email) {
		return userMapper.userInfo(user_email);
	}


	@Override
	public UserVO checkId(String user_email) {
		
		return userMapper.checkId(user_email);
	}


	@Override
	public UserVO changePE(String user_email) {
		return userMapper.changePE(user_email);
	}




	


}
