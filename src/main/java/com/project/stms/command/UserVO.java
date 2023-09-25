package com.project.stms.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
	
	@NotBlank(message = "아이디는 필수입니다") 
	@Email(message = "이메일 형식이어야 합니다")
	private String user_email;
	
	@NotEmpty(message = "비밀번호는 필수입니다")
	private String user_pw;
	
	@NotEmpty(message = "이름은 필수입니다")
	private String user_nm; //사용자 이름
	private String user_group;
	private String user_id; //유저 식별번호 이메일 아이디와 다름
	private String user_role; //customer, engineer, admin
	private String user_auth_yn; //이메일 인증 여부
	private String user_adr;
	private String org_file_nm;
	
}