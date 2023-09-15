package com.project.stms.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class PageVO {

	private int start; //시작 페이지네이션
	private int end; //끝 페이지네이션
	private boolean prev; //이전 버튼 활성화 여부
	private boolean next; //다음 버튼 활성화 여부
	private int realEnd; //실제 보여지는 끝번호
	
	private int total; //전체 게시글 수
	private int page; //cri에 있는 현재 조회중인 페이지
	private int amount; //cri에 있는 데이터 개수
	private Criteria cri; //페이지 기준

	private int pnCount = 10; //페이지네이션 개수 - 화면에 보여질 페이지 개수
	
	private List<Integer> pageList; //페이지네이션을 리스트로 저장
	
	//페이지네이션 클래스는 cri와 total을 매개변수로 받음
	public PageVO(Criteria cri, int total) {
		this.cri = cri;
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;

		//end페이지 계산
		this.end = (int)(Math.ceil(this.page / (double)this.pnCount)) * this.pnCount;
		
		//start페이지 계산
		this.start = this.end - this.pnCount + 1;
		
		//realEnd 계산 amount(데이터개수 10개 기준)
		this.realEnd = (int)(Math.ceil(this.total / (double)this.amount));
		
		//end페이지 재결정
		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		
		//prev활성화 여부
		this.prev = this.start > 1;
		
		//next활성화 여부
		this.next = this.realEnd > this.end;
		
		//리스트에 페이지네이션 담음
		this.pageList = IntStream.rangeClosed(this.start, this.end)
								 .boxed().collect(Collectors.toList());
		
	}
	
}
