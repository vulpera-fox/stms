package com.project.stms.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsVO { // post 테이블..

	private Integer post_id;
	private Integer board_id;
	private char post_secret_yn;
	private String post_pw;
	private String post_title;
	private String user_id;
	private LocalDateTime post_regdate;
	private String post_contents;
	private String file_id;
	private Integer post_hits;
	private char post_delete_yn;
	private Integer org_id;
	
	
	
}
