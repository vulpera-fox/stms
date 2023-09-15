package com.project.stms.service.question;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.stms.command.NewsVO;
import com.project.stms.util.NewsCriteria;

@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionMapper questionMapper;
	
	@Override
	public ArrayList<NewsVO> getQlist(NewsCriteria cri) {
		return questionMapper.getQlist(cri);
	}

	@Override
	public int getTotal(NewsCriteria cri) {
		return questionMapper.getTotal(cri);
	}

}
