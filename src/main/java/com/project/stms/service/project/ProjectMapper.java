package com.project.stms.service.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.stms.command.FileVO;
import com.project.stms.command.ProjectVO;
import com.project.stms.command.ServerVO;
import com.project.stms.command.TaskVO;
import com.project.stms.command.UserVO;
import com.project.stms.util.Criteria;

@Mapper
public interface ProjectMapper {
	
	public List<ProjectVO> getList();
	
	public List<ProjectVO> getRequestList();
	
	public void requestProject(ProjectVO vo);
	
	public void insertFiles(FileVO list);
	
	public ProjectVO getProjectDetail(int pjt_id);
	
	public UserVO getCustomerUserDetail(int pjt_id);
	
	public UserVO getAdminUserDetail(int pjt_id);
	
	//
	public List<UserVO> getNormalUserDetail(int pjt_id);
	
	// 모달창에서 작업자 리스트 띄우기
	public List<UserVO> getNormalUserDetailByPage(@Param("pjt_id") int pjt_id, @Param("cri") Criteria cri);
	
	// 작업 리스트 가져오기
	public List<TaskVO> getTaskDetail(int pjt_id);
	
	// 완료된 작업의 비율 가져오기
	public Integer getCompletedTask(@Param("pjt_date") String pjt_date, @Param("pjt_id") int pjt_id);
	
	// 필터로 프로젝트 검색
	public List<ProjectVO> getFilteredList(@Param("server_type") String server_type, @Param("pjt_end_dt") String pjt_end_dt, @Param("ins_user_id") String ins_user_id);

	// 이름으로 프로젝트 검색
	public List<ProjectVO> getProjectByName(String pjt_nm);
	
	// 총 작업자 수
	public int getTotal();
	
	// 작업에 참여하지 않은 작업자 수
	public int getNotAddedTotal(int pjt_id);

	// 프로젝트 최종 생성
	public void updateProjectInfo(ProjectVO vo);
	
	// pjt_mem 테이블에 작업자 정보 기입
	public void insertUserInfo(@Param("user_id") String user_id, @Param("pjt_id") int pjt_id);
	
	// 고객사 별 보유 서버 리스트
	public List<ServerVO> getMyServer(String user_id);
	
	// 프로젝트에 참여한 작업자 리스트
	public List<UserVO> getUserByProject(int pjt_id);
	
	// 프로젝트에 참여하지 않은 작업자 리스트
	public List<UserVO> getMemberNotAdded(@Param("pjt_id") int pjt_id, @Param("cri") Criteria cri);
	
	// 세부창에서 프로젝트에 참여하는 멤버 업데이트
	public void updateMemberAtProject(@Param("user_id") String user_id, @Param("pjt_id") int pjt_id);
	
	// 파일업로드시 필요한 정보 가져오기
	public ProjectVO getProjectInfoForFiles();
}
