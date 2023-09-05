package com.project.stms.service.project;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.stms.command.FileVO;
import com.project.stms.command.ProjectVO;

public interface ProjectService {
	
	public List<ProjectVO> getList();
	
	public void insertFiles(List<MultipartFile> list);

}
