package com.project.stms.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.stms.command.UserVO;


//이 객체는 화면에 전달이 되는데, 화면에서 사용할 값들은 getter로 생성
public class MyUserDetails implements UserDetails{

	//멤버변수로 UserVO객체를 받습니다.
	private UserVO userVO;
	
	public MyUserDetails(UserVO vo) {
		this.userVO = vo;
	}
	
	//화면에서 권한도 사용할 수 있게 해주고 싶다면? getter믄드세요
	//플러스 알파 더 만들고 싶은거 다 써주세요
	public String getUser_role() {
		return userVO.getUser_role();
	}
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//UserVO가 가지고 있는 권한을 리스트에 담아서 변환시키면, 스프링 시큐리티가 참조해서 사용합니다.
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return userVO.getUser_role();
			}
		});
		
		return list;
	}

	@Override
	public String getPassword() {
		return userVO.getUser_pw();
	}

	@Override
	public String getUsername() {
		return userVO.getUser_email();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; //계정이 만료되지 않았습니까? true면 네 
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; //계정이 락이 걸리지 않았습니까 네
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; //비밀번호가 만료되지 않았습니까? 네
	}

	@Override
	public boolean isEnabled() {
		return true; //사용할 수 있는 계정입니까? 
	}
	
}
