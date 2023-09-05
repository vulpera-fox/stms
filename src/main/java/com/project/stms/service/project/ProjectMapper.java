package com.project.stms.service.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.stms.command.FileVO;
import com.project.stms.command.ProjectVO;

@Mapper
public interface ProjectMapper {
	
	public List<ProjectVO> getList();
	
	public void insertFiles(List<FileVO> list);

}
