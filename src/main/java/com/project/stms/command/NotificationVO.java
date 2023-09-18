package com.project.stms.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationVO {
	private Integer noti_id; //알림ID
	private Integer pre_noti_id; //이전알림ID
	private String rcv_id; //수신자ID
	private String send_id; //발신자ID
	private String noti_nm; //알림제목
	private String noti_dtl; //알림내용
	private String noti_send_dt; //알림발송일시
	private String noti_chk_dt; //알림확인일시
	private String task_date;
	private String rcv_del_yn;
	private String send_del_yn;
	private Integer num;
	private Integer count_pre_noti;
	private String category;
	private String stat;
	private Integer count;
	private String url;
	
	
	
}
