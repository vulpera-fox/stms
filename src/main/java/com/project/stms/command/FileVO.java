package com.project.stms.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileVO {
	
	
	private Integer file_no;
	private String file_serial_num;
	private String org_file_nm;
	private String file_nm;
	private String file_path;
	private Integer file_size;
	private Timestamp regdate;

}
