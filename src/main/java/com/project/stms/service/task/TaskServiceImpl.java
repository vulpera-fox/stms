package com.project.stms.service.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void deleteTaskList(int task_id) {
		
		taskMapper.deleteTaskList(task_id);
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

	public ArrayList<ProjectVO> getPjtList(Criteria cri) {
		
		return taskMapper.getPjtList(cri);
	}


	@Override
	public ArrayList<TaskVO> getTemplateList() {
		
		return taskMapper.getTemplateList();
	}


	@Override
	public void deleteTemplate(Integer tem_id) {
		
		taskMapper.deleteTemplate(tem_id);
	}


	@Override
	public ArrayList<TaskVO> getTaskSearch(Integer pjt_id) {
		
		return taskMapper.getTaskSearch(pjt_id);
	}


	@Override
	public TaskVO getTaskDetailSearch(Integer task_id) {
		
		return taskMapper.getTaskDetailSearch(task_id);
	}


	@Override
	public void updateTask(TaskVO vo) {
		
		System.out.println("서비스에서"+ vo.toString());
		
		taskMapper.updateTask(vo);
	}

	
	@Override
	public ArrayList<TaskVO> getRegBoxA(Integer pjt_id) {
		
		return taskMapper.getRegBoxA(pjt_id);
	}
	

	@Override
	public ArrayList<TaskVO> getPjtMembers(Integer pjt_id) {
		
		return taskMapper.getPjtMembers(pjt_id);
	}


	@Override
	public ArrayList<TaskVO> getTaskTemp() {
		
		return taskMapper.getTaskTemp();
	}


	@Override
	public int taskRegist(TaskVO vo) {
		
		
		return taskMapper.taskRegist(vo);
	}


	@Override
	public TaskVO applyTemp(String tem_nm) {
		
		return taskMapper.applyTemp(tem_nm);
	}

	@Override
	public int modiTask(TaskVO vo) {
		
		return taskMapper.modiTask(vo);

	}

	@Override
	public void changeMember(int task_id) {

	taskMapper.changeMember(task_id);

	}


	@Override
	public TaskVO getTaskDetail(int task_id) {

	return taskMapper.getTaskDetail(task_id);
	}
	

	@Override
	public void checkStartTime(TaskVO vo) {
		taskMapper.checkStartTime(vo);
		
	}


	@Override
	public void checkEndTime(TaskVO vo) {
		taskMapper.checkEndTime(vo);
		
	}


	@Override
	public List<TaskVO> getCalendar() {
		
		return taskMapper.getCalendar();
	}


	@Override
	public void registComment(int task_id, String comment_dtl) {
		taskMapper.registComment(task_id, comment_dtl);
		
	}


//	@Override
//	public void delTask(int task_id) {
//		taskMapper.delTask(task_id);
//	}
	
	
		
}
