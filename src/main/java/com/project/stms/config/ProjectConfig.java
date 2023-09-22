package com.project.stms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.stms.interceptor.ProjectInterceptor;
import com.project.stms.interceptor.UserAuthInterceptor;


@Configuration
public class ProjectConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new ProjectInterceptor());
		
		//로그인 시 권한별 경로 처리
		registry.addInterceptor(new UserAuthInterceptor()).addPathPatterns("/");
		
		
		
	}
	
	

}
