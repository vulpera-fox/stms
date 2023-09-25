package com.project.stms.service.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.stms.command.ProjectVO;
import com.project.stms.command.TaskVO;
import com.project.stms.util.Criteria;

@Mapper
public interface TaskMapper {

	//작업 조회
	public ArrayList<TaskVO> getTaskList(@Param("cri") Criteria cri);
	//데이터 총 개수 구하기
	public int getTotal(@Param("cri") Criteria cri);
	//리스트 삭제
	public void deleteTaskList(int task_id);
	//수정페이지 띄우기(전체 값 입력 되도록)
	public TaskVO getModify(int task_id);
	//작업상세페이지
	public TaskVO getTaskDetail(int task_id);
	
	//템플릿등록
	public int getTemplate(TaskVO vo);
	//템플릿조회
	public ArrayList<TaskVO> getTemplateList();
	//템플릿삭제
	public void deleteTemplate(@Param("tem_id") Integer tem_id);	

	//관리자 작업관리 페이지
	//프로젝트 목록 조회
	public ArrayList<ProjectVO> getPjtList(@Param("cri") Criteria cri);
	//프로젝트에 맞는 작업 목록 조회
	public ArrayList<TaskVO> getTaskSearch(Integer pjt_id);
	//작업 상세내용 불러오기
	public TaskVO getTaskDetailSearch(Integer task_id);
	//작업 수정
	public void updateTask(TaskVO vo);
	
	//작업등록카드(타입A:작업목록 있는 경우)생성
	public ArrayList<TaskVO> getRegBoxA(Integer pjt_id);
	
	
	//프로젝트 멤버(엔지니어) 조회
	public ArrayList<TaskVO> getPjtMembers(@Param("pjt_id") Integer pjt_id);
	
	
	//작업등록시 템플릿 리스트 조회
	public ArrayList<TaskVO> getTaskTemp();
	
	//템플릿 적용
		public TaskVO applyTemp(@Param("tem_id")int tem_id);
	
	//작업등록(관리자)
	public int taskRegist(TaskVO vo);
	
	//작업수정(작업자)
	public int modiTask(TaskVO vo);
	
	//작업 변경 요청(작업자)
	public void changeMember(int task_id);
	
	//작업시작시간 체크
	public void checkStartTime(TaskVO vo);
	
	//작업종료시간 체크
	public void checkEndTime(TaskVO vo);
	
	//캘린더뷰 조회
	public List<TaskVO> getCalendar();
	
	//작업결과 등록
	public void registComment(@Param("task_id") int task_id,
							  @Param("comment_dtl") String comment_dtl);
	
	//작업삭제
	//public void delTask(int task_id);
	
	//모달(프로젝트 정보 등록)
//	public ArrayList<ProjectVO> regPjtList();

}
