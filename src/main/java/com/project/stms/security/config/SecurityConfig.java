package com.project.stms.security.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.stms.security.filter.CustomLoginFilter;
import com.project.stms.security.filter.JwtAuthorizationFilter;


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
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//기본로그인 방식, 세션, 베이직인증, csrf토큰 전부 사용하지 않음
		http.csrf().disable();
		http.formLogin() //form 기반 로그인을 사용할거임 //rest기반으로 할거면 .disable()
			.loginPage("/log");
		http.httpBasic().disable(); //Authorization : 아이디 => 형식으로 넘어오는 basic 인증을 사용하지 않음
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션 인증 기반을 사용하지 않고 jwt 사용해서 인증
		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); //모든 요청은 전부 허용
		//1. 크로스오리진 필터 생성 cors
		http.cors(Customizer.withDefaults());
		
		//3. 로그인 시도에 AuthenticationManager가 필요함
		//AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		//++UserDetailService객체 and PasswordEncoder 가 반드시 필요
		AuthenticationManager authenticationManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
		System.out.println(authenticationManager);
		
		
		
		//6. 요청별로 필터 실행시키기
		// /login 요청에만 CustomLoginFilter가 실행됨
		http.requestMatchers().antMatchers("/login")
							  .and()
							  .addFilter(new CustomLoginFilter(authenticationManager));
						
		//api로 시작하는 요청에만 jwt필터 실행됨
		http.requestMatchers().antMatchers("/question/**")
							  .antMatchers("/engineer/**")
							  .antMatchers("/admin/**")
							  .and()
							  .addFilter(new JwtAuthorizationFilter(authenticationManager));
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*")); //모든 요청 주소를 허용함
		configuration.setAllowedMethods(Arrays.asList("*")); //모든 요청 메서드를 허용함
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	 @Bean
	   public HttpFirewall allowSemicolonHttpFirewall() {
	      StrictHttpFirewall firewall = new StrictHttpFirewall();
	      firewall.setAllowSemicolon(true); //세미콜론 허용
	      return firewall;
	   }


}

