package com.project.stms.task.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//	@Autowired
//	private TaskMapper taskMapper;
	
	
	
}
