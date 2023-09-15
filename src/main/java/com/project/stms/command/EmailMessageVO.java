package com.project.stms.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailMessageVO {
	private String to;
	private String subject;
	private String message;
}
