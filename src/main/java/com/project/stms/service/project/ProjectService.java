package com.project.stms.service.project;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.stms.command.FileVO;
import com.project.stms.command.ProjectVO;
import com.project.stms.command.ServerVO;
import com.project.stms.command.TaskVO;
import com.project.stms.command.UserVO;
import com.project.stms.util.Criteria;
import com.project.stms.util.ProjectCriteria;

public interface ProjectService {
	
	public List<ProjectVO> getList(ProjectCriteria cri);
	
	public List<ProjectVO> getRequestList(ProjectCriteria cri);
	
	public List<ProjectVO> getRoledList(String user_id, ProjectCriteria cri);
	
	public void requestProject(ProjectVO vo);
	
	public void insertFiles(List<MultipartFile> list, int pjt_id);
	
	public ProjectVO getProjectDetail(int pjt_id);
	
	public UserVO getCustomerUserDetail(int pjt_id);
	
	public UserVO getAdminUserDetail(int pjt_id);
	
	public List<UserVO> getNormalUserDetail(int pjt_id);
	
	public List<UserVO> getNormalUserDetailByPage(int pjt_id);
	
	public List<TaskVO> getTaskDetail(int pjt_id);
	
	public Integer getCompletedTask(String pjt_date, int pjt_id);
	
	public List<ProjectVO> getFilteredList(String server_type, String pjt_end_dt, String ins_user_id);
	
	public List<ProjectVO> getProjectByName(String pjt_nm);
	
	public int getTotal(ProjectCriteria cri);
	
	public int getRoledTotal(String user_id, ProjectCriteria cri);
	
	public int getFilteredTotal(String server_type, String pjt_end_dt, String ins_user_id);
	
	public int getSearchNameTotal(String pjt_nm);
	
	public int getNotAddedTotal(int pjt_id);
	
	public void updateProjectInfo(ProjectVO vo);
	
	public void insertUserInfo(String user_id, int pjt_id);
	
	public List<ServerVO> getMyServer(String user_id);
	
	public List<UserVO> getUserByProject(int pjt_id);
	
	public List<UserVO> getMemberNotAdded(int pjt_id, Criteria cri);
	
	public void updateMemberAtProject(String user_id, int pjt_id);
	
	public ProjectVO getProjectInfoForFiles();
	
	public List<FileVO> getFileName(int pjt_id);
	
	public void deleteProject(int pjt_id);
	
	public void modifyProject(ProjectVO vo);
	
	public void registServer(ServerVO vo);
	
	public void modifyServer(ServerVO vo);
	
	public void deleteServer(int server_id);
	
	public ServerVO getMyServerDetail(int server_id);
	
	
	
	
}
