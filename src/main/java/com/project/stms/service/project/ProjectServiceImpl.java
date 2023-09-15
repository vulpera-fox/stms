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
import com.project.stms.util.Criteria;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	
	
	@Autowired
	private ProjectMapper projectMapper;
	
	private String file_path = "/Users/uwu/Desktop/pjtImg";
	

	@Override
	public List<ProjectVO> getList() {
		
		return projectMapper.getList();
	}
	

	@Override
	public List<ProjectVO> getRequestList() {
		return projectMapper.getRequestList();
	}

	@Override
	public void requestProject(ProjectVO vo) {
		projectMapper.requestProject(vo);
	}





	@Override
	public void insertFiles(List<MultipartFile> list) {
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		
		for(MultipartFile file : list) {
			System.out.println(file.getOriginalFilename());
			
			
			// 무작위 난수
			String rdmID = UUID.randomUUID().toString();
			
			// 저장될 파일의 이름
			String file_nm = rdmID + "_" + file.getOriginalFilename();
			
			// 파일이 저장된 경로
			String save_path = file_path + "/" + file_nm;
			
			System.out.println(save_path);
			
			try {
				File saveFile = new File(save_path);
				
				file.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("업로드 실패");
			}
			
			
			FileVO vo = FileVO.builder().org_file_nm(file.getOriginalFilename())
										.file_serial_num(rdmID)
										.file_nm(file_nm)
										.file_path(file_path)
										.build();
			
			fileList.add(vo);
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
	public List<UserVO> getNormalUserDetailByPage(int pjt_id, Criteria cri) {
		return projectMapper.getNormalUserDetailByPage(pjt_id, cri);
	}


	@Override
	public int getTotal() {
		return projectMapper.getTotal();
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
	
	
	

}
