package com.project.stms.service.notification;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.stms.command.NotificationVO;
import com.project.stms.util.Criteria;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	private NotificationMapper notificationMapper;
	
	
	@Override
	public ArrayList<NotificationVO> getCount(String rcv_id) {
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
	public void changeDelStatus(int noti_id, String rcv_del_yn) {
		notificationMapper.changeDelStatus(noti_id, rcv_del_yn);
	}

	@Override
	public void markAllAsRead(String rcv_id) {
		notificationMapper.markAllAsRead(rcv_id);
	}

	@Override
	public ArrayList<NotificationVO> getPopUpList(String rcv_id, String category) {
		
		return notificationMapper.getPopUpList(rcv_id, category);
	}

	@Override
	public ArrayList<NotificationVO> getSearchResult(Criteria cri, String rcv_id) {
		return notificationMapper.getSearchResult(cri, rcv_id);
	}

//	@Override
//	public ArrayList<NotificationVO> getData(int pageNumber) {
//		return notificationMapper.getData(pageNumber);
//	}


}
