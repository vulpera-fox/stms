package com.project.stms.util;

import lombok.Data;

@Data
public class Criteria {

	private int page; //조회 페이지
	private int amount; //데이터 개수
	
	//검색에 필요한 키워드를 선언
	private String searchTaskName; //작업명 검색
	private String searchCustomer; //고객사 검색
	private String searchEngineer; //담당자 검색
	

	private String searchType;
	private String searchKeyword;
	private String searchPjtName; //작업등록 시 프로젝트 목록 검색

	//기본 값(기본 생성자로 만들어졌을 때 1, 10)
	public Criteria() {
		this.page = 1;
		this.amount = 10;
	}
	
	//기본 생성자 아니면 값 전달받음
	public Criteria(int page, int amount) {
		this.page = page;
		this.amount = amount;
	}
	
	//페이지 시작을 지정하는 getter
	public int getPageStart() {
		return (page - 1) * amount;
	}
	
}
