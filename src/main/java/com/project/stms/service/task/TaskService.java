package com.project.stms.service.task;

import java.util.ArrayList;

import com.project.stms.command.ProjectVO;
import com.project.stms.command.TaskVO;
import com.project.stms.util.Criteria;

public interface TaskService {

	//작업 조회
	public ArrayList<TaskVO> getTaskList(Criteria cri);
	//데이터 총 개수 구하기
	public int getTotal(Criteria cri);
	//리스트 삭제
	public int deleteTaskList(int task_id);
	//수정페이지 띄우기(전체 값 입력 되도록)
	public TaskVO getModify(int task_id);
	
	//템플릿등록
	public int getTemplate(TaskVO vo);
	//템플릿조회
	public ArrayList<TaskVO> getTemplateList();
	//템플릿삭제
	public void deleteTemplate(Integer tem_id);
	
	
	
	//모달(프로젝트 정보 등록)
	//public ArrayList<ProjectVO> regPjtList();
	//모달(프로젝트 정보 검색)
	public ArrayList<ProjectVO> getPjtList(); 
	
}
