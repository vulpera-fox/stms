package com.project.stms.service.news;

import java.util.ArrayList;

import com.project.stms.command.NewsVO;
import com.project.stms.util.NewsCriteria;

public interface NewsService {
	
	public ArrayList<NewsVO> getNews(NewsCriteria cri);
	public int getNewsTotal(NewsCriteria cri);
	public NewsVO getNewsDetail(int post_id);
	public NewsVO getNewsPrev(int post_id, NewsCriteria cri);
	public NewsVO getNewsNext(int post_id,NewsCriteria cri);
	public int updateNewsViewCount(int post_id);
									  
}
