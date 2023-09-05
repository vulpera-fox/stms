package com.project.stms.controller.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.stms.command.FileVO;
import com.project.stms.command.ProjectVO;
import com.project.stms.service.project.ProjectService;

@Controller
@RequestMapping("project")
public class ProjectController {
	
	@Autowired
	@Qualifier("projectService")
	ProjectService projectService;
	
	
	@GetMapping("/ProjectMain")
	public String ProjectMain(ProjectVO vo, Model mo) {
		
		List<ProjectVO> pList = projectService.getList();
		
		mo.addAttribute("pList", pList);
		
		return "/project/ProjectMain";
	}
	
	@GetMapping("/ProjectRegist")
	public String ProjectRegist() {
		
		
		
		return "/project/ProjectRegist";
	}
	
	
	
	@PostMapping("/registForm")
	public String registForm(ProjectVO vo,
							 @RequestParam(required = false, name = "file") List<MultipartFile> list) {
		
		if(list.size() > 0) {
			projectService.insertFiles(list);
		}
		
		
//		System.out.println("1");
		System.out.println(vo.toString());
		
		return "redirect:/project/ProjectMain";
	}
	
	@GetMapping("/ProjectCreate")
	public String ProjectCreate() {
		
		
		return "/project/ProjectCreate";
	}
	
}
