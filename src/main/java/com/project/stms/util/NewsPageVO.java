package com.project.stms.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class NewsPageVO {
	
	private int start;
	private int end;
	private boolean prev;
	private boolean next;
	private int total;
	private int realEnd;
	
	private int page;
	private int amount;
	private NewsCriteria cri;
	
	private int pageCount = 5;
	
	private List<Integer> pageList;
	
	public NewsPageVO(NewsCriteria cri, int total) {
		
		this.cri = cri;
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		
	
	this.end = (int)(Math.ceil(this.page / (double)this.pageCount)) * this.pageCount;
	
	
	this.start = this.end - this.pageCount +1;
	this.realEnd = (int)(Math.ceil(this.total / (double) this.amount));
	
	
	if(this.end > this.realEnd) this.end = this.realEnd;
	this.prev = this.page > 1;
	this.next = this.realEnd > this.end;
	
	this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
	
	}
	

}
