package com.project.stms.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.stms.command.UserVO;





@Service //서비스 빈으로 선언 반드시 선언해야함
public class MyUserDetailService implements UserDetailsService{

	
	@Autowired
	private UserMapper userMapper;
	//loginProcessURL이 호출될 때 loadUserByUsername함수를 자동으로 연결
	//화면에서는 기본적으로 username이라는 파라미터로 지정해야합니다.
	@Override
	public UserDetails loadUserByUsername(String user_email) throws UsernameNotFoundException {

		
		System.out.println("==============loadUserByUsername===================");
		
		System.out.println(user_email);
		//로그인 처리 - password는 시큐리티가 알아서 처리
		UserVO vo = userMapper.login(user_email);
		
		System.out.println(vo);
		
		//vo가 null이 아니라는 것은 회원정보가 있다.
		if(vo != null) {
			//스프링 시큐리티가 이값을 받아가서 password를 확인한 후에, 정상유저라고 판별이 되면
			//시큐리티세션(new Authentication(new MyuserDetails)) 형태로 세션에 저장을 시킵니다.
			return new MyUserDetails(vo);
			//시큐리티 설정파일에 defaultSuccessURL을 작성
		}
		
		
		
		return null;
	}

}
