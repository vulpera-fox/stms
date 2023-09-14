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
import com.project.stms.command.UserVO;

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
	public List<UserVO> getUserDetail(int pjt_id) {
		return projectMapper.getUserDetail(pjt_id);
	}

}
