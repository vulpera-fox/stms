package com.project.stms.service.project;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.stms.command.FileVO;
import com.project.stms.command.ProjectVO;
import com.project.stms.command.UserVO;

public interface ProjectService {
	
	public List<ProjectVO> getList();
	
	public void insertFiles(List<MultipartFile> list);
	
	public ProjectVO getProjectDetail(int pjt_id);
	
	public List<UserVO> getUserDetail(int pjt_id);
	

}
