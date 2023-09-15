package com.project.stms.service.task;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.stms.command.ProjectVO;
import com.project.stms.command.TaskVO;
import com.project.stms.util.Criteria;

@Service("taskService")
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskMapper taskMapper;
	
	
	@Override
	public ArrayList<TaskVO> getTaskList(Criteria cri) {
		
		return taskMapper.getTaskList(cri);
	}


	@Override
	public int getTotal(Criteria cri) {
		
		return taskMapper.getTotal(cri);
	}


	@Override
	public int deleteTaskList(int task_id) {
		
		return taskMapper.deleteTaskList(task_id);
	}


	@Override
	public TaskVO getModify(int task_id) {
		
		return taskMapper.getModify(task_id);
	}


	@Override
	public int getTemplate(TaskVO vo) {
		
		return taskMapper.getTemplate(vo);
	}
	
	
	@Override
	public ArrayList<ProjectVO> getPjtList() {
		
		return taskMapper.getPjtList();
	}


	@Override
	public ArrayList<TaskVO> getTemplateList() {
		
		return taskMapper.getTemplateList();
	}


	@Override
	public void deleteTemplate(Integer tem_id) {
		
		taskMapper.deleteTaskList(tem_id);
	}





//	@Override
//	public ArrayList<ProjectVO> getPjtList() {
//		
//		return taskMapper.getPjtList();
//	}

//	@Autowired
//	private TaskMapper taskMapper;
	
	
	
}
