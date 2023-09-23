package com.project.stms.service.question;

import java.util.ArrayList;

import com.project.stms.command.NewsVO;
import com.project.stms.util.NewsCriteria;

public interface QuestionService {

	public ArrayList<NewsVO> getQlist(NewsCriteria cri); 
	public int getTotal(NewsCriteria cri);
	public NewsVO getQdetail(int post_id);
	public int updateQviewCount(int post_id);
	public int regiQuestion(NewsVO vo);
	public int deleteQ(int post_id);
	public ArrayList<NewsVO> getMyQlist(NewsCriteria cri, String user_id);
	public int getQtotal(NewsCriteria cri);
	public int updateQ(NewsVO vo);
	
}
