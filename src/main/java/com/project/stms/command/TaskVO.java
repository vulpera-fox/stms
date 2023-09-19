package com.project.stms.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskVO {
	
	private Integer task_id;
	private Integer pjt_id;
	private Integer server_id;
	private String task_nm; //작업제목
	private String task_dtl; //작업내용
	
	private String user_group; //고객사 이름(회사명)
	private String user_id; //작업자 아이디

	private String user_adr; //작업지
	private String user_nm; //작업자 이름
	
	private String status; //작업상태
	private String task_date; //작업일
	private String task_st_dt; //작업시작일시
	private LocalDateTime task_end_dt; //작업종료일시
	private Integer task_al_t; //작업예상시간
	private Integer task_oper_t; //작업생성시간

	private Integer completed_task;
	
	private Integer tem_id;
	private String tem_nm;
	private String pred_time;
	
}
