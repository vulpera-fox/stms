package com.project.stms.service.question;

import java.util.ArrayList;

import com.project.stms.command.NewsVO;
import com.project.stms.util.NewsCriteria;

public interface QuestionService {

	public ArrayList<NewsVO> getQlist(NewsCriteria cri); 
	public int getTotal(NewsCriteria cri);
	
	
}
