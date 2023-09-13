package com.project.stms.service.notification;

import java.util.ArrayList;

import com.project.stms.command.NotificationVO;

public interface NotificationService {
	
	public int getCount(String rcv_id);
	
	public ArrayList<NotificationVO> getList(String rcv_id);
	
	public void getReadChk(int noti_id);
	
	public void changeDelStatus(int noti_id);
	
	public void markAllAsRead(String rcv_id);
}
