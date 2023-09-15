package com.project.stms.service.news;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.stms.command.NewsVO;
import com.project.stms.util.NewsCriteria;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsMapper newsMapper;

	

	@Override
	public ArrayList<NewsVO> getNews(NewsCriteria cri) {
		// TODO Auto-generated method stub
		
		return newsMapper.getNews(cri);
	}

	@Override
	public int getNewsTotal(NewsCriteria cri) {
		// TODO Auto-generated method stub
		return newsMapper.getNewsTotal(cri);
	}


	@Override
	public NewsVO getNewsDetail(int post_id) {
		// TODO Auto-generated method stub
		return newsMapper.getNewsDetail(post_id);
	}

	@Override
	public NewsVO getNewsPrev(int post_id,NewsCriteria cri) {
		// TODO Auto-generated method stub
		return newsMapper.getNewsPrev(post_id,cri);
	}

	@Override
	public NewsVO getNewsNext(int post_id,NewsCriteria cri) {
		// TODO Auto-generated method stub
		return newsMapper.getNewsNext(post_id,cri);
	}

	@Override
	public int updateNewsViewCount(int post_id) {
		// TODO Auto-generated method stub
		return newsMapper.updateNewsViewCount(post_id);
	}




}
