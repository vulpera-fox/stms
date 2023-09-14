package com.project.stms.service.notification;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.stms.command.NotificationVO;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	private NotificationMapper notificationMapper;
	
	
	@Override
	public int getCount(String rcv_id) {
		return notificationMapper.getCount(rcv_id);
	}

	@Override
	public ArrayList<NotificationVO> getList(String rcv_id) {
		return notificationMapper.getList(rcv_id);
	}
	
	@Override
	public void getReadChk(int noti_id) {
		notificationMapper.getReadChk(noti_id);
	}

	@Override
	public void changeDelStatus(int noti_id) {
		notificationMapper.changeDelStatus(noti_id);
	}

	@Override
	public void markAllAsRead(String rcv_id) {
		notificationMapper.markAllAsRead(rcv_id);
	}


}
