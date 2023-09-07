package com.project.stms.service.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.stms.command.FileVO;
import com.project.stms.command.ProjectVO;
import com.project.stms.command.UserVO;

@Mapper
public interface ProjectMapper {
	
	public List<ProjectVO> getList();
	
	public void insertFiles(List<FileVO> list);
	
	public ProjectVO getProjectDetail(int pjt_id);
	
	public List<UserVO> getUserDetail(int pjt_id);

}
