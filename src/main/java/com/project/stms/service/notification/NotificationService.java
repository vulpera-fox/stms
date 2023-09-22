package com.project.stms.service.notification;

import java.util.ArrayList;

import com.project.stms.command.NotificationVO;
import com.project.stms.util.Criteria;

public interface NotificationService {
	// 알림기능
	public ArrayList<NotificationVO> getCount(String rcv_id);
	
	public ArrayList<NotificationVO> getList(String rcv_id);
	
	public void getReadChk(int noti_id);
	
	public void changeDelStatus(int noti_id, String rcv_del_yn);
	
	public void markAllAsRead(String rcv_id);
	
	public ArrayList<NotificationVO> getPopUpList(String rcv_id, String category);
	
	public ArrayList<NotificationVO> getSearchResult(Criteria cri, String rcv_id);
	
	public void createProjectNotification(String rcv_id, String send_id, String pjt_nm);

	public void createTaskNotification(String rcv_id, String task_date, String task_nm, int pjt_id);
	
	public void createChangeMemberNotification(String rcv_id);
//	public ArrayList<NotificationVO> getData(int pageNumber);
		
}
