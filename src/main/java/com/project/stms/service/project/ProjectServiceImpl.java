package com.project.stms.service.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.stms.command.FileVO;
import com.project.stms.command.ProjectVO;
import com.project.stms.command.ServerVO;
import com.project.stms.command.TaskVO;
import com.project.stms.command.UserVO;
import com.project.stms.service.s3.S3Service;
import com.project.stms.util.Criteria;
import com.project.stms.util.ProjectCriteria;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	
	
	@Autowired
	private ProjectMapper projectMapper;
	
	@Autowired
	private S3Service s3Service;
	
	

	@Override
	public List<ProjectVO> getList(ProjectCriteria cri) {
		
		return projectMapper.getList(cri);
	}

	@Override
	public List<ProjectVO> getRequestList(ProjectCriteria cri) {
		return projectMapper.getRequestList(cri);
	}
	
	@Override
	public List<ProjectVO> getRoledList(String user_id, ProjectCriteria cri) {
		return projectMapper.getRoledList(user_id, cri);
	}

	@Override
	public void requestProject(ProjectVO vo) {
		projectMapper.requestProject(vo);
	}

	@Override
	public void insertFiles(List<MultipartFile> list, int pjt_id) {
		
		
		for(MultipartFile file : list) {
			
			String originName = file.getOriginalFilename();
			
			// 무작위 난수
			String rdmID = UUID.randomUUID().toString();
			
			// 저장될 파일의 이름
			String file_nm = rdmID + "_" + originName;
			
			System.out.println(file_nm);
			
			
			FileVO uploadFile = FileVO.builder().file_serial_num(rdmID).org_file_nm(originName).file_nm(file_nm).pjt_id(pjt_id).build();
			
			System.out.println(uploadFile);
			
			projectMapper.insertFiles(uploadFile);
			
			try {
				s3Service.uploadFiles(file_nm, file.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}


	@Override
	public ProjectVO getProjectDetail(int pjt_id) {
		return projectMapper.getProjectDetail(pjt_id);
	}


	@Override
	public UserVO getCustomerUserDetail(int pjt_id) {
		return projectMapper.getCustomerUserDetail(pjt_id);
	}
	
	@Override
	public UserVO getAdminUserDetail(int pjt_id) {
		return projectMapper.getAdminUserDetail(pjt_id);
	}
	
	@Override
	public List<UserVO> getNormalUserDetail(int pjt_id) {
		return projectMapper.getNormalUserDetail(pjt_id);
	}


	@Override
	public List<TaskVO> getTaskDetail(int pjt_id) {
		return projectMapper.getTaskDetail(pjt_id);
	}


	@Override
	public Integer getCompletedTask(String pjt_Date, int pjt_id) {
		return projectMapper.getCompletedTask(pjt_Date, pjt_id);
	}


	@Override
	public List<ProjectVO> getFilteredList(String server_type, String pjt_end_dt, String ins_user_id) {
		return projectMapper.getFilteredList(server_type, pjt_end_dt, ins_user_id);
	}


	@Override
	public List<ProjectVO> getProjectByName(String pjt_nm) {
		return projectMapper.getProjectByName(pjt_nm);
	}


	@Override
	public List<UserVO> getNormalUserDetailByPage(int pjt_id) {
		return projectMapper.getNormalUserDetailByPage(pjt_id);
	}


	@Override
	public int getTotal(ProjectCriteria cri) {
		return projectMapper.getTotal(cri);
	}


	@Override
	public int getNotAddedTotal(int pjt_id) {
		return projectMapper.getNotAddedTotal(pjt_id);
	}


	@Override
	public void updateProjectInfo(ProjectVO vo) {
		projectMapper.updateProjectInfo(vo);
	}


	@Override
	public void insertUserInfo(String user_id, int pjt_id) {
		projectMapper.insertUserInfo(user_id, pjt_id);
	}


	@Override
	public List<ServerVO> getMyServer(String user_id) {
		return projectMapper.getMyServer(user_id);
	}


	@Override
	public List<UserVO> getUserByProject(int pjt_id) {
		return projectMapper.getUserByProject(pjt_id);
	}


	@Override
	public List<UserVO> getMemberNotAdded(int pjt_id, Criteria cri) {
		return projectMapper.getMemberNotAdded(pjt_id, cri);
	}


	@Override
	public void updateMemberAtProject(String user_id, int pjt_id) {
		projectMapper.updateMemberAtProject(user_id, pjt_id);
	}


	@Override
	public ProjectVO getProjectInfoForFiles() {
		return projectMapper.getProjectInfoForFiles();
	}


	@Override
	public List<FileVO> getFileName(int pjt_id) {
		return projectMapper.getFileName(pjt_id);
	}

	@Override
	public void deleteProject(int pjt_id) {
		projectMapper.deleteProject(pjt_id);
	}

	@Override
	public void modifyProject(ProjectVO vo) {
		projectMapper.modifyProject(vo);
		
	}

	@Override
	public int getRoledTotal(String user_id, ProjectCriteria cri) {
		return projectMapper.getRoledTotal(user_id, cri);
	}

	@Override
	public int getFilteredTotal(String server_type, String pjt_end_dt, String ins_user_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSearchNameTotal(String pjt_nm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void registServer(ServerVO vo) {
		projectMapper.registServer(vo);
	}

	@Override
	public void modifyServer(ServerVO vo) {
		projectMapper.modifyServer(vo);
	}

	@Override
	public void deleteServer(int server_id) {
		projectMapper.deleteServer(server_id);
	}

	@Override
	public ServerVO getMyServerDetail(int server_id) {
		return projectMapper.getMyServerDetail(server_id);
	}




	


	
	
	

}
