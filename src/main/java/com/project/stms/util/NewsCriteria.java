package com.project.stms.util;

import lombok.Data;

@Data
public class NewsCriteria {

	private int page;
	private int amount;
	
	private String searchType;
	private String searchKeyword;
	
	public NewsCriteria() {
		
		this.page = 1;
		this.amount = 10;
		
		
	}
	

	
	public NewsCriteria(int page, int amount) {
		
		this.page = page;
		this.amount = amount;
		
	}
	
	public int getPageStart() {
		
		return (page-1) * amount;
		
	}
	
	
	
	
}
