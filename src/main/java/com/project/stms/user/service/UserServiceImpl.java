package com.project.stms.user.service;

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
	public UserVO login(String user_email) {
		return userMapper.login(user_email);
	}

	@Override
	public int changePW(UserVO userVO) {
		return userMapper.changePW(userVO);
	}


	


}
