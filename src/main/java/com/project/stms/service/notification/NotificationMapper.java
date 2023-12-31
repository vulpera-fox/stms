package com.project.stms.service.notification;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.stms.command.NotificationVO;
import com.project.stms.util.Criteria;

@Mapper
public interface NotificationMapper {
	
	public ArrayList<NotificationVO> getCount(@Param("rcv_id")String rcv_id, @Param("user_role")String user_role);
	
	public ArrayList<NotificationVO> getList(String rcv_id);
	
	public void getReadChk(int noti_id);

	public void changeDelStatus(@Param("noti_id")int noti_id, @Param("rcv_del_yn")String rcv_del_yn);
	
	public void markAllAsRead(String rcv_id);
	
	public ArrayList<NotificationVO> getPopUpList(@Param("rcv_id") String rcv_id, @Param("category")String category, @Param("user_role")String user_role);
	
	public ArrayList<NotificationVO> getSearchResult(@Param("cri")Criteria cri, 
													 @Param("rcv_id")String rcv_id);
	
	public void createProjectNotification(@Param("rcv_id")String rcv_id, @Param("send_id")String send_id, @Param("pjt_nm")String pjt_nm);
	
	public void createTaskNotification(@Param("rcv_id")String rcv_id, @Param("task_date")String task_date, @Param("task_nm")String task_nm, @Param("pjt_id")int pjt_id);
	
	public void createChangeMemberNotification(String rcv_id);
	
	//	public ArrayList<NotificationVO> getData(int pageNumber);
	
}
