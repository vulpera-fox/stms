package com.project.stms.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	//비밀번호 암호화객체
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeHttpRequests(authorize -> authorize.antMatchers("/main").hasAnyRole("CUSTOMER", "ADMIN", "ENGINEER")
														 .antMatchers("/customer/**").hasRole("CUSTOMER")
														 .antMatchers("/engineer/**").hasRole("ENGINEER")
														 .antMatchers("/admin/**").hasRole("ADMIN")
														 .anyRequest().permitAll());




		//시큐리티 설정파일 만들면, 시큐리티가 제공하는 기본 로그인페이지가 보이지 않게됩니다.
		//시큐리티가 사용하는 기본로그인 페이지를 사용함
		//권한이나 인증이 되지 않으면 기본으로 선언된 로그인페이지를 보여주게 됩니다.
		//http.formLogin(Customizer.withDefaults() );//기본 로그인 페이지 사용

		//사용자가 제공하는 폼기반 로그인기능을 사용할 수 있습니다.
		http.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/loginForm") //로그인시도 요청경로 -> 스프링이 로그인 시도를 낚아채서 UserDetailService 객체로 연결
		.defaultSuccessUrl("/main") //로그인 성공시 페이지
		.usernameParameter("user_email")//username말고 user_email로 바꿈
		.passwordParameter("user_pw") //password말고 user_pw로 바꿈
		.failureUrl("/login?err=true") //로그인 실패시 이동할 url
		.and()
		.exceptionHandling().accessDeniedPage("/deny") //권한이 없을때 이동할 리다이렉트 경로
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/hello");//default로그아웃 경로 /logout, /logout 주소를 직접 작성할 수 있고, 로그아웃 성공시 리다이렉트 할 경로



		return http.build();
	}
}