package com.project.stms.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectVO {
	
	
	private Integer pjt_id;
	private String pjt_nm;
	private String pjt_dtl;
	private String file_no;
	private String pjt_st_dt;
	private String pjt_end_dt;
	private String req_user_id;
	private String ins_user_id;
	private Integer status;
	private String server_id;
	private String server_model;
	private String user_group;
	private String user_nm;
	private String created_yn;
	
}
