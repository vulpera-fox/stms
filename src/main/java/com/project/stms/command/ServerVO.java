package com.project.stms.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServerVO {
	
	
	
	private Integer server_id;
	private String server_type;
	private String server_model;
	private String server_vender;
	private String server_prod_dt;
	private String server_loca;
	private Integer server_cpu_core;
	private Integer server_memory;
	private String user_id;

}
